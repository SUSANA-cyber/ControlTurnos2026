package controlador;

import conexion.Conexion;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/MarcajeServlet")
public class MarcajeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            // ✅ SOLUCIÓN UTF-8
            req.setCharacterEncoding("UTF-8");
            res.setContentType("text/html;charset=UTF-8");

            HttpSession sesion = req.getSession();
            int idUsuario = (int) sesion.getAttribute("id_usuario");

            String accion = req.getParameter("accion");
            String mensaje = "";

            Connection con = Conexion.getConexion();

            // 🔍 Último estado
            String sqlUlt = "SELECT * FROM marcajes WHERE usuario_id=? ORDER BY id DESC LIMIT 1";
            PreparedStatement psUlt = con.prepareStatement(sqlUlt);
            psUlt.setInt(1, idUsuario);
            ResultSet rs = psUlt.executeQuery();

            String ultimo = "";

            if (rs.next()) {
                if (rs.getTime("hora_salida") != null) ultimo = "salida";
                else if (rs.getTime("hora_descanso2") != null) ultimo = "descanso2";
                else if (rs.getTime("hora_descanso1") != null) ultimo = "descanso1";
                else if (rs.getTime("hora_entrada") != null) ultimo = "entrada";
            }

            boolean permitido = false;

            switch (accion) {

                case "entrada":
                    if (ultimo.equals("") || ultimo.equals("salida")) {
                        permitido = true;
                    }
                    break;

                case "descanso1":
                    if (ultimo.equals("entrada")) {
                        permitido = true;
                    }
                    break;

                case "descanso2":
                    if (ultimo.equals("descanso1")) {
                        permitido = true;
                    }
                    break;

                case "salida":
                    if (ultimo.equals("descanso2")) {
                        permitido = true;
                    }
                    break;
            }

            if (permitido) {

                String insert = "INSERT INTO marcajes (usuario_id, fecha, hora_entrada, hora_descanso1, hora_descanso2, hora_salida) VALUES (?, CURDATE(), ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(insert);

                ps.setInt(1, idUsuario);

                Time ahora = new Time(System.currentTimeMillis());

                if ("entrada".equals(accion)) {
                    ps.setTime(2, ahora);
                    ps.setNull(3, Types.TIME);
                    ps.setNull(4, Types.TIME);
                    ps.setNull(5, Types.TIME);
                } 
                else if ("descanso1".equals(accion)) {
                    ps.setNull(2, Types.TIME);
                    ps.setTime(3, ahora);
                    ps.setNull(4, Types.TIME);
                    ps.setNull(5, Types.TIME);
                } 
                else if ("descanso2".equals(accion)) {
                    ps.setNull(2, Types.TIME);
                    ps.setNull(3, Types.TIME);
                    ps.setTime(4, ahora);
                    ps.setNull(5, Types.TIME);
                } 
                else if ("salida".equals(accion)) {
                    ps.setNull(2, Types.TIME);
                    ps.setNull(3, Types.TIME);
                    ps.setNull(4, Types.TIME);
                    ps.setTime(5, ahora);
                }

                ps.executeUpdate();

                // Bitácora
                String bit = "INSERT INTO bitacora(usuario_id, modulo, tipo_operacion, fecha_hora) VALUES (?, 'Marcaje', ?, NOW())";
                PreparedStatement psBit = con.prepareStatement(bit);
                psBit.setInt(1, idUsuario);
                psBit.setString(2, accion);
                psBit.executeUpdate();

                // ✅ MENSAJE EXACTO
                mensaje = "Marcaje realizado con éxito";

                ps.close();
                psBit.close();
            } else {
                mensaje = "Acción no permitida";
            }

            res.sendRedirect("marcaje.jsp?msg=" + java.net.URLEncoder.encode(mensaje, "UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("marcaje.jsp?msg=Error");
        }
    }
}
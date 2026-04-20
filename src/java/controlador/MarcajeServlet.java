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

        Connection con = null;

        try {
            HttpSession sesion = req.getSession(false);

            if (sesion == null || sesion.getAttribute("id_usuario") == null) {
                res.sendRedirect("login.jsp");
                return;
            }

            int idUsuario = (int) sesion.getAttribute("id_usuario");
            String accion = req.getParameter("accion");

            con = Conexion.getConexion();

            // 🔍 Buscar registro del día
            String sql = "SELECT * FROM marcajes WHERE usuario_id=? AND fecha=CURDATE()";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            boolean existe = rs.next();

            String mensaje = "";
            Time ahora = new Time(System.currentTimeMillis());

            // =========================
            // ENTRADA
            // =========================
            if ("entrada".equals(accion)) {

                if (existe) {
                    mensaje = "Ya marcó entrada hoy";
                } else {

                    String insert = "INSERT INTO marcajes (usuario_id, fecha, hora_entrada) VALUES (?, CURDATE(), ?)";
                    PreparedStatement ps2 = con.prepareStatement(insert);
                    ps2.setInt(1, idUsuario);
                    ps2.setTime(2, ahora);
                    ps2.executeUpdate();

                    mensaje = "Entrada registrada";
                }

            }

            // =========================
            // DESCANSO 1
            // =========================
            else if ("descanso1".equals(accion)) {

                if (!existe) {
                    mensaje = "Debe marcar entrada primero";
                } else if (rs.getTime("hora_descanso1") != null) {
                    mensaje = "Ya marcó descanso 1";
                } else {

                    String update = "UPDATE marcajes SET hora_descanso1=? WHERE usuario_id=? AND fecha=CURDATE()";
                    PreparedStatement ps2 = con.prepareStatement(update);
                    ps2.setTime(1, ahora);
                    ps2.setInt(2, idUsuario);
                    ps2.executeUpdate();

                    mensaje = "Descanso 1 registrado";
                }

            }

            // =========================
            // DESCANSO 2
            // =========================
            else if ("descanso2".equals(accion)) {

                if (!existe) {
                    mensaje = "Debe marcar entrada primero";
                } else if (rs.getTime("hora_descanso1") == null) {
                    mensaje = "Debe marcar descanso 1 primero";
                } else if (rs.getTime("hora_descanso2") != null) {
                    mensaje = "Ya marcó descanso 2";
                } else {

                    String update = "UPDATE marcajes SET hora_descanso2=? WHERE usuario_id=? AND fecha=CURDATE()";
                    PreparedStatement ps2 = con.prepareStatement(update);
                    ps2.setTime(1, ahora);
                    ps2.setInt(2, idUsuario);
                    ps2.executeUpdate();

                    mensaje = "Descanso 2 registrado";
                }

            }

            // =========================
            // SALIDA
            // =========================
            else if ("salida".equals(accion)) {

                if (!existe) {
                    mensaje = "Debe marcar entrada primero";
                } else if (rs.getTime("hora_descanso2") == null) {
                    mensaje = "Debe completar descansos primero";
                } else if (rs.getTime("hora_salida") != null) {
                    mensaje = "Ya marcó salida";
                } else {

                    String update = "UPDATE marcajes SET hora_salida=? WHERE usuario_id=? AND fecha=CURDATE()";
                    PreparedStatement ps2 = con.prepareStatement(update);
                    ps2.setTime(1, ahora);
                    ps2.setInt(2, idUsuario);
                    ps2.executeUpdate();

                    mensaje = "Salida registrada";
                }

            }

            // =========================
            // BITÁCORA
            // =========================
            String bit = "INSERT INTO bitacora (usuario_id, modulo, tipo_operacion, descripcion, fecha_hora) VALUES (?, 'Marcaje', ?, ?, NOW())";
            PreparedStatement ps3 = con.prepareStatement(bit);
            ps3.setInt(1, idUsuario);
            ps3.setString(2, accion);
            ps3.setString(3, mensaje);
            ps3.executeUpdate();

            res.sendRedirect("marcaje.jsp?msg=" + java.net.URLEncoder.encode(mensaje, "UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("marcaje.jsp?msg=Error en el sistema");
        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
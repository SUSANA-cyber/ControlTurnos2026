package controlador;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import conexion.Conexion;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String dpi = req.getParameter("dpi");
        String nombre = req.getParameter("nombre");
        String usuario = req.getParameter("usuario");
        String area = req.getParameter("area");
        String puesto = req.getParameter("puesto");
        String turno = req.getParameter("turno");
        String correo = req.getParameter("correo");
        String password = req.getParameter("password");
        String estado = req.getParameter("estado");

        String modo_inactivo = req.getParameter("modo_inactivo");

        // 🔥 SI ESTÁ ACTIVO → NO DEBE TENER MOTIVO
        if ("Activo".equals(estado)) {
            modo_inactivo = null;
        } else {
            if (modo_inactivo == null || modo_inactivo.isEmpty()) {
                modo_inactivo = "Sin especificar";
            }
        }

        Connection con = null;

        try {
            con = Conexion.getConexion();

            // 🔍 VALIDAR USUARIO DUPLICADO
            String validar = "SELECT * FROM usuarios WHERE usuario=?";
            PreparedStatement psVal = con.prepareStatement(validar);
            psVal.setString(1, usuario);
            ResultSet rs = psVal.executeQuery();

            if (rs.next()) {
                res.sendRedirect("usuarios.jsp?error=1");
                return;
            }

            // 💾 INSERT
            String sql = "INSERT INTO usuarios (dpi, nombre, usuario, area, correo, password, estado, modo_inactivo, puesto, turno) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, dpi);
            ps.setString(2, nombre);
            ps.setString(3, usuario);
            ps.setString(4, area);
            ps.setString(5, correo);
            ps.setString(6, password);
            ps.setString(7, estado);
            ps.setString(8, modo_inactivo);
            ps.setString(9, puesto);
            ps.setString(10, turno);

            int resultado = ps.executeUpdate();

            // ✅ VALIDAR SI INSERT FUNCIONÓ
            if (resultado > 0) {
                res.sendRedirect("usuarios.jsp?ok=1");
            } else {
                res.sendRedirect("usuarios.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("usuarios.jsp?error=1");
        } finally {
            try {
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
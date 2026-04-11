package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        int rol_id = Integer.parseInt(req.getParameter("rol_id"));

        // Si está activo, no lleva motivo
        if ("Activo".equals(estado)) {
            modo_inactivo = null;
        }

        Connection con = null;
        PreparedStatement psVal = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();

            // Validar usuario duplicado
            String validar = "SELECT * FROM usuarios WHERE usuario=?";
            psVal = con.prepareStatement(validar);
            psVal.setString(1, usuario);
            rs = psVal.executeQuery();

            if (rs.next()) {
                res.sendRedirect("usuarios.jsp?error=1");
                return;
            }

            // Insertar usuario con rol_id directamente
            String sql = "INSERT INTO usuarios "
                    + "(dpi, nombre, usuario, area, correo, password, estado, modo_inactivo, puesto, turno, rol_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(sql);

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
            ps.setInt(11, rol_id);

            int resultado = ps.executeUpdate();

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
                if (rs != null) rs.close();
                if (psVal != null) psVal.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
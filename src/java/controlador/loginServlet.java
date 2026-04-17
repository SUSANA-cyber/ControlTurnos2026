package controlador;

import conexion.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();

            String sql = "SELECT u.id, u.password, r.nombre AS rol " +
                         "FROM usuarios u " +
                         "LEFT JOIN roles r ON u.rol_id = r.id " +
                         "WHERE u.usuario=? AND u.estado='Activo'";

            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);

            rs = ps.executeQuery();

            if (rs.next()) {

                String passBD = rs.getString("password");
                String rol = rs.getString("rol");
                int idUsuario = rs.getInt("id");

                if (passBD.equals(password)) {

                    HttpSession sesion = req.getSession();
                    sesion.setAttribute("usuario", usuario);
                    sesion.setAttribute("rol", rol);
                    sesion.setAttribute("id_usuario", idUsuario);

                    res.sendRedirect("dashboard.jsp");

                } else {
                    res.sendRedirect("login.jsp?error=1");
                }

            } else {
                res.sendRedirect("login.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("login.jsp?error=2");

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
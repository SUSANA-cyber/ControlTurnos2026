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

        try {
            Connection con = Conexion.getConexion();

            String sql = "SELECT u.id, u.password, r.nombre AS rol " +
                         "FROM usuarios u " +
                         "LEFT JOIN roles r ON u.rol_id = r.id " +
                         "WHERE u.usuario=? AND u.estado='Activo'";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idCapturado = rs.getInt("id");
                String passBD = rs.getString("password");
                String rol = rs.getString("rol");
                
                if (passBD.equals(password)) {

                    HttpSession sesion = req.getSession();
                    sesion.setAttribute("id_usuario", idCapturado);
                    sesion.setAttribute("usuario", usuario);
                    sesion.setAttribute("rol", rol);

                    res.sendRedirect("dashboard.jsp");

                } else {
                    res.sendRedirect("login.jsp?error=1");
                }

            } else {
                res.sendRedirect("login.jsp?error=1");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("login.jsp?error=2");
        }
    }
}
package controlador;

import Config.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        HttpSession sesion = req.getSession(false);

        Connection con = null;
        PreparedStatement ps = null;

        try {

            if (sesion != null) {

                Integer idUsuario =
                        (Integer) sesion.getAttribute("id_usuario");

                if (idUsuario != null) {

                    con = Conexion.getConexion();

                    String sql =
                            "INSERT INTO bitacora " +
                            "(usuario_id, modulo, tipo_operacion, descripcion, fecha_hora) " +
                            "VALUES (?, 'Login', 'Logout', 'Cierre de sesión', NOW())";

                    ps = con.prepareStatement(sql);
                    ps.setInt(1, idUsuario);
                    ps.executeUpdate();
                }

                sesion.invalidate();
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        res.sendRedirect(req.getContextPath() + "/vistas/login.jsp");
    }
}
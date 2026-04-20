package controlador;

import conexion.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EliminarTurnoServlet")
public class EliminarTurnoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        Connection con = null;
        PreparedStatement ps = null;

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            con = Conexion.getConexion();

            String sql = "DELETE FROM asignacion_turnos WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

            res.sendRedirect(req.getContextPath() + "/turnos.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
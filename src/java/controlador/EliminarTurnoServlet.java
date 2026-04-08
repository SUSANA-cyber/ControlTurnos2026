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

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            Connection con = Conexion.getConexion();

            String sql = "DELETE FROM turnos WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            con.close();

            res.sendRedirect(req.getContextPath() + "/turnos.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

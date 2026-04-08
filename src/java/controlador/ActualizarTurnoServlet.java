package controlador;

import conexion.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ActualizarTurnoServlet")
public class ActualizarTurnoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));
            String inicio = req.getParameter("fecha_inicio");
            String fin = req.getParameter("fecha_fin");
            String turno = req.getParameter("turno");

            Connection con = Conexion.getConexion();

            String sql = "UPDATE turnos SET fecha_inicio=?, fecha_fin=?, turno=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, inicio);
            ps.setString(2, fin);
            ps.setString(3, turno);
            ps.setInt(4, id);

            ps.executeUpdate();

            ps.close();
            con.close();

            res.sendRedirect(req.getContextPath() + "/turnos.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
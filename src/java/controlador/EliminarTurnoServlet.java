package controlador;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModeloDAO.TurnoDAO;

@WebServlet("/EliminarTurnoServlet")
public class EliminarTurnoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));

            // 🔹 DAO
            TurnoDAO dao = new TurnoDAO();

            // 🔹 ELIMINAR
            dao.eliminarTurno(id);

            // 🔹 Redirección
            res.sendRedirect(req.getContextPath() + "/turnos.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
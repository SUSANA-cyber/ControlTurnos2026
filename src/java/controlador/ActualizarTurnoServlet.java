package controlador;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModeloDAO.TurnoDAO;

@WebServlet("/ActualizarTurnoServlet")
public class ActualizarTurnoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        try {

            int id = Integer.parseInt(req.getParameter("id"));
            String inicio = req.getParameter("fecha_inicio");
            String fin = req.getParameter("fecha_fin");
            int turno_id = Integer.parseInt(req.getParameter("turno_id"));

            // 🔹 DAO
            TurnoDAO dao = new TurnoDAO();

            // 🔹 Ejecutar UPDATE
            dao.actualizarTurno(id, inicio, fin, turno_id);

            // 🔹 Redirección igual
            res.sendRedirect(req.getContextPath() + "/turnos.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
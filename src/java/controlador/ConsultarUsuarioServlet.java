package controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModeloDAO.UsuarioDAO;

@WebServlet("/ConsultarUsuarioServlet")
public class ConsultarUsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String buscar = req.getParameter("buscar");

        if (buscar == null) {
            buscar = "";
        }

        try {

            // 🔹 DAO
            UsuarioDAO dao = new UsuarioDAO();

            // 🔹 Obtener lista (misma lógica)
            List<String[]> listaUsuarios = dao.listarUsuarios(buscar);

            // 🔹 Enviar a JSP
            req.setAttribute("listaUsuarios", listaUsuarios);

            // 🔹 forward igual
            req.getRequestDispatcher("consultarUsuarios.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
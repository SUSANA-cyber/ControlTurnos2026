package controlador;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ModeloDAO.UsuarioDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");

        try {

            UsuarioDAO dao = new UsuarioDAO();

            ResultSet rs = dao.login(usuario);

            if (rs.next()) {

                String passBD = rs.getString("password");
                String rol = rs.getString("rol");
                int idUsuario = rs.getInt("id");

                if (passBD.equals(password)) {

                    HttpSession sesion = req.getSession();
                    sesion.setAttribute("usuario", usuario);
                    sesion.setAttribute("rol", rol);
                    sesion.setAttribute("id_usuario", idUsuario);

                    res.sendRedirect(
                            req.getContextPath() +
                            "/vistas/dashboard.jsp");

                } else {
                    res.sendRedirect(
                            req.getContextPath() +
                            "/vistas/login.jsp?error=1");
                }

            } else {
                res.sendRedirect(
                        req.getContextPath() +
                        "/vistas/login.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();

            res.sendRedirect(
                    req.getContextPath() +
                    "/vistas/login.jsp?error=2");
        }
    }
}
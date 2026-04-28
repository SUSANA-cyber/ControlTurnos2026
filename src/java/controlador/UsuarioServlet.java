package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Usuario;
import ModeloDAO.UsuarioDAO;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        // Obtener datos del formulario
        String dpi = req.getParameter("dpi");
        String nombre = req.getParameter("nombre");
        String usuario = req.getParameter("usuario");
        String area = req.getParameter("area");
        String puesto = req.getParameter("puesto");

        int turno_actual_id =
                Integer.parseInt(req.getParameter("turno_actual_id"));

        String correo = req.getParameter("correo");
        String password = req.getParameter("password");
        String estado = req.getParameter("estado");

        // Motivo inactivo
        String motivoParam =
                req.getParameter("motivo_inactivo_id");

        Integer motivo_inactivo_id = null;

        if (motivoParam != null && !motivoParam.isEmpty()) {
            motivo_inactivo_id = Integer.parseInt(motivoParam);
        }

        int rol_id =
                Integer.parseInt(req.getParameter("rol_id"));

        // Si está activo, no lleva motivo
        if ("Activo".equals(estado)) {
            motivo_inactivo_id = null;
        }

        // Crear objeto modelo
        Usuario u = new Usuario();
        u.setDpi(dpi);
        u.setNombre(nombre);
        u.setUsuario(usuario);
        u.setArea(area);
        u.setPuesto(puesto);
        u.setTurno_actual_id(turno_actual_id);
        u.setCorreo(correo);
        u.setPassword(password);
        u.setEstado(estado);
        u.setMotivo_inactivo_id(motivo_inactivo_id);
        u.setRol_id(rol_id);

        UsuarioDAO dao = new UsuarioDAO();

        try {

            // Validar usuario duplicado
            if (dao.existeUsuario(usuario)) {
                res.sendRedirect(
                        req.getContextPath() +
                        "/vistas/usuarios.jsp?error=1");
                return;
            }

            // Insertar usuario
            boolean resultado = dao.agregarUsuario(u);

            if (resultado) {
                res.sendRedirect(
                        req.getContextPath() +
                        "/vistas/usuarios.jsp?ok=1");
            } else {
                res.sendRedirect(
                        req.getContextPath() +
                        "/vistas/usuarios.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();

            res.sendRedirect(
                    req.getContextPath() +
                    "/vistas/usuarios.jsp?error=1");
        }
    }
}
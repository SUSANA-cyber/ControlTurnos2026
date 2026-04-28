package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Turno;
import ModeloDAO.TurnoDAO;

@WebServlet(name = "TurnoServlet", urlPatterns = {"/TurnoServlet"})
public class TurnoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id_usuario =
                Integer.parseInt(request.getParameter("id_usuario"));

        String fecha_inicio =
                request.getParameter("fecha_inicio");

        String fecha_fin =
                request.getParameter("fecha_fin");

        String turno =
                request.getParameter("turno");

        try {

            // VALIDAR FECHAS
            if (fecha_inicio.compareTo(fecha_fin) > 0) {
                response.sendRedirect(
                        request.getContextPath() +
                        "/vistas/turnos.jsp?error=1");
                return;
            }

            // MODELO
            Turno t = new Turno();
            t.setId_usuario(id_usuario);
            t.setFecha_inicio(fecha_inicio);
            t.setFecha_fin(fecha_fin);
            t.setTurno(turno);

            // DAO
            TurnoDAO dao = new TurnoDAO();

            // VALIDAR DUPLICADO
            if (dao.existeTurno(id_usuario, fecha_inicio, fecha_fin)) {
                response.sendRedirect(
                        request.getContextPath() +
                        "/vistas/turnos.jsp?error=duplicado");
                return;
            }

            // INSERTAR
            boolean resultado = dao.agregarTurno(t);

            if (resultado) {
                response.sendRedirect(
                        request.getContextPath() +
                        "/vistas/turnos.jsp?ok=1");
            } else {
                response.sendRedirect(
                        request.getContextPath() +
                        "/vistas/turnos.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();

            response.sendRedirect(
                    request.getContextPath() +
                    "/vistas/turnos.jsp?error=1");
        }
    }
}
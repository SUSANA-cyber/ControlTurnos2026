package controlador;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import ModeloDAO.MarcajeDAO;

@WebServlet("/MarcajeServlet")
public class MarcajeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            HttpSession sesion = req.getSession(false);

            if (sesion == null || sesion.getAttribute("id_usuario") == null) {
                res.sendRedirect("login.jsp");
                return;
            }

            int idUsuario = (int) sesion.getAttribute("id_usuario");
            String accion = req.getParameter("accion");

            MarcajeDAO dao = new MarcajeDAO();

            ResultSet rs = dao.obtenerMarcajeHoy(idUsuario);
            boolean existe = rs.next();

            String mensaje = "";
            Time ahora = new Time(System.currentTimeMillis());

            // =========================
            // ENTRADA
            // =========================
            if ("entrada".equals(accion)) {

                if (existe) {
                    mensaje = "Ya marcó entrada hoy";
                } else {
                    dao.insertarEntrada(idUsuario, ahora);
                    mensaje = "Entrada registrada";
                }

            }

            // =========================
            // DESCANSO 1
            // =========================
            else if ("descanso1".equals(accion)) {

                if (!existe) {
                    mensaje = "Debe marcar entrada primero";
                } else if (rs.getTime("hora_descanso1") != null) {
                    mensaje = "Ya marcó descanso 1";
                } else {
                    dao.actualizarDescanso1(idUsuario, ahora);
                    mensaje = "Descanso 1 registrado";
                }

            }

            // =========================
            // DESCANSO 2
            // =========================
            else if ("descanso2".equals(accion)) {

                if (!existe) {
                    mensaje = "Debe marcar entrada primero";
                } else if (rs.getTime("hora_descanso1") == null) {
                    mensaje = "Debe marcar descanso 1 primero";
                } else if (rs.getTime("hora_descanso2") != null) {
                    mensaje = "Ya marcó descanso 2";
                } else {
                    dao.actualizarDescanso2(idUsuario, ahora);
                    mensaje = "Descanso 2 registrado";
                }

            }

            // =========================
            // SALIDA
            // =========================
            else if ("salida".equals(accion)) {

                if (!existe) {
                    mensaje = "Debe marcar entrada primero";
                } else if (rs.getTime("hora_descanso2") == null) {
                    mensaje = "Debe completar descansos primero";
                } else if (rs.getTime("hora_salida") != null) {
                    mensaje = "Ya marcó salida";
                } else {
                    dao.actualizarSalida(idUsuario, ahora);
                    mensaje = "Salida registrada";
                }

            }

            // 🔹 BITÁCORA
            dao.insertarBitacora(idUsuario, accion, mensaje);

            res.sendRedirect("marcaje.jsp?msg=" + java.net.URLEncoder.encode(mensaje, "UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("marcaje.jsp?msg=Error en el sistema");
        }
    }
}
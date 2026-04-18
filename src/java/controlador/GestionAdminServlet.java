package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModeloDAO.TurnoDAO;
import ModeloDAO.SolicitudDAO;
import conexion.ServicioCorreo; 

@WebServlet(name = "GestionAdminServlet", urlPatterns = {"/GestionAdminServlet"})
public class GestionAdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
        int idSolicitud = Integer.parseInt(request.getParameter("id_solicitud"));
        String correoEmpleado = request.getParameter("correo_empleado");
        String tipoTabla = request.getParameter("tipo_tabla"); // 
        String tipoSolicitud = request.getParameter("tipo_solicitud");
        String decision = request.getParameter("decision"); 
        boolean exito = false;

     
        if (tipoTabla.equals("turnos")) {
            TurnoDAO tDao = new TurnoDAO();
            exito = tDao.ActualizarEstado(idSolicitud, decision); 
        } else {
            SolicitudDAO sDao = new SolicitudDAO();
            exito = sDao.ActualizarEstado(idSolicitud, decision);
        }

       
        if (exito) {
            String asunto = "Respuesta a tu solicitud de " + tipoSolicitud;
            String mensaje = "Hola,\n\nTe informamos que tu solicitud de " + tipoSolicitud + 
                             " ha sido: " + decision.toUpperCase() + " por la administración.\n\n" +
                             "Saludos,\nSistema ControlTurnos2026";
            
            
            ServicioCorreo.enviarEmail(correoEmpleado, asunto, mensaje);
            
       
            response.sendRedirect("GstionAdminServlet.jsp");
        } else {
            response.sendRedirect("error_solicitar.jsp");
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.MSolicitudes;
import ModeloDAO.SolicitudDAO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

        

/**
 *
 * @author Acer
 */
@WebServlet(name = "Solicitudes", urlPatterns = {"/Solicitudes"})
public class Solicitudes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        
        Integer idLogueado = (Integer) request.getSession().getAttribute("id_usuario");        
        System.out.println("DEBUG: El ID recuperado de la sesión es: " + idLogueado);
        
        MSolicitudes msol= new MSolicitudes();
        SolicitudDAO dao= new SolicitudDAO();
        
        
        
        msol.setUsuario_id(idLogueado);
        msol.setTipo(request.getParameter("Sopciones"));
        msol.setFecha_inicio(request.getParameter("FechaInicio"));
        msol.setFecha_fin(request.getParameter("FechaFIn"));
        msol.setMotivo(request.getParameter("motivo"));
        
        boolean exito=dao.AgregarSolicitud(msol);
        
        if(exito){
            response.sendRedirect("Solicitudes.jsp?msj=exito");
            if (idLogueado == null) {
                System.out.println("ALERTA: La sesión no tiene el ID del usuario.");
}
            }else{
            response.sendRedirect("Solicitudes.jsp?msj=error");
            
        }
    }



   

}

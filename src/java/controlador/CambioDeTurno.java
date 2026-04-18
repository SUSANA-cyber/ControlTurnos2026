/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ModeloDAO.TurnoDAO;
import Modelo.MTurno;
        

/**
 *
 * @author Acer
 */
@WebServlet(name = "CambioDeTurno", urlPatterns = {"/CambioDeTurno"})
public class CambioDeTurno extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
         
         if (idLogueado == null) {
             System.out.println("No hay sesión. Redirigiendo al login...");
             response.sendRedirect("login.jsp");
             return; 
         }
          
          MTurno mtu =new MTurno();
          TurnoDAO dao= new TurnoDAO();
          
          
        
           mtu.setId_usuario(idLogueado); //get para acceder, set para modificar
           mtu.setFecha_inico(request.getParameter("FechaInicio"));
           mtu.setNuevoTurno(request.getParameter("NuevoTurno"));
           mtu.setNuevafecha(request.getParameter("Nuevafecha"));
           mtu.setTurnoInicial(request.getParameter("InicialTurno"));
           mtu.setMotivo(request.getParameter("motivo"));
           
           
           boolean exito =dao.CambiarTurno(mtu);
           
           if(exito){
               response.sendRedirect("ActualizarTurno.jsp?msj=exito");
           }else{
           
               System.err.println("error al enviar solicitud");
               response.sendRedirect("ActualizarTurno.jsp?=error");
           }
           

        
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

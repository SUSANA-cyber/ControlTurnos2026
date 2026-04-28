<%-- 
    Document   : Solicitudes
    Created on : 12/04/2026, 11:51:25 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>  
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Solicitudes</h1>
        
            <form action="Solicitudes" method="POST">
            <select name="Sopciones"  required>
                <option value="Vacaciones">Vacaciones</option>
                <option value="PermisoPersonal">Permiso Personal</option>
                <option value="CitaAlIgss">Cita al Igss</option>
                <option value="LicenciaDeCumpleaños">Licencia De Cumpleaños</option>
                <option value="SuspensionLaboral">Suspension Laboral</option>
                <option value="Otros">Otros</option> 
            </select>
        
       
        
        
        
                <div class="campo">
                    <label>Fecha De Inicio</label>
                    <input type="date" name="FechaInicio"  required>  
                </div>   
                
                <div clas="campo">
                    <label> Fecha Fin </label>
                    <input type="date" name="FechaFIn"required>
                </div>
                
                <div class="campo">
                    <label>Motivo De La Solicitud:</label>
                    <textarea name="motivo" rows="4" placeholder="Explique Brevemente el motivo" required></textarea>      
                </div>
                
                <div clas="botones">
                    <a href="dashboard.jsp">Regresar</a>
                    <button type="submit" name="accion" value="GuardarSolicitud">Guardar</button>  
                </div>          
        </form>
        
        
    <%                      

        String mensaje=request.getParameter("msj");
        if("exito".equals(mensaje)){
        
        %>
        
        <script>
                Swal.fire({
                    title: '¡Operación Exitosa!',
                    text: 'Los datos se guardaron correctamente en el sistema.',
                    icon: 'success',
                    confirmButtonText: 'Entendido'
                });
            </script>
        
        <%                      

       
            } else if("error".equals(mensaje)){
        
        %>
        
        
        <script>
                Swal.fire({
                    title: '¡Error del Sistema!',
                    text: 'No se pudo completar la solicitud. Revisa la base de datos.',
                    icon: 'error',
                    confirmButtonColor: '#d33',
                    confirmButtonText: 'Intentar de nuevo'
                });
            </script>
    <%
        }
    %>  
        
    </body>
</html>

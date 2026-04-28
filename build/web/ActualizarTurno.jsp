<%-- 
    Document   : ActualizarTurno
    Created on : 14/04/2026, 01:52:55 PM
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
        <form action="CambioDeTurno" method="POST">
            <div class="campo">
                    <label>Fecha De Inicio</label>
                    <input type="date" name="FechaInicio"  required>  
                </div > 
                
                <div class="campo">
                    <label>Turno Inicial </label>
                    <select name="InicialTurno" required>
                        <option value="Matutino">Matutino</option>
                        <option value="Vespertino">Vespertino</option>
                        <option value="Diurno">Diurno</option>    
                    </select>   
                </div>
            
                <div class="campo">
                    <label>Nueva fecha</label>
                    <input type="date" name="Nuevafecha"required>  
                </div >
                
                
                <div class="campo">
                    <label>Nuevo Turno</label>
                    <select name="NuevoTurno" required=>
                        <option value="Matutino">Matutino</option>
                        <option value="Vespertino">Vespertino</option>
                        <option value="Diurno">Diurno</option>    
                    </select>
                    
                </div>
            
            
            <div class="campo">
                    <label>Motivo De La Solicitud:</label>
                    <textarea name="motivo" rows="4" placeholder="Explique Brevemente el motivo" required></textarea>      
                </div>
                
                
                <div clas="botones">
                    <a href="dashboard.jsp">Regresar</a>
                    <button type="submit" name="accion" value="Guardar">Guardar</button>
                    <button type="reset" > Limpiar Campos</button
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

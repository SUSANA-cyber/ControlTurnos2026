<%-- 
    Document   : GstionSolicitudes
    Created on : 15/04/2026, 04:20:16 PM
    Author     : cesar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="conexion.Conexion"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panel de Administración</title>
    </head>
    <body>
        <h1>Panel de Administración</h1>
        <h2>Gestión de Solicitudes Pendientes</h2>
        
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr style="background-color: #f2f2f2;">
                    <th>Empleado</th>
                    <th>Tipo de Solicitud</th>
                    <th>Detalles / Motivo</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <%
                    try {
                        Connection con = Conexion.getConexion();

                       
                       
                        String sqlTurnos = "SELECT t.id, u.usuario, u.correo, t.Motivo, t.TurnoInicial, t.NuevoTurno " +
                                           "FROM turnos t JOIN usuarios u ON t.id_usuario = u.id " +
                                           "WHERE t.estado LIKE 'pendiente%'";
                        PreparedStatement ps1 = con.prepareStatement(sqlTurnos);
                        ResultSet rsTurnos = ps1.executeQuery();

                        while(rsTurnos.next()) {
                %>
                <tr>
                    <td><%= rsTurnos.getString("usuario") %> (<%= rsTurnos.getString("correo") %>)</td>
                    <td><strong>Cambio de Turno</strong></td>
                    <td>De <%= rsTurnos.getString("TurnoInicial") %> a <%= rsTurnos.getString("NuevoTurno") %>.<br>
                        <em>Motivo:</em> <%= rsTurnos.getString("Motivo") %>
                    </td>
                    <td>
                        <form action="GestionAdminServlet" method="POST">
                            <input type="hidden" name="id_solicitud" value="<%= rsTurnos.getInt("id") %>">
                            <input type="hidden" name="correo_empleado" value="<%= rsTurnos.getString("correo") %>">
                            <input type="hidden" name="tipo_tabla" value="turnos">
                            <input type="hidden" name="tipo_solicitud" value="Cambio de Turno">
                            
                            <button type="submit" name="decision" value="Aprobado" style="color: green;">Aprobar</button>
                            <button type="submit" name="decision" value="Rechazado" style="color: red;">Rechazar</button>
                        </form>
                    </td>
                </tr>
                <%
                        }

                  
                        String sqlSol = "SELECT s.id, u.usuario, u.correo, s.tipo, s.motivo " +
                                        "FROM solicitudes s JOIN usuarios u ON s.usuario_id = u.id " +
                                        "WHERE s.estado LIKE 'pendiente%'";
                        PreparedStatement ps2 = con.prepareStatement(sqlSol);
                        ResultSet rsSol = ps2.executeQuery();

                        while(rsSol.next()) {
                %>
                <tr>
                    <td><%= rsSol.getString("usuario") %> (<%= rsSol.getString("correo") %>)</td>
                    <td><strong><%= rsSol.getString("tipo") %></strong></td>
                    <td><%= rsSol.getString("motivo") %></td>
                    <td>
                        <form action="GestionAdminServlet" method="POST">
                            <input type="hidden" name="id_solicitud" value="<%= rsSol.getInt("id") %>">
                            <input type="hidden" name="correo_empleado" value="<%= rsSol.getString("correo") %>">
                            <input type="hidden" name="tipo_tabla" value="solicitudes">
                            <input type="hidden" name="tipo_solicitud" value="<%= rsSol.getString("tipo") %>">
                            
                      <button type="submit" name="decision" value="Aprobado" style="color: green ;">Aprobar</button>
                            <button type="submit" name="decision" value="Rechazado" style="color: red;">Rechazar</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                        con.close();
                        
                    } catch(Exception e) {
                        out.println("<tr><td colspan='4'><b>Error al cargar datos:</b> " + e.getMessage() + "</td></tr>");
                    }
                %>
            </tbody>
        </table>
        
        <br>
        <a href="dashboard.jsp">Regresar</a>
        
        
        
    </body>
</html>
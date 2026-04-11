<%@ page import="java.util.ArrayList" %>

<h2>Consultar Usuarios</h2>

<form action="ConsultarUsuarioServlet" method="get">
    <input type="text" name="buscar" placeholder="Buscar usuario...">
    <button type="submit">Buscar</button>
</form>

<br>

<table border="1">
    <tr>
        <th>Usuario</th>
        <th>Área</th>
        <th>Estado</th>
    </tr>

<%
ArrayList<String[]> lista = (ArrayList<String[]>) request.getAttribute("lista");

if(lista != null){
    for(String[] u : lista){
%>
    <tr>
        <td><%= u[0] %></td>
        <td><%= u[1] %></td>
        <td><%= u[2] %></td>
    </tr>
<%
    }
}
%>

</table>

<br>
<a href="dashboard.jsp">Regresar</a>
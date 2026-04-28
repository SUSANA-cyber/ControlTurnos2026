<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consultar Usuarios</title>
</head>
<body>

<h2>Consultar Usuarios</h2>

<form action="../ConsultarUsuarioServlet" method="get">
    <input type="text" name="buscar" placeholder="Buscar usuario...">
    <button type="submit">Buscar</button>
</form>

<br>

<table border="1">
    <tr>
        <th>Usuario</th>
        <th>Área</th>
        <th>Puesto</th>
        <th>Turno</th>
        <th>Rol</th>
        <th>Estado</th>
    </tr>

<%
ArrayList<String[]> lista = (ArrayList<String[]>) request.getAttribute("lista");

if (lista != null && !lista.isEmpty()) {
    for (String[] u : lista) {
%>
    <tr>
        <td><%= u[0] %></td>
        <td><%= u[1] %></td>
        <td><%= u[2] %></td>
        <td><%= u[3] %></td>
        <td><%= u[4] %></td>
        <td><%= u[5] %></td>
    </tr>
<%
    }
} else {
%>
    <tr>
        <td colspan="6">No hay resultados</td>
    </tr>
<%
}
%>

</table>

<br>
<a href="dashboard.jsp">Regresar</a>

</body>
</html>
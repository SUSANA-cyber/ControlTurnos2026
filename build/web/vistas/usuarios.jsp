<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String usuarioSesion = (String) session.getAttribute("usuario");
String rolSesion = (String) session.getAttribute("rol");

if (usuarioSesion == null) {
    response.sendRedirect("login.jsp");
    return;
}

if (rolSesion == null || !rolSesion.equals("AdminRRHH")) {
    response.sendRedirect("dashboard.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuarios</title>

<style>
body{
    font-family: Arial;
    margin: 30px;
}

input, select{
    width: 300px;
    padding: 6px;
    margin-bottom: 10px;
}

button{
    padding: 10px 20px;
}

label{
    display:block;
    font-weight:bold;
}
</style>

</head>
<body>

<h1>Registrar Empleado</h1>

<%
if (request.getParameter("ok") != null) {
%>
<p style="color:green;">Usuario registrado correctamente</p>
<%
}
%>

<%
if (request.getParameter("error") != null) {
%>
<p style="color:red;">Error al registrar usuario</p>
<%
}
%>

<form action="../UsuarioServlet" method="post">

<label>DPI</label>
<input type="text" name="dpi" required>

<label>Nombre</label>
<input type="text" name="nombre" required>

<label>Usuario</label>
<input type="text" name="usuario" required>

<label>Área</label>
<input type="text" name="area" required>

<label>Puesto</label>
<input type="text" name="puesto" required>

<label>Correo</label>
<input type="email" name="correo" required>

<label>Password</label>
<input type="password" name="password" required>

<label>Turno</label>
<select name="turno_actual_id" required>
<option value="1">Matutino</option>
<option value="2">Vespertino</option>
<option value="3">Diurno</option>
</select>

<label>Estado</label>
<select name="estado" required>
<option value="Activo">Activo</option>
<option value="Inactivo">Inactivo</option>
</select>

<label>Motivo Inactividad</label>
<select name="motivo_inactivo_id">
<option value="">-- Ninguno --</option>
<option value="1">Vacaciones</option>
<option value="2">Permiso Personal</option>
<option value="3">IGSS</option>
<option value="4">Licencia Cumpleaños</option>
<option value="5">Suspensión Laboral</option>
<option value="6">Otros</option>
</select>

<label>Rol</label>
<select name="rol_id" required>
<option value="1">AdminRRHH</option>
<option value="2">AdminArea</option>
<option value="3">Empleado</option>
</select>

<br><br>

<button type="submit">Registrar</button>

</form>

<br>
<a href="dashboard.jsp">Regresar</a>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
String usuarioSesion = (String) session.getAttribute("usuario");
String rolSesion = (String) session.getAttribute("rol");

if(usuarioSesion == null){
    response.sendRedirect("login.jsp");
    return;
}

if(rolSesion == null || !rolSesion.equals("AdminRRHH")){
    response.sendRedirect("dashboard.jsp");
    return;
}
%>

<html>
<head>
<title>Usuarios</title>
<meta charset="UTF-8">
</head>
<body>

<h1>Registrar Empleado</h1>

<%
if(request.getParameter("ok") != null){
%>
<p style="color:green;">Usuario registrado correctamente</p>
<%
}
%>

<%
if(request.getParameter("error") != null){
%>
<p style="color:red;">Error al registrar usuario</p>
<%
}
%>

<form action="UsuarioServlet" method="post">

<label>DPI</label>
<input type="text" name="dpi"><br>

<label>Nombre</label>
<input type="text" name="nombre"><br>

<label>Usuario</label>
<input type="text" name="usuario"><br>

<label>Área</label>
<input type="text" name="area"><br>

<label>Puesto</label>
<input type="text" name="puesto"><br>

<label>Correo</label>
<input type="text" name="correo"><br>

<label>Password</label>
<input type="password" name="password"><br>

<!-- 🔹 CAMBIO: ahora es turno_actual_id -->
<label>Turno</label>
<select name="turno_actual_id">
<option value="1">Matutino</option>
<option value="2">Vespertino</option>
<option value="3">Diurno</option>
</select><br>

<label>Estado</label>
<select name="estado">
<option value="Activo">Activo</option>
<option value="Inactivo">Inactivo</option>
</select><br>

<!-- 🔹 NUEVO: motivo de inactividad -->
<label>Motivo Inactividad</label>
<select name="motivo_inactivo_id">
<option value="">-- Ninguno --</option>
<option value="1">Vacaciones</option>
<option value="2">Permiso Personal</option>
<option value="3">IGSS</option>
<option value="4">Licencia Cumpleaños</option>
<option value="5">Suspensión Laboral</option>
<option value="6">Otros</option>
</select><br>

<label>Rol</label>
<select name="rol_id">
<option value="1">AdminRRHH</option>
<option value="2">AdminArea</option>
<option value="3">Empleado</option>
</select><br><br>

<button type="submit">Registrar</button>

</form>

<a href="dashboard.jsp">Regresar</a>

</body>
</html>
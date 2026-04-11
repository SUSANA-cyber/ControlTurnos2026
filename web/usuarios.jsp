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

<label>Turno</label>
<select name="turno">
<option value="Matutino">Matutino</option>
<option value="Vespertino">Vespertino</option>
<option value="Diurno">Diurno</option>
</select><br>

<label>Estado</label>
<select name="estado">
<option value="Activo">Activo</option>
<option value="Inactivo">Inactivo</option>
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
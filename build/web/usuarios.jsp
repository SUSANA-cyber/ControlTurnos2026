<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Usuarios</title>
<meta charset="UTF-8">

<style>
    body {
        margin: 0;
        font-family: Arial;
        background: radial-gradient(circle at top, #020617, #000000);
        color: #e0f7ff;
    }

    .container {
        max-width: 900px;
        margin: 50px auto;
        padding: 25px;
    }

    h1 {
        text-align: center;
        color: #00eaff;
        text-shadow: 0 0 15px #00eaff;
        margin-bottom: 20px;
    }

    .grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 30px;
    }

    .section-title {
        font-size: 16px;
        margin-bottom: 10px;
        color: #00eaff;
        border-bottom: 1px solid rgba(0, 234, 255, 0.4);
        padding-bottom: 5px;
    }

    label {
        display: block;
        margin-top: 15px;
        font-size: 13px;
        color: #67e8f9;
    }

    input, select {
        width: 100%;
        padding: 12px;
        margin-top: 5px;
        border-radius: 10px;
        border: 1px solid rgba(0, 234, 255, 0.2);
        background: #020617;
        color: #00eaff;
        transition: all 0.3s ease;
    }

    /* 🔥 EFECTO NEÓN */
    input:focus, select:focus {
        outline: none;
        background: #ffffff;
        color: #000000;
        border: 1px solid #00eaff;
        box-shadow: 
            0 0 10px #00eaff,
            0 0 20px #00eaff,
            0 0 30px rgba(0, 234, 255, 0.6);
    }

    button {
        margin-top: 30px;
        padding: 14px;
        width: 100%;
        background: linear-gradient(90deg, #00eaff, #0099cc);
        border: none;
        border-radius: 12px;
        font-weight: bold;
        color: black;
        cursor: pointer;
        transition: 0.3s;
    }

    button:hover {
        box-shadow: 0 0 20px #00eaff;
        transform: translateY(-2px);
    }

    .full {
        grid-column: span 2;
    }

    /* MENSAJES */
    .mensaje-ok {
        text-align: center;
        color: #00ffcc;
        margin-bottom: 15px;
        text-shadow: 0 0 10px #00ffcc;
    }

    .mensaje-error {
        text-align: center;
        color: #ff4d4d;
        margin-bottom: 15px;
        text-shadow: 0 0 10px #ff4d4d;
    }

    .link {
        text-align: center;
        margin-top: 20px;
    }

    .link a {
        color: #00eaff;
        text-decoration: none;
    }

</style>

<script>
function mostrarMotivo() {
    var estado = document.getElementById("estado").value;
    var divMotivo = document.getElementById("divMotivo");

    if (estado === "Inactivo") {
        divMotivo.style.display = "block";
    } else {
        divMotivo.style.display = "none";
    }
}
</script>

</head>

<body>

<div class="container">

<h1>Registrar Empleado</h1>

<!-- 🔥 MENSAJES -->
<%
if(request.getParameter("ok") != null){
%>
<div class="mensaje-ok">✔ Usuario registrado correctamente</div>
<%
}
%>

<%
if(request.getParameter("error") != null){
%>
<div class="mensaje-error">❌ Error al registrar usuario duplicado</div>
<%
}
%>

<form action="UsuarioServlet" method="post">

<div class="grid">

<!-- IZQUIERDA -->
<div>
<div class="section-title">Datos Personales</div>

<label>DPI</label>
<input type="text" name="dpi">

<label>Nombre</label>
<input type="text" name="nombre">

<label>Correo</label>
<input type="text" name="correo">

<label>Contraseña</label>
<input type="password" name="password">
</div>

<!-- DERECHA -->
<div>
<div class="section-title">Datos Laborales</div>

<label>Usuario</label>
<input type="text" name="usuario">

<label>Área</label>
<input type="text" name="area">

<label>Puesto</label>
<input type="text" name="puesto">

<label>Turno</label>
<select name="turno">
    <option value="Matutino">Matutino</option>
    <option value="Vespertino">Vespertino</option>
    <option value="Diurno">Diurno</option>
</select>

<label>Estado</label>
<select name="estado" id="estado" onchange="mostrarMotivo()">
    <option value="Activo">Activo</option>
    <option value="Inactivo">Inactivo</option>
</select>

<div id="divMotivo" style="display:none;">
    <label>Motivo</label>
    <select name="modo_inactivo">
        <option>Permiso Personal</option>
        <option>Vacaciones</option>
        <option>IGSS</option>
        <option>Licencia</option>
        <option>Suspensión Laboral</option>
        <option>Otros</option>
    </select>
</div>

</div>

<!-- BOTÓN -->
<div class="full">
<button type="submit">Registrar</button>
</div>

</div>

</form>

<div class="link">
<a href="dashboard.jsp">⬅ Regresar</a>
</div>

</div>

</body>
</html>
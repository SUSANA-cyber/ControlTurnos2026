<<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
Integer idUsuario = (Integer) session.getAttribute("id_usuario");

if (idUsuario == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Marcaje</title>

<style>
body {
    font-family: Arial;
    text-align: center;
}

.reloj {
    font-size: 40px;
    font-weight: bold;
    margin: 20px;
}

button {
    padding: 10px 20px;
    margin: 5px;
    background: #2d89ef;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

button:hover {
    opacity: 0.9;
}
</style>
</head>

<body>

<h2>Marcaje de Asistencia</h2>

<div class="reloj" id="reloj"></div>

<%
String msg = request.getParameter("msg");
if (msg != null) {
%>
<p style="color:red;"><b><%= msg %></b></p>
<%
}
%>

<form action="../MarcajeServlet" method="post">

    <button type="submit" name="accion" value="entrada">Entrada</button>
    <button type="submit" name="accion" value="descanso1">Descanso 1</button>
    <button type="submit" name="accion" value="descanso2">Descanso 2</button>
    <button type="submit" name="accion" value="salida">Salida</button>

</form>

<br>
<a href="dashboard.jsp">Regresar</a>

<script>
function actualizarReloj() {
    const ahora = new Date();

    let h = String(ahora.getHours()).padStart(2, '0');
    let m = String(ahora.getMinutes()).padStart(2, '0');
    let s = String(ahora.getSeconds()).padStart(2, '0');

    document.getElementById("reloj").innerHTML = h + ":" + m + ":" + s;
}

setInterval(actualizarReloj, 1000);
actualizarReloj();
</script>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="conexion.Conexion" %>

<!DOCTYPE html>
<html>
<head>
    <title>Asignación de Turnos</title>
    <meta charset="UTF-8">

    <!-- BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: linear-gradient(135deg, #0f2027, #203a43, #2c5364);
            min-height: 100vh;
            color: white;
        }

        .container-box {
            max-width: 900px;
            margin: auto;
            margin-top: 40px;
        }

        .card {
            background: rgba(0,0,0,0.75);
            border-radius: 15px;
            padding: 25px;
            box-shadow: 0 0 20px rgba(0,191,255,0.4);
        }

        h2, h3 {
            text-align: center;
            color: #00bfff;
            text-shadow: 0 0 10px #00bfff;
        }

        label {
            margin-top: 10px;
            font-weight: bold;
            color: #00e6ff;
            text-shadow: 0 0 5px #00e6ff;
        }

        .form-control, select {
            background: rgba(255,255,255,0.15);
            color: white;
            border: 1px solid #00bfff;
        }

        .form-control:focus, select:focus {
            box-shadow: 0 0 10px #00bfff;
        }

        button {
            margin-top: 15px;
            width: 100%;
            border: 2px solid #00bfff;
            background: transparent;
            color: #00bfff;
            padding: 10px;
            border-radius: 10px;
            font-weight: bold;
        }

        button:hover {
            background: #00bfff;
            color: black;
            box-shadow: 0 0 10px #00bfff;
        }

        table {
            margin-top: 20px;
            background: rgba(0,0,0,0.7);
        }

        th {
            background-color: #00bfff;
            color: black;
        }

        td, th {
            text-align: center;
            padding: 10px;
        }

        a {
            color: #00bfff;
            text-decoration: none;
            font-weight: bold;
        }

        .msg-ok {
            color: #00ff99;
            text-align: center;
        }

        .msg-error {
            color: red;
            text-align: center;
        }
    </style>
</head>

<body>

<div class="container-box">

<div class="card">

<h2>Asignación de Turnos</h2>

<form action="TurnoServlet" method="post">

<label>Empleado:</label>
<select name="id_usuario" class="form-control" required onchange="mostrarDatos(this)">
<option value="">Seleccione un empleado</option>

<%
Connection con = Conexion.getConexion();
String sql = "SELECT id, nombre, area, puesto FROM usuarios WHERE estado='Activo'";
PreparedStatement ps = con.prepareStatement(sql);
ResultSet rs = ps.executeQuery();

while(rs.next()){
%>

<option 
value="<%=rs.getInt("id")%>"
data-area="<%=rs.getString("area")%>"
data-puesto="<%=rs.getString("puesto")%>"
>
<%=rs.getString("nombre")%>
</option>

<%
}
%>

</select>

<br>

<label>Área:</label>
<input type="text" id="area" class="form-control" readonly>

<label>Puesto:</label>
<input type="text" id="puesto" class="form-control" readonly>

<br>

<label>Fecha inicio:</label>
<input type="date" name="fecha_inicio" class="form-control" required>

<label>Fecha fin:</label>
<input type="date" name="fecha_fin" class="form-control" required>

<br>

<label>Turno:</label>
<select name="turno" class="form-control" required>
<option value="">Seleccione</option>
<option value="Matutino">Matutino</option>
<option value="Vespertino">Vespertino</option>
<option value="Diurno">Diurno</option>
</select>

<button type="submit">Guardar</button>

</form>

<%
if(request.getParameter("ok") != null){
%>
<p class="msg-ok">Asignación creada con éxito</p>
<%
}
if("1".equals(request.getParameter("error"))){
%>
<p class="msg-error">Error en las fechas</p>
<%
}
if("duplicado".equals(request.getParameter("error"))){
%>
<p class="msg-error">El turno ya existe para ese empleado</p>
<%
}
%>

</div>

<div class="card">

<h3>Turnos asignados</h3>

<table class="table table-dark table-hover table-bordered">
<tr>
<th>Empleado</th>
<th>Área</th>
<th>Puesto</th>
<th>Inicio</th>
<th>Fin</th>
<th>Turno</th>
<th>Acciones</th>
</tr>

<%
String sql2 = "SELECT t.id, u.nombre, u.area, u.puesto, t.fecha_inicio, t.fecha_fin, t.turno " +
"FROM turnos t INNER JOIN usuarios u ON t.id_usuario = u.id";

PreparedStatement ps2 = con.prepareStatement(sql2);
ResultSet rs2 = ps2.executeQuery();

while(rs2.next()){
%>

<tr>
<td><%=rs2.getString("nombre")%></td>
<td><%=rs2.getString("area")%></td>
<td><%=rs2.getString("puesto")%></td>
<td><%=rs2.getString("fecha_inicio")%></td>
<td><%=rs2.getString("fecha_fin")%></td>
<td><%=rs2.getString("turno")%></td>
<td>
<a href="editarTurno.jsp?id=<%=rs2.getInt("id")%>">Editar</a>
|
<a href="EliminarTurnoServlet?id=<%=rs2.getInt("id")%>"
onclick="return confirm('¿Eliminar turno?')">Eliminar</a>
</td>
</tr>

<%
}

rs.close();
ps.close();
rs2.close();
ps2.close();
con.close();
%>

</table>

</div>

</div>

<script>
function mostrarDatos(select){
let option = select.options[select.selectedIndex];

document.getElementById("area").value = option.getAttribute("data-area") || "";
document.getElementById("puesto").value = option.getAttribute("data-puesto") || "";
}
</script>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="conexion.Conexion" %>

<%
request.setCharacterEncoding("UTF-8");

Integer idUsuario = (Integer) session.getAttribute("id_usuario");

if(idUsuario == null){
    response.sendRedirect("login.jsp");
    return;
}

Connection con = Conexion.getConexion();

// Último estado
String ultimo = "";

String sql = "SELECT * FROM marcajes WHERE usuario_id=? ORDER BY id DESC LIMIT 1";
PreparedStatement ps = con.prepareStatement(sql);
ps.setInt(1, idUsuario);
ResultSet rs = ps.executeQuery();

if(rs.next()){
    if(rs.getTime("hora_salida") != null) ultimo = "salida";
    else if(rs.getTime("hora_descanso2") != null) ultimo = "descanso2";
    else if(rs.getTime("hora_descanso1") != null) ultimo = "descanso1";
    else if(rs.getTime("hora_entrada") != null) ultimo = "entrada";
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
    padding: 20px;
}

.botones {
    display: flex;
    gap: 10px;
}

button {
    padding: 10px 20px;
    background: #2d89ef;
    color: white;
    border: none;
    border-radius: 5px;
}

button:disabled {
    background: gray;
}
</style>
</head>

<body>

<h2>Marcaje de Asistencia</h2>

<%
String msg = request.getParameter("msg");
if(msg != null){
%>
<p style="color:green;"><b><%= msg %></b></p>
<%
}
%>

<form action="<%=request.getContextPath()%>/MarcajeServlet" method="post" class="botones">

<button name="accion" value="entrada"
<%= (!ultimo.equals("") && !ultimo.equals("salida")) ? "disabled" : "" %>>
Entrada
</button>

<button name="accion" value="descanso1"
<%= (!ultimo.equals("entrada")) ? "disabled" : "" %>>
Descanso 1
</button>

<button name="accion" value="descanso2"
<%= (!ultimo.equals("descanso1")) ? "disabled" : "" %>>
Descanso 2
</button>

<button name="accion" value="salida"
<%= (!ultimo.equals("descanso2")) ? "disabled" : "" %>>
Salida
</button>

</form>

<br>
<a href="dashboard.jsp">Regresar</a>

</body>
</html>

<%
rs.close();
ps.close();
con.close();
%>
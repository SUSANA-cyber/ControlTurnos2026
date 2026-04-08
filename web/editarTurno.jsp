<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="conexion.Conexion" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));

    Connection con = Conexion.getConexion();
    String sql = "SELECT * FROM turnos WHERE id=?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, id);

    ResultSet rs = ps.executeQuery();
    rs.next();
%>

<h2>Editar Turno</h2>

<form action="ActualizarTurnoServlet" method="post">

    <input type="hidden" name="id" value="<%=id%>">

    Fecha inicio:
    <input type="date" name="fecha_inicio" value="<%=rs.getString("fecha_inicio")%>">

    <br><br>

    Fecha fin:
    <input type="date" name="fecha_fin" value="<%=rs.getString("fecha_fin")%>">

    <br><br>

    Turno:
    <input type="text" name="turno" value="<%=rs.getString("turno")%>">

    <br><br>

    <button type="submit">Actualizar</button>

</form>

<%
    rs.close();
    ps.close();
    con.close();
%>

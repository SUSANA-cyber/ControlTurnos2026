package controlador;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import conexion.Conexion;

@WebServlet(name = "TurnoServlet", urlPatterns = {"/TurnoServlet"})
public class TurnoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
        String fecha_inicio = request.getParameter("fecha_inicio");
        String fecha_fin = request.getParameter("fecha_fin");
        String turno = request.getParameter("turno");

        try {
            Connection con = Conexion.getConexion();

            // 🔥 VALIDAR FECHAS
            if (fecha_inicio.compareTo(fecha_fin) > 0) {
                response.sendRedirect("turnos.jsp?error=1");
                return;
            }

            // 🔥 VALIDAR DUPLICADO
            String validar = "SELECT * FROM turnos WHERE id_usuario=? AND fecha_inicio=? AND fecha_fin=?";
            PreparedStatement psVal = con.prepareStatement(validar);
            psVal.setInt(1, id_usuario);
            psVal.setString(2, fecha_inicio);
            psVal.setString(3, fecha_fin);
            ResultSet rs = psVal.executeQuery();

            if (rs.next()) {
                response.sendRedirect("turnos.jsp?error=duplicado");
                return;
            }

            // 🔥 INSERTAR
            String sql = "INSERT INTO turnos (id_usuario, fecha_inicio, fecha_fin, turno) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id_usuario);
            ps.setString(2, fecha_inicio);
            ps.setString(3, fecha_fin);
            ps.setString(4, turno);

            ps.executeUpdate();

            response.sendRedirect("turnos.jsp?ok=1");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("ERROR: " + e.getMessage());
        }
    }
}

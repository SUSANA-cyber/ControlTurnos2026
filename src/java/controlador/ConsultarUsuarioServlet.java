package controlador;

import conexion.Conexion;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ConsultarUsuarioServlet")
public class ConsultarUsuarioServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String buscar = req.getParameter("buscar");

        if (buscar == null) {
            buscar = "";
        }

        List<String[]> listaUsuarios = new ArrayList<>();

        try {
            Connection con = Conexion.getConexion();

            String sql = "SELECT usuario, area, estado FROM usuarios WHERE usuario LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + buscar + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] datos = new String[3];
                datos[0] = rs.getString("usuario");
                datos[1] = rs.getString("area");
                datos[2] = rs.getString("estado");

                listaUsuarios.add(datos);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 🔥 Enviar datos al JSP
        req.setAttribute("listaUsuarios", listaUsuarios);

        // 🔥 IMPORTANTE: usar forward (NO redirect)
        req.getRequestDispatcher("consultarUsuarios.jsp").forward(req, res);
    }
}
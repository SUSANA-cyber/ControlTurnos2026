package controlador;

import conexion.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String usuario = req.getParameter("usuario");
        String password = req.getParameter("password");

        System.out.println("Usuario: " + usuario);
        System.out.println("Password: " + password);

        try {
            Connection con = Conexion.getConexion();

            String sql = "SELECT * FROM usuarios WHERE usuario=? AND estado='Activo'";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String passBD = rs.getString("password");

                if (passBD.equals(password)) {
                    HttpSession sesion = req.getSession();
                    sesion.setAttribute("usuario", usuario);

                    System.out.println("LOGIN CORRECTO");
                    res.sendRedirect("dashboard.jsp");
                } else {
                    System.out.println("PASSWORD INCORRECTO");
                    res.sendRedirect("login.jsp?error=1");
                }
            } else {
                System.out.println("USUARIO NO EXISTE");
                res.sendRedirect("login.jsp?error=1");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("login.jsp?error=2");
        }
    }
}
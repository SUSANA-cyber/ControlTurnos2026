package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3308/control_turnos?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConexion() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Conectado a la base de datos");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Error: Driver no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión");
            e.printStackTrace();
        }

        return con;
    }
}
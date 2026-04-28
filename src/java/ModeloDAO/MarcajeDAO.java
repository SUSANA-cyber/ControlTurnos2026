package ModeloDAO;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

public class MarcajeDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // VERIFICAR SI EXISTE MARCAJE HOY
    public ResultSet obtenerMarcajeHoy(int idUsuario) {
        try {
            con = Conexion.getConexion();

            String sql =
                "SELECT * FROM marcajes " +
                "WHERE usuario_id=? AND fecha=CURDATE()";

            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);

            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    // INSERTAR ENTRADA
    public void insertarEntrada(int idUsuario, Time ahora) {
        try {
            con = Conexion.getConexion();

            String sql =
                "INSERT INTO marcajes " +
                "(usuario_id, fecha, hora_entrada) " +
                "VALUES (?, CURDATE(), ?)";

            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setTime(2, ahora);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ACTUALIZAR DESCANSO 1
    public void actualizarDescanso1(int idUsuario, Time ahora) {
        try {
            con = Conexion.getConexion();

            String sql =
                "UPDATE marcajes " +
                "SET hora_descanso1=? " +
                "WHERE usuario_id=? AND fecha=CURDATE()";

            ps = con.prepareStatement(sql);
            ps.setTime(1, ahora);
            ps.setInt(2, idUsuario);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ACTUALIZAR DESCANSO 2
    public void actualizarDescanso2(int idUsuario, Time ahora) {
        try {
            con = Conexion.getConexion();

            String sql =
                "UPDATE marcajes " +
                "SET hora_descanso2=? " +
                "WHERE usuario_id=? AND fecha=CURDATE()";

            ps = con.prepareStatement(sql);
            ps.setTime(1, ahora);
            ps.setInt(2, idUsuario);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ACTUALIZAR SALIDA
    public void actualizarSalida(int idUsuario, Time ahora) {
        try {
            con = Conexion.getConexion();

            String sql =
                "UPDATE marcajes " +
                "SET hora_salida=? " +
                "WHERE usuario_id=? AND fecha=CURDATE()";

            ps = con.prepareStatement(sql);
            ps.setTime(1, ahora);
            ps.setInt(2, idUsuario);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // BITÁCORA
    public void insertarBitacora(int idUsuario, String accion, String mensaje) {
        try {
            con = Conexion.getConexion();

            String sql =
                "INSERT INTO bitacora " +
                "(usuario_id, modulo, tipo_operacion, descripcion, fecha_hora) " +
                "VALUES (?, 'Marcaje', ?, ?, NOW())";

            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setString(2, accion);
            ps.setString(3, mensaje);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
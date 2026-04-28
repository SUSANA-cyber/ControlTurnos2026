package ModeloDAO;

import Config.Conexion;
import Modelo.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TurnoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // VALIDAR DUPLICADO
    public boolean existeTurno(int id_usuario, String fecha_inicio, String fecha_fin) {

        boolean existe = false;

        String sql =
            "SELECT * FROM turnos " +
            "WHERE id_usuario=? AND fecha_inicio=? AND fecha_fin=?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id_usuario);
            ps.setString(2, fecha_inicio);
            ps.setString(3, fecha_fin);

            rs = ps.executeQuery();

            if (rs.next()) {
                existe = true;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return existe;
    }

    // INSERTAR TURNO
    public boolean agregarTurno(Turno t) {

        boolean resultado = false;

        String sql =
            "INSERT INTO turnos " +
            "(id_usuario, fecha_inicio, fecha_fin, turno) " +
            "VALUES (?, ?, ?, ?)";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, t.getId_usuario());
            ps.setString(2, t.getFecha_inicio());
            ps.setString(3, t.getFecha_fin());
            ps.setString(4, t.getTurno());

            int r = ps.executeUpdate();

            if (r > 0) {
                resultado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

    // ACTUALIZAR TURNO
    public boolean actualizarTurno(int id, String inicio, String fin, int turno_id) {

        boolean resultado = false;

        String sql =
            "UPDATE asignacion_turnos " +
            "SET fecha_inicio=?, fecha_fin=?, turno_id=? " +
            "WHERE id=?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, inicio);
            ps.setString(2, fin);
            ps.setInt(3, turno_id);
            ps.setInt(4, id);

            int r = ps.executeUpdate();

            if (r > 0) {
                resultado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

    // ELIMINAR TURNO
    public boolean eliminarTurno(int id) {

        boolean resultado = false;

        String sql =
            "DELETE FROM asignacion_turnos WHERE id=?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int r = ps.executeUpdate();

            if (r > 0) {
                resultado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }
}
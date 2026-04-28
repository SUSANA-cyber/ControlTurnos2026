package ModeloDAO;

import Config.Conexion;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // 🔹 LOGIN
    public ResultSet login(String usuario) {

        try {
            con = Conexion.getConexion();

            String sql = "SELECT u.id, u.password, r.nombre AS rol " +
                         "FROM usuarios u " +
                         "LEFT JOIN roles r ON u.rol_id = r.id " +
                         "WHERE u.usuario=? AND u.estado='Activo'";

            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);

            rs = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    // 🔹 VALIDAR USUARIO
    public boolean existeUsuario(String usuario) {
        boolean existe = false;
        String sql = "SELECT * FROM usuarios WHERE usuario=?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                existe = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return existe;
    }

    // 🔹 INSERTAR
    public boolean agregarUsuario(Usuario u) {
        boolean resultado = false;

        String sql = "INSERT INTO usuarios "
                + "(dpi, nombre, usuario, area, correo, password, estado, motivo_inactivo_id, puesto, turno_actual_id, rol_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, u.getDpi());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getUsuario());
            ps.setString(4, u.getArea());
            ps.setString(5, u.getCorreo());
            ps.setString(6, u.getPassword());
            ps.setString(7, u.getEstado());

            if (u.getMotivo_inactivo_id() != null) {
                ps.setInt(8, u.getMotivo_inactivo_id());
            } else {
                ps.setNull(8, java.sql.Types.INTEGER);
            }

            ps.setString(9, u.getPuesto());
            ps.setInt(10, u.getTurno_actual_id());
            ps.setInt(11, u.getRol_id());

            int r = ps.executeUpdate();

            if (r > 0) resultado = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // 🔹 CONSULTAR
    public List<String[]> listarUsuarios(String buscar) {

        List<String[]> lista = new ArrayList<>();

        String sql = "SELECT usuario, area, estado FROM usuarios WHERE usuario LIKE ?";

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + buscar + "%");

            rs = ps.executeQuery();

            while (rs.next()) {
                String[] datos = new String[3];
                datos[0] = rs.getString("usuario");
                datos[1] = rs.getString("area");
                datos[2] = rs.getString("estado");

                lista.add(datos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
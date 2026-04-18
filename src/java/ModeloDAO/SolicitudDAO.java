/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;
import Modelo.ModeloUsuario;
import conexion.Conexion;
import Modelo.MSolicitudes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
/**
 *
 * @author Acer
 */
public class SolicitudDAO {
    
   Connection con;
   PreparedStatement ps;
   
   
   public boolean AgregarSolicitud(MSolicitudes sol){
       String sql="Insert INTO solicitudes(usuario_id,tipo,fecha_inicio,fecha_fin,motivo,estado)VALUES(?,?,?,?,?,'pendiente')";
       try{
           con=Conexion.getConexion();
           ps=con.prepareStatement(sql);
           
           
           ps.setInt(1, sol.getUsuario_id());
           ps.setString(2, sol.getTipo());
           ps.setString(3,sol.getFecha_inicio());
           ps.setString(4,sol.getFecha_fin());
           ps.setString(5,sol.getMotivo());
           
           
           ps.executeUpdate();
           return true;
       
       }catch(Exception e ){
           System.out.println("Error en la solicitud"+e);
           return false;
       
       }
   
  
   }
   
   
   
    public boolean ActualizarEstado(int id, String nuevoEstado) {
        // Asumiendo que la llave primaria de tu tabla solicitudes se llama 'id'
        String sql = "UPDATE solicitudes SET estado = ? WHERE id = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, nuevoEstado); 
            ps.setInt(2, id);            
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar estado en Solicitudes: " + e);
            return false;
        }
    }
   
   
}

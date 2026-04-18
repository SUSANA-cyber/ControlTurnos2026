/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloDAO;
import Modelo.MTurno;
import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 *
 * @author Acer
 */
public class TurnoDAO {
    
    Connection con;
    PreparedStatement ps;
    
    public boolean CambiarTurno(MTurno mt){
        String sql="Insert INTO turnos(id_usuario,fecha_inicio,TurnoInicial,NuevoTurno,Nuevafecha,Motivo)VALUES(?,?,?,?,?,?)";
       
        
        try{
        con=Conexion.getConexion();
        ps=con.prepareStatement(sql);
        
        ps.setInt(1,mt.getId_usuario());
        ps.setString(2,mt.getFecha_inico());
        ps.setString(3,mt.getTurnoInicial());
        ps.setString(4,mt.getNuevoTurno());
        ps.setString(5,mt.getNuevafecha());
        ps.setString(6,mt.getMotivo());
        
        
        
        
        ps.executeUpdate();
        return true;
        
        }catch(Exception e){
            System.out.println("error en la solicitud"+ e);
            
        return false;
        }                
    } 
    
    
    
    public boolean ActualizarEstado(int id, String nuevoEstado) {
      
        String sql = "UPDATE turnos SET estado = ? WHERE id = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, nuevoEstado); 
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar estado en Turnos: " + e);
            return false;
        }
    }
    
}

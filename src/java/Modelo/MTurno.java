/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Acer
 */
public class MTurno {
   private int id;
   private int id_usuario;
   private String fecha_inico;
   private String TurnoInicial;
   private String NuevoTurno;
   private String Nuevafecha;
   private String Motivo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha_inico() {
        return fecha_inico;
    }

    public void setFecha_inico(String fecha_inico) {
        this.fecha_inico = fecha_inico;
    }

    public String getTurnoInicial() {
        return TurnoInicial;
    }

    public void setTurnoInicial(String TurnoInicial) {
        this.TurnoInicial = TurnoInicial;
    }

    public String getNuevoTurno() {
        return NuevoTurno;
    }

    public void setNuevoTurno(String NuevoTurno) {
        this.NuevoTurno = NuevoTurno;
    }

    public String getNuevafecha() {
        return Nuevafecha;
    }

    public void setNuevafecha(String Nuevafecha) {
        this.Nuevafecha = Nuevafecha;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }
     
    
    
}

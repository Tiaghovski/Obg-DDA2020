/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoras;

import dominio.Configuracion;
import dominio.EstadoPartida;
import dominio.Partida;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public class GestoraPartidas {
    private ArrayList<Partida> colPartidas;
    private static GestoraPartidas instancia = null;
    private static Configuracion config;
    
    public static GestoraPartidas getInstance(){
        if(instancia == null){
            return new GestoraPartidas();
        }
        return instancia;
    }
    
    public GestoraPartidas(){
        colPartidas = new ArrayList<>();
    }
    
    public void agregarPartida(Partida p) {
        colPartidas.add(p);
    }

    public void setConfig(Configuracion configuracion) {
        for (Partida p : colPartidas) {
          p.setConfig(configuracion);
          config = configuracion;
        }
    }

    public Partida getPartida() {
        if(colPartidas.isEmpty()) {
            Partida nuevaP = new Partida();
            nuevaP.setEstado(EstadoPartida.En_Espera);
            colPartidas.add(nuevaP);
            setConfig(config);
        }
        for (Partida p : colPartidas) {
            return p;
        }
        return null;
    }

    public void nuevaPartida() {
        try {
            if(colPartidas.isEmpty()) {
                return;
            }
            for (Partida p : colPartidas) {
                if(p.getEstado() == EstadoPartida.Finalizada) {
                    colPartidas.remove(p);
                }
            }
            Partida nuevaP = new Partida();
            nuevaP.setEstado(EstadoPartida.Iniciada);
            colPartidas.add(nuevaP);
        } catch (Exception e) {
        }
    }
    
    
}

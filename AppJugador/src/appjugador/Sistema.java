/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjugador;

import dominio.Configuracion;
import dominio.Jugador;
import servicio.IFachadaRemota;

/**
 *
 * @author Santiago
 */
public class Sistema {
    private static IFachadaRemota fachada;
    private static Jugador jugador;
    private static Configuracion config;
    
    
    private Sistema(){
        
    }

    public static IFachadaRemota getFachada() {
        return fachada;
    }

    public static void setFachada(IFachadaRemota fachada) {
        Sistema.fachada = fachada;
    }

    public static Jugador getJugador() {
        return jugador;
    }

    public static void setJugador(Jugador jugador) {
        Sistema.jugador = jugador;
    }

    public static Configuracion getConfig() {
        return config;
    }

    public static void setConfig(Configuracion config) {
        Sistema.config = config;
    }   
    
    
    
    
}

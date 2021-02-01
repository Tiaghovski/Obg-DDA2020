/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjugador.controllers.partida;


import dominio.Jugador;
import dominio.TipoFigura;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public interface UIPartida {
    void mostrarCantJugadores(int n);
    
    void listarFiguras(ArrayList<TipoFigura> f);  
    
    void listarCartones(Integer[][] cartones);
    
    void listarJugadores(ArrayList<Jugador> jugadores);
    
    void partidaIniciada();
    
    void mostrarUltimaBolillaSorteada(int n);
    
    void preguntarContinuidad();
    
    void mostrarPozo(int n); 
    
    void cerrarVentana();
    
    void ocultarPanelContinuar();
    
    void mostrarSaldoJugador(int n);
    
    void mostrarSaldoActual(int n);
    
    void mostrarGanador(String nombre);
    
    void mostrarPanelGanador();
    
    void ocultarEsperando();      
   
    
  
    
    
}

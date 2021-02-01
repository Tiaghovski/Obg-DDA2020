/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import dominio.Configuracion;
import dominio.Jugador;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicio.Evento;



/**
 *
 * @author Santiago
 */
public class ThreadSimple implements Runnable {
    
    private Thread miThread = null;
    private Jugador jugador;
    private Fachada fachada;
    private boolean activado;
    private boolean parado;
    private Configuracion config;

    public ThreadSimple(Jugador jugador, Fachada fachada, Configuracion config) {
        this.jugador = jugador;
        this.fachada = fachada;
        this.activado = true;
        this.parado = false;
        this.config = config;
    }  
    
    public void iniciar(){
        miThread = new Thread(this);
        miThread.start();
     }  
    
    public void parar(){
        setActivado(false);
        setParado(true);
        this.fachada.notifyObserver(jugador, Evento.Continuar);        
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public boolean isParado() {
        return parado;
    }

    public void setParado(boolean parado) {
        this.parado = parado;
    }
    
    
    
    

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
     
    

    @Override
    public void run() {
        while(activado){
            try {
            miThread.sleep(config.getTiempoEspera() * 1000);   
            setActivado(false);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadSimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        if(!isParado()){
          this.fachada.notifyObserver(jugador, Evento.No_Continuar);
            try {
                fachada.borrarHilo(jugador);              
            } catch (RemoteException ex) {
                Logger.getLogger(ThreadSimple.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
    
        
    }

  
    
}

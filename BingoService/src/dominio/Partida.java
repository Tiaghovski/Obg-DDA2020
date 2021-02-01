/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Santiago
 */
public class Partida implements Serializable {
    private Configuracion config;
    private ArrayList<Jugador> colJugadores;    
    private EstadoPartida estado;  
    private Bolillero bolillero;
    private int ultimaBolillaSorteada;
    private int pozo;
    private int cantCartonesIniciales;    
 

    public Partida(Configuracion config, EstadoPartida estado, Bolillero bolillero, int pozo) {
        this.config = config;        
        this.estado = estado;      
        this.bolillero = bolillero;
        this.pozo = pozo;
        colJugadores = new ArrayList<>();     
    }

    public Partida(){
        colJugadores = new ArrayList<>();        
    }

    

    public EstadoPartida getEstado() {
        return estado;
    }

    public void setEstado(EstadoPartida estado) {
        this.estado = estado;
    }   

    public Configuracion getConfig() {
        return config;
    }

    public void setConfig(Configuracion config) {
        this.config = config;
    }    

    public ArrayList<Jugador> getColJugadores() {
        return colJugadores;
    }

    public void setColJugadores(ArrayList<Jugador> colJugadores) {
        this.colJugadores = colJugadores;
    }

    public Bolillero getBolillero() {
        return bolillero;
    }

    public void setBolillero(Bolillero bolillero) {
        this.bolillero = bolillero;
    }

    public int getUltimaBolillaSorteada() {
        return ultimaBolillaSorteada;
    }

    public void setUltimaBolillaSorteada(int ultimaBolillaSorteada) {
        this.ultimaBolillaSorteada = ultimaBolillaSorteada;
    }

    public int getPozo() {
        return pozo;
    }

    public void setPozo(int pozo) {
        this.pozo = pozo;
    }   

    public int getCantCartonesIniciales() {
        return cantCartonesIniciales;
    }

    public void setCantCartonesIniciales(int cantCartonesIniciales) {
        this.cantCartonesIniciales = cantCartonesIniciales;
    }
    
    
    
     public void agregarJugador(Jugador j) {       
             colJugadores.add(j);              
     } 
     
     public Usuario buscarJugadorPorNombreUsuario(String nomUsu){         
         for(Jugador j : colJugadores){
             Usuario user = (Usuario) j;
             if(user.getNombreUsuario().equals(nomUsu)){
                 return user;
             }
         }
         return null;
     }     
     
     public void eliminarJugador(Jugador j){
         colJugadores.remove(this.buscarJugadorPorNombreUsuario(j.getNombreUsuario()));
         ArrayList<Jugador> jugadores = getColJugadores();
         ArrayList<Integer> nuevoBolillero = new ArrayList<Integer>();

         for (Jugador ju : jugadores) {
             ArrayList<Carton> cartones = ju.getColCartones();
             for (Carton c : cartones) {
                 nuevoBolillero.addAll(c.getNumerosCarton());
             }
         }
         getBolillero().setNumerosBolillero(nuevoBolillero);
     }    
  
     
     public void empezarPartida(){
         setEstado(EstadoPartida.Iniciada);
         bolillero = new Bolillero();         
         calcularCantCartonesIniciales();
         calcularValorPozo();
     }
     
     public void finalizarPartida(){    
         setEstado(EstadoPartida.Finalizada);           
     }
     
     public int calcularValorPozo(){
         int valorPozo = 0;
         int cantCartonesJugadorenPartida = 0;
         int inicialesPorValorCarton = getCantCartonesIniciales() * config.getValorCarton();
         for(Jugador j : colJugadores){
             cantCartonesJugadorenPartida += j.getColCartones().size();             
         }
        valorPozo = inicialesPorValorCarton + (cantCartonesJugadorenPartida * config.getValorCarton());
        setPozo(valorPozo);
        return valorPozo;
     }
     
     public void calcularCantCartonesIniciales(){         
         for(Jugador j : colJugadores){
             cantCartonesIniciales += j.getColCartones().size();
         }
         setCantCartonesIniciales(cantCartonesIniciales);
     }

     
     public void repartirNumeros(){        
         ArrayList<Integer> numBolillero = (ArrayList<Integer>) bolillero.getNumerosBolillero().clone();
         int x = bolillero.getNumerosBolillero().size() / cantCartonesIniciales;
         for(Jugador j : colJugadores){
             for(Object c : j.getColCartones()){
                 ArrayList<Integer> numCarton = new ArrayList<>();
                 for(int i= 0; i < x; i ++){
                     numCarton.add(numBolillero.get(i));                     
                 }                
                 ((Carton)c).setNumerosCarton(numCarton);                
                 ArrayList<Integer> aux = new ArrayList<Integer>(numBolillero.subList(0, x));
                 numBolillero.removeAll(aux);
             }
         }
         Collections.shuffle(bolillero.getNumerosBolillero());        
     }
     
     public Jugador comprobarVictoria() {
        for (Jugador j : colJugadores) {
            for (TipoFigura f : config.getColFiguras()) {
                if(f.comprobarVictoria(j, bolillero, config)) {
                    return j;
                }
            }
        }
        return null;
    }
     
     
   
       
      
     
     
        
     

     
     
     
        
        
    
}

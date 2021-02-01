/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Santiago
 */
public class Configuracion implements Serializable {
    private int cantFilas;
    private int cantColumnas;
    private int cantJugadores;
    private int cartonesPorJugador;
    private int valorCarton;
    private ArrayList<TipoFigura> colFiguras;
    private int tiempoEspera;
   

    public Configuracion(int cantFilas, int cantColumnas, int cantJugadores, int cartonesPorJugador, int valorCarton, int tiempoEspera) {
        this.cantFilas = cantFilas;
        this.cantColumnas = cantColumnas;
        this.cantJugadores = cantJugadores;
        this.cartonesPorJugador = cartonesPorJugador;
        this.valorCarton = valorCarton; 
        this.colFiguras = new ArrayList<>();
        this.tiempoEspera = tiempoEspera;
        cargarFiguras();
    }
    
    public Configuracion(){       
       colFiguras = new ArrayList<>();
       cargarFiguras();
    }   

    public int getCantFilas() {
        return cantFilas;
    }

    public void setCantFilas(int cantFilas) {
        this.cantFilas = cantFilas;
    }

    public int getCantColumnas() {
        return cantColumnas;
    }

    public void setCantColumnas(int cantColumnas) {
        this.cantColumnas = cantColumnas;
    }

    public int getCantJugadores() {
        return cantJugadores;
    }

    public void setCantJugadores(int cantJugadores) {
        this.cantJugadores = cantJugadores;
    }

    public int getCartonesPorJugador() {
        return cartonesPorJugador;
    }

    public void setCartonesPorJugador(int cartonesPorJugador) {
        this.cartonesPorJugador = cartonesPorJugador;
    }

    public int getValorCarton() {
        return valorCarton;
    }

    public void setValorCarton(int valorCarton) {
        this.valorCarton = valorCarton;
    }  

    public ArrayList<TipoFigura> getColFiguras() {
        return colFiguras;
    }

    public void setColFiguras(ArrayList<TipoFigura> colFiguras) {
        this.colFiguras = colFiguras;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }  
    
    

    @Override
    public String toString() {
        return "Filas: " + cantFilas + ", Columnas: " + cantColumnas + ", Jugadores: " + cantJugadores + ", Cartones: " + cartonesPorJugador + ", Valor Cartón: " + valorCarton + ", Tiempo espera: " + tiempoEspera;
    }
    
    public void cargarFiguras(){
        CartonLleno cartonLleno = new CartonLleno();
        colFiguras.add((TipoFigura) cartonLleno);
          
    }
    
    public void agregarFigura(TipoFigura tf){
        colFiguras.add(tf);
    }
    
    public void eliminarFigura(TipoFigura tf){
        colFiguras.remove(tf);
    }
    
   
    
    
    
    
    
    
}

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
public class Jugador extends Usuario implements Serializable {
    private int saldo;
    private ArrayList<Carton> colCartones;
  

    public Jugador(int saldo, String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena);
        this.saldo = saldo;  
        this.colCartones = new ArrayList<>();        
    }           
    

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Carton> getColCartones() {
        return colCartones;
    }

    public void setColCartones(ArrayList<Carton> colCartones) {
        this.colCartones = colCartones;
    }
    
    public void agregarCartones(Carton c){
        colCartones.add(c);
    }
    
    public boolean comprarCartones(int n, int valorCarton){         
        int dobleDelCosto = (n * valorCarton) * 2;
        if(saldo < dobleDelCosto) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            agregarCartones(new Carton());
        }
        return true;
    }

    public void repartirBollillas(ArrayList<Integer> bolillasJugador) {
        int i, j, chunk = colCartones.size();
        ArrayList<Integer> bolillasCarton = new ArrayList<Integer>();
        for (i = 0, j = bolillasJugador.size(); i < j; i += chunk) {
            bolillasCarton = getSliceOfArray(bolillasJugador, i, i + chunk); 
            colCartones.get(i).setNumerosCarton(bolillasJugador);
        }
    }
    
      public ArrayList<Integer> getSliceOfArray(ArrayList<Integer> arr, int start, int end){
        int num = end - start;        
        ArrayList<Integer> slice = new ArrayList<Integer>(num); 
        for (int i = 0; i < slice.size(); i++) {           
            slice.add(i, arr.get(start + i));                     
        }       
        return slice; 
    }

    @Override
    public String toString() {
        return "Jugador: " + super.getNombreUsuario();
    }
    
      
     
    

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoras;

import dominio.Configuracion;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public class GestoraConfiguracion implements Serializable {
    private ArrayList<Configuracion> colConfiguraciones;
    private static GestoraConfiguracion instancia = null;
    
    public static GestoraConfiguracion getInstance(){
        if(instancia == null){
            return new GestoraConfiguracion();
        }
        return instancia;
    }

    public GestoraConfiguracion() {
        colConfiguraciones = new ArrayList<>();
        cargarConfiguracion();
    }
    
    public void cargarConfiguracion(){
        Configuracion config = new Configuracion(3, 2, 3, 2, 10, 20);
        colConfiguraciones.add(config);
    }
    
    public ArrayList<Configuracion> obtenerConfiguraciones(){
        return colConfiguraciones;
    }
    
    public void guardarConfiguracion(int a, int b, int cd, int d, int e, int f){
        for(Configuracion c : colConfiguraciones){
            c.setCantFilas(a);
            c.setCantColumnas(b);
            c.setCantJugadores(cd);
            c.setCartonesPorJugador(d);
            c.setValorCarton(e);   
            c.setTiempoEspera(f);
        }        
    }    
   
    
  
    
    
}

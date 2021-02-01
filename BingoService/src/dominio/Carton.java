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
public class Carton implements Serializable { 
    private Configuracion config;
    private ArrayList<Integer> numerosCarton;
    private Integer[][] matriz;

    public Carton(Configuracion config) {       
        this.config = config;
        this.numerosCarton = new ArrayList<Integer>();
    }

    public Carton(){
        numerosCarton = new ArrayList<Integer>();        
    }
    
    public Integer[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Integer[][] matriz) {
        this.matriz = matriz;
    }
   

    public Configuracion getConfig() {
        return config;
    }

    public void setConfig(Configuracion config) {
        this.config = config;
    }

    public ArrayList<Integer> getNumerosCarton() {
        return numerosCarton;
    }

    public void setNumerosCarton(ArrayList<Integer> numerosCarton) {
        this.numerosCarton = numerosCarton;
    }
    
    
    
    
}

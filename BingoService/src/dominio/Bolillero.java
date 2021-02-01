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
public class Bolillero implements Serializable {
    private ArrayList<Integer> numerosBolillero;
    private ArrayList<Integer> bolillasSorteadas;

    public Bolillero() {
        numerosBolillero = new ArrayList<Integer>();
        bolillasSorteadas = new ArrayList<Integer>();
        Collections.shuffle(numerosBolillero);
    }

    public ArrayList<Integer> getBolillasSorteadas() {
        return bolillasSorteadas;
    }

    public void setBolillasSorteadas(ArrayList<Integer> bolillasSorteadas) {
        this.bolillasSorteadas = bolillasSorteadas;
    }
    
    public ArrayList<Integer> getNumerosBolillero() {
        return numerosBolillero;
    }

    public void setNumerosBolillero(ArrayList<Integer> numerosBolillero) {
        this.numerosBolillero = numerosBolillero;
        Collections.shuffle(numerosBolillero);
    }
    
    public int sortearBolilla(){       
        if(numerosBolillero.size() > 0){
            int bolilla = numerosBolillero.get(numerosBolillero.size() -1);           
            numerosBolillero.remove(numerosBolillero.size() -1);
            bolillasSorteadas.add(bolilla);
            return bolilla;
        }
        return 999;    
    }
    
   
    

    
    
    
}

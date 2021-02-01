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
public class Lineal implements TipoFigura, Serializable {
    

    @Override
    public String toString() {
        return "Lineal";
    }

    @Override
    public boolean comprobarVictoria(Jugador jugador, Bolillero bolillero, Configuracion c) {
       Carton primerCartonJugador = jugador.getColCartones().get(0);
        Integer[][] matriz = primerCartonJugador.getMatriz();

        int filasCarton = c.getCantFilas();
        int colsCarton = c.getCantColumnas();
        Integer[][] matrizCarton = new Integer[filasCarton][colsCarton];

        ArrayList<Integer> bolillasEnCarton = new ArrayList<>();
        for (int i = 0; i < matrizCarton.length; i++) {
            for (int j = 0; j < matrizCarton[i].length; j++) {

                if(matriz[i][j] != null) {
                    int bolilla = matriz[i][j];
                    bolillasEnCarton.add(bolilla);
                }
            }
        }
        ArrayList<Integer> bolillasQueSalieron = bolillero.getBolillasSorteadas();

        ArrayList<Integer> filas = new ArrayList<>();
        for (int i = 0; i < matrizCarton.length; i++) {

//            if(bolillasQueSalieron.contains(matriz[i][i])) {
//                    filas.add(matriz[i][i]);
//                }

            ArrayList<Integer> cols = new ArrayList<>();
            for (int j = 0; j < matrizCarton[i].length; j++) {

                if(bolillasQueSalieron.contains(matriz[i][j])) {
                    cols.add(matriz[i][j]);
                }
            }
            if(cols.size() == matrizCarton[i].length) {
                return true;
            }
        }
        if(filas.size() == matrizCarton.length) {
            return true;
        }
        return false;
    }
    
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appadministrador.controllers.configuracion;

import dominio.Configuracion;
import dominio.TipoFigura;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public interface UIConfiguracion {
    
    void listarConfiguraciones(ArrayList<Configuracion> c);
    
    void listarFiguras(ArrayList<TipoFigura> f);   
    
    int getCantColumnas();
    
    int getCantFilas();
    
    int getCantJugadores();
    
    int getCartonesPorJugador();
    
    int getValorCarton();
    
    int getTiempoEspera();
    
    boolean getLineal(); 
    
    boolean getDiagonal();    
    
    boolean getCentro(); 
    
    void LinealActivado();
    
    void DiagonalActivado();
    
    void CentroActivado();
    
    void setCantColumnas(int n);
    
    void setCantFilas(int n);
    
    void setCantJugadores(int n);
    
    void setCantCartonesJugador(int n);
    
    void setValorCarton(int n);
    
    void setTiempoEspera(int n);
    
    
    
    
   
   
  
}

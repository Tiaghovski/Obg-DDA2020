/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appadministrador.controllers.configuracion;

import dominio.Centro;
import dominio.Configuracion;
import dominio.Diagonal;
import dominio.Lineal;
import dominio.TipoFigura;
import java.rmi.RemoteException;
import servicio.IFachadaRemota;

/**
 *
 * @author Santiago
 */
public class ConfiguracionController {
    private UIConfiguracion ui;
    private IFachadaRemota fachada;

    public ConfiguracionController(UIConfiguracion ui, IFachadaRemota fachada) {
        this.ui = ui;
        this.fachada = fachada;
    }
    
    public void listarConfiguraciones() throws RemoteException{
         ui.listarConfiguraciones(fachada.getConfiguraciones());
    }
    
    public void listarFiguras() throws RemoteException{
        ui.listarFiguras(fachada.getFiguras());
    }
    
    public boolean partidaActiva() throws RemoteException{
        if(fachada.partidaActiva()){
            return true;
        }
        return false;
    }
    
    public void guardarConfiguracion() throws RemoteException{
        fachada.guardarConfiguracion(ui.getCantFilas(), ui.getCantColumnas(), ui.getCantJugadores(), ui.getCartonesPorJugador(), ui.getValorCarton(), ui.getTiempoEspera());
        fachada.activarLineal(ui.getLineal());
        fachada.activarDiagonal(ui.getDiagonal());
        fachada.activarCentro(ui.getCentro());
        ui.listarFiguras(fachada.getFiguras());        
    }
    
    public void cargarBotonesFiguras() throws RemoteException{
        for(TipoFigura f : fachada.getFiguras()){
            if(f instanceof Lineal){        
                  ui.LinealActivado();        
            }            
            if(f instanceof Diagonal){         
                  ui.DiagonalActivado();            
            }            
            if(f instanceof Centro){      
                  ui.CentroActivado();                
            }
        }
    }
    
    public void cargarSpinnersConfiguracion() throws RemoteException{
        for(Configuracion c : fachada.getConfiguraciones()){
            ui.setCantColumnas(c.getCantColumnas());
            ui.setCantFilas(c.getCantFilas());
            ui.setCantJugadores(c.getCantJugadores());
            ui.setCantCartonesJugador(c.getCartonesPorJugador());
            ui.setValorCarton(c.getValorCarton());
            ui.setTiempoEspera(c.getTiempoEspera());
        }
    }
    
  
    
    
}

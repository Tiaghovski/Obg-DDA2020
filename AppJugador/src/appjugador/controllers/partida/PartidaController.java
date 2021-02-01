/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjugador.controllers.partida;

import appjugador.Sistema;
import dominio.Carton;
import dominio.Jugador;
import dominio.Usuario;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import servicio.Evento;
import servicio.IFachadaRemota;
import servicio.IRemoteObserver;

/**
 *
 * @author Santiago
 */
public class PartidaController extends UnicastRemoteObject implements IRemoteObserver{
    private UIPartida ui;
    private IFachadaRemota fachada;

    public PartidaController(UIPartida ui, IFachadaRemota fachada) throws RemoteException {
        this.ui = ui;
        this.fachada = fachada;
        fachada.agregarObserver(this);
        ArrayList<Carton> cartonesJugador = Sistema.getJugador().getColCartones();         
    }
    
     public void listarFiguras() throws RemoteException{
        ui.listarFiguras(fachada.getFiguras());
    }
     
     public void listarJugadores() throws RemoteException{
         ui.listarJugadores(fachada.getJugadores());
     }
    
    public void mostrarCantidadJugares() throws RemoteException{
        ui.mostrarCantJugadores(fachada.getCantidadJugadores());
    }
    
    public void cerrarSesion() throws RemoteException{         
         fachada.cerrarSesion(Sistema.getJugador().getNombreUsuario());      
    }
    
    public void eliminarJugador() throws RemoteException{
        fachada.eliminarJugador(Sistema.getJugador());      
    }
    
    public void validarCantJugadoresPartida() throws RemoteException{
        if(fachada.partidaActiva()){
            ui.partidaIniciada();
        }       
    }

    @Override
    public void Update(IFachadaRemota fachada, Object obj, Object obj2) throws RemoteException {
       if(obj instanceof Evento){
           if(obj == Evento.Modificar_Figuras){
                    listarFiguras();
           }
           else if(obj == Evento.Partida_Iniciada){
               ui.partidaIniciada();
               ui.mostrarPozo(fachada.calcularPozoPartida());
               listarJugadores();
           }
           else if(obj == Evento.Jugador_Agregado){               
               ui.mostrarCantJugadores(fachada.getCantidadJugadores());
               ui.mostrarSaldoActual(Sistema.getJugador().getSaldo());
           }
           else if(obj == Evento.Jugador_Eliminado){
                ui.mostrarCantJugadores(fachada.getCantidadJugadores());                
                ui.mostrarPozo(fachada.calcularPozoPartida());
                ui.listarJugadores(fachada.getJugadores());
           }
           else if(obj == Evento.Bolilla_Sorteada){
               ui.ocultarEsperando();
               ui.mostrarUltimaBolillaSorteada(fachada.mostrarUltimaBolillaSorteada());
               if(!fachada.comprobarGanador(Sistema.getJugador())) {          
                    preguntarContinuidadJugador();
               }
           } 
           else if(obj == Evento.Numeros_Repartidos){               
               if(((Usuario)obj2).getNombreUsuario().equals(Sistema.getJugador().getNombreUsuario())){                     
                   ui.listarCartones(obtenerMatrizDeCartones(((Jugador)obj2).getColCartones().get(0).getNumerosCarton()));
               }
           }
       }
       else if(obj2!= null && obj2 instanceof Evento){           
           if(((Usuario)obj).getNombreUsuario().equals(Sistema.getJugador().getNombreUsuario())){
               if(obj2 == Evento.No_Continuar){  
//                   fachada.removerObserver(this);
                   eliminarJugador();
                   cerrarSesion();
                   ui.cerrarVentana();
               } else if(obj2 == Evento.Continuar){                   
                     ui.ocultarPanelContinuar();                   
               } else if(obj2 == Evento.Partida_Finalizada){                 
                   ui.mostrarPanelGanador();
                   ui.mostrarGanador(Sistema.getJugador().getNombreUsuario());                   
                   Sistema.getJugador().setSaldo(Sistema.getJugador().getSaldo() + fachada.getPartida().getPozo());
                   ui.mostrarSaldoActual(Sistema.getJugador().getSaldo());
               } 
           } else {
               if(obj2 == Evento.Partida_Finalizada) {
                   fachada.restarSaldoPerdedor(Sistema.getJugador());
                   cerrarSesion();
                   ui.cerrarVentana();
               }
           }
       }
    }
    
    public int obtenerNumeroFilas() throws RemoteException{
        return fachada.getCantFilas();
    }
    
    public int obtenerNumeroColumnas() throws RemoteException{
        return fachada.getCantColumnas();
    }
    
      public Integer[][] obtenerMatrizDeCartones(ArrayList<Integer> cartonesJugador) throws RemoteException{       
        Integer[][] matriz = new Integer[obtenerNumeroFilas()][obtenerNumeroColumnas()];
        int x = 0;        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (x == cartonesJugador.size()) {
                    break;
                }
                matriz[i][j] = cartonesJugador.get(x++);
            }
        }
        fachada.guardarMatrizDeCarton(matriz, Sistema.getJugador().getNombreUsuario());
        return matriz;
    }
      
      
    public void preguntarContinuidadJugador() throws RemoteException{
        ui.preguntarContinuidad();        
        fachada.preguntarContinuidad(Sistema.getJugador());
    }
    
    public void calcularValorPozo() throws RemoteException{
        ui.mostrarPozo(fachada.getPartida().getPozo());
    }
    
    public void continuar() throws RemoteException{
        fachada.desactivarHilo(Sistema.getJugador());
    }
    
    public void mostrarSaldoJugador() throws RemoteException{
        Sistema.getJugador().setSaldo(Sistema.getJugador().getSaldo() - (Sistema.getJugador().getColCartones().size() * fachada.getValorCarton()));
        ui.mostrarSaldoJugador(Sistema.getJugador().getSaldo());
    }

    public void removerObserver() throws RemoteException {
        fachada.removerObserver(this);
    }
    
    

 
    
  
    
    
}

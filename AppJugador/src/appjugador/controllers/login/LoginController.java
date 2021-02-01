/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjugador.controllers.login;

import appjugador.Sistema;
import appjugador.vistas.FrmPartida;
import dominio.EstadoPartida;
import dominio.Jugador;
import dominio.Usuario;
import java.rmi.RemoteException;
import servicio.IFachadaRemota;

/**
 *
 * @author Santiago
 */
public class LoginController {
    
    private UILogin ui;
    private IFachadaRemota fachada;

    public LoginController(UILogin ui, IFachadaRemota fachada) {
        this.ui = ui;
        this.fachada = fachada;
    }
    
    public void iniciarSesion() throws RemoteException{
         if(ui.getCartones() <= 0){
             ui.alertaCartones("Indique con cuantos cartones desea jugar");
         }else if(ui.getCartones() > fachada.getCantCartones()){
             ui.alertaCartones("No se puede participar con más de " + fachada.getCantCartones() + " cartones");
         } else if(fachada.getPartida().getEstado() == EstadoPartida.Iniciada){
             ui.alertaPatidaIniciada();
         }
         else {
             Jugador j = (Jugador)fachada.obtenerUsuarioPorNombre(ui.getNombreUsuario());
             if(j == null) {
               ui.mostrarMensaje("Datos incorrectos");
               return;
             }

             if(j.comprarCartones(ui.getCartones(), fachada.getValorCarton())){

                if(fachada.iniciarSesion(ui.getNombreUsuario(), ui.getContrasena())) {

                       Sistema.setJugador(j);
                       ui.cerrarForm();
                       entrarPartida();

                } else {
                    ui.mostrarMensaje("Acceso denegado");
                  }
            } else {
                   ui.alertaCartones("Saldo insuficiente");
            }
         }
    }
    
     public void entrarPartida() throws RemoteException{   
        new FrmPartida().setVisible(true); 
        fachada.agregarJugador(Sistema.getJugador());       
    }
     
   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjugador.controllers.login;

/**
 *
 * @author Santiago
 */
public interface UILogin {
    
    String getNombreUsuario();
    
    String getContrasena();
    
    void cerrarForm();
    
    int getCartones();

    void mostrarMensaje(String msg);
    
    void alertaCartones(String msg);
    
    void alertaPatidaIniciada();
    

    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appadministrador.controllers.login;

/**
 *
 * @author Santiago
 */
public interface UILogin {
    
    String getNombreUsuario();

    String getContrasena();

    void mostrarMensaje(String msg);

    void cerrarForm();
}

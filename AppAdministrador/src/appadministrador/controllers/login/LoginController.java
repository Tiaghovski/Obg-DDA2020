/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appadministrador.controllers.login;

import appadministrador.Sistema;
import appadministrador.vistas.FrmAdministrador;
import dominio.Administrador;
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
            if (fachada.iniciarSesion(ui.getNombreUsuario(), ui.getContrasena())) {
                Usuario u = fachada.obtenerUsuarioPorNombre(ui.getNombreUsuario());
                if (u instanceof Administrador) {
                    Sistema.setAdmin((Administrador) u);                   
                    new FrmAdministrador().setVisible(true);
                    ui.cerrarForm();
                } else {
                    ui.mostrarMensaje("Acceso denegado");
                }
            } else {
                ui.mostrarMensaje("Acceso denegado");
            }
        } 
    }
    
    
    
    


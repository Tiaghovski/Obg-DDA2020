/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appadministrador.controllers.admin;

import appadministrador.Sistema;
import java.rmi.RemoteException;
import servicio.IFachadaRemota;


/**
 *
 * @author Santiago
 */
public class AdministradorController {
    private UIAdministrador ui;
    private IFachadaRemota fachada;

    public AdministradorController(UIAdministrador ui, IFachadaRemota fachada) throws RemoteException {
        this.ui = ui;
        this.fachada = fachada;        
    }
    
    public void cerrarSesion() throws RemoteException{
         fachada.cerrarSesion(Sistema.getAdmin().getNombreUsuario());
    }

   
    
    
}

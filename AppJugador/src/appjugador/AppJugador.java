/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appjugador;

import appjugador.vistas.FrmLogin;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicio.IFachadaRemota;

/**
 *
 * @author Santiago
 */
public class AppJugador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {           
            Sistema.setFachada((IFachadaRemota) Naming.lookup("rmi://localhost/fachadaServidor"));
            FrmLogin frm = new FrmLogin();           
            frm.setVisible(true);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(AppJugador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

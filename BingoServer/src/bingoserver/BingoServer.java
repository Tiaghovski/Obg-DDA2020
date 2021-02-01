/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import servicio.IFachadaRemota;

/**
 *
 * @author Santiago
 */
public class BingoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            IFachadaRemota f = (IFachadaRemota) UnicastRemoteObject.exportObject(Fachada.getInstance(), 0);
            LocateRegistry.createRegistry(1099);
            Naming.rebind("fachadaServidor", f);
            System.out.println("-----------------------------");
            System.out.println("Servidor iniciado con éxito!");
            System.out.println("-----------------------------");  
        } catch (RemoteException ex) {
            Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BingoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

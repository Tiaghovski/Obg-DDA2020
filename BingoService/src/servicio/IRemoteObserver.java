/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Santiago
 */
public interface IRemoteObserver extends Remote{
    
    void Update(IFachadaRemota fachada, Object obj, Object obj2) throws RemoteException;


}

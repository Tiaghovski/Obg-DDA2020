/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicio;

import dominio.Configuracion;
import dominio.Jugador;
import dominio.Partida;
import dominio.TipoFigura;
import dominio.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public interface IFachadaRemota extends Remote {
    
    boolean iniciarSesion(String username, String password) throws RemoteException;

    Usuario obtenerUsuarioPorNombre(String username) throws RemoteException;

    void cerrarSesion(String username) throws RemoteException;

    void agregarObserver(IRemoteObserver io) throws RemoteException;
    
    ArrayList<Configuracion> getConfiguraciones() throws RemoteException;
     
    ArrayList<TipoFigura> getFiguras() throws RemoteException;
    
    ArrayList<Jugador> getJugadores() throws RemoteException;
     
    void guardarConfiguracion(int a, int b, int c, int d, int e, int f) throws RemoteException;
    
    boolean activarLineal(boolean res) throws RemoteException;
    
    boolean activarDiagonal(boolean res) throws RemoteException;
     
    boolean activarCentro(boolean res) throws RemoteException;
    
    boolean partidaActiva() throws RemoteException;  
    
    void agregarJugador(Jugador j) throws RemoteException;
    
    void eliminarJugador(Jugador j) throws RemoteException;
    
    int getCantidadJugadores() throws RemoteException;
    
    int getCantFilas() throws RemoteException;
    
    int getCantColumnas() throws RemoteException;
    
    int getCantCartones() throws RemoteException;
    
    int getValorCarton() throws RemoteException;
     
    int mostrarUltimaBolillaSorteada() throws RemoteException;
    
    void preguntarContinuidad(Jugador j) throws RemoteException;
    
    Partida getPartida() throws RemoteException;
    
    int calcularPozoPartida() throws RemoteException;
    
    void desactivarHilo(Jugador j) throws RemoteException;
    
    void removerObserver(IRemoteObserver o) throws RemoteException;
    
    ArrayList<Integer> getBolillasSorteadas() throws RemoteException;
    
    void guardarMatrizDeCarton(Integer[][] matriz, String nombreUsuario) throws RemoteException;

    boolean comprobarGanador(Jugador j) throws RemoteException;
    
    void restarSaldoJugador(Jugador j) throws RemoteException;
   
    void restarSaldoPerdedor(Jugador j) throws RemoteException;
    
    
    
    
    
 
    
    
    
}

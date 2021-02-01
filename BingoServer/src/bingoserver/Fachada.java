/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import dominio.Centro;
import dominio.Configuracion;
import dominio.Diagonal;
import dominio.EstadoPartida;
import dominio.Jugador;
import dominio.Lineal;
import dominio.Partida;
import dominio.TipoFigura;
import dominio.Usuario;
import gestoras.GestoraConfiguracion;
import gestoras.GestoraPartidas;
import gestoras.GestoraUsuarios;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import servicio.Evento;
import servicio.IFachadaRemota;
import servicio.IRemoteObserver;

/**
 *
 * @author Santiago
 */
public class Fachada implements IFachadaRemota {
    private GestoraUsuarios gu;
    private GestoraConfiguracion gc;   
    private GestoraPartidas gp;
    private static Fachada instancia = null;
    private ArrayList<IRemoteObserver> colObservers;  
    private ArrayList<ThreadSimple> colHilos;
    
    public static Fachada getInstance(){
        if(instancia == null){
            return new Fachada();
        }
        return instancia;
    }


    

    public Fachada() {
        gu = GestoraUsuarios.getInstance();
        gc = GestoraConfiguracion.getInstance();
        gp = GestoraPartidas.getInstance();
        gp.agregarPartida(new Partida());
        gp.setConfig(getConfiguracion());
        colObservers = new ArrayList<>();  
        colHilos = new ArrayList<>();
    } 
    
    public Configuracion getConfiguracion(){
        Configuracion config = null;
        for(Configuracion c : gc.obtenerConfiguraciones()){
            config = c;
        }
        return config;
    }    
    
    @Override
    public void agregarObserver(IRemoteObserver io) throws RemoteException {
        colObservers.add(io);
    }  
    
    @Override
    public void removerObserver(IRemoteObserver o) throws RemoteException {
        colObservers.remove(o);
    }
   
    
    public void notifyObserver(Object obj, Object obj2){
        Iterator<IRemoteObserver> it = colObservers.iterator();
        while (it.hasNext()) {
            try {
                it.next().Update(this, obj, obj2);
            } catch (RemoteException e) {
                it.remove();
            }
        }        
    }    

    @Override
    public boolean iniciarSesion(String username, String password) throws RemoteException {  
         if (gu.iniciarSesion(username, password)) {      
            return true;
        }
        return false;
    }    
   

    @Override
    public Usuario obtenerUsuarioPorNombre(String username) throws RemoteException {
         return gu.obtenerUsuarioPorNombre(username);
    }

    @Override
    public void cerrarSesion(String username) throws RemoteException {
        gu.cerrarSesion(username);        
    }

    @Override
    public ArrayList<Configuracion> getConfiguraciones() throws RemoteException {
        return gc.obtenerConfiguraciones();
    }
    
    @Override
    public ArrayList<Jugador> getJugadores() throws RemoteException {
        return gp.getPartida().getColJugadores();
    }


    @Override
    public void guardarConfiguracion(int a, int b, int c, int d, int e, int f) throws RemoteException {
        gc.guardarConfiguracion(a, b, c, d, e, f);
    }

    @Override
    public ArrayList<TipoFigura> getFiguras() throws RemoteException {
        ArrayList<TipoFigura> f = new ArrayList<>();
        for(Configuracion c : gc.obtenerConfiguraciones()){
            c.getColFiguras();            
            f = c.getColFiguras();          
        }    
        return f;
    }

    @Override
    public boolean activarLineal(boolean res) {
        boolean hay = false;
        for(Configuracion c : gc.obtenerConfiguraciones()){
            c.getColFiguras(); 
            for(TipoFigura tp : c.getColFiguras()){
                if(tp instanceof Lineal){
                    if(!res){
                       c.eliminarFigura(tp);
                       notifyObserver(Evento.Modificar_Figuras, null);
                       return false;
                    }                   
                    hay = true;
                }                       
            }
            if(!hay && res){
                c.agregarFigura(new Lineal());
                notifyObserver(Evento.Modificar_Figuras, null);
                return true;
            }
          }    
        return false;  
      }
                       
    
          
    

    @Override
    public boolean activarDiagonal(boolean res) throws RemoteException {
        boolean hay = false;
        for(Configuracion c : gc.obtenerConfiguraciones()){
            c.getColFiguras(); 
            for(TipoFigura tp : c.getColFiguras()){
                if(tp instanceof Diagonal){
                    if(!res){
                       c.eliminarFigura(tp);
                       notifyObserver(Evento.Modificar_Figuras, null);
                       return false;
                    }                   
                    hay = true;
                }                       
            }
            if(!hay && res){
                c.agregarFigura(new Diagonal());
                notifyObserver(Evento.Modificar_Figuras, null);
                return true;
            }
          }    
        return false;  
      }
    

    @Override
    public boolean activarCentro(boolean res) throws RemoteException {
        boolean hay = false;
        for(Configuracion c : gc.obtenerConfiguraciones()){
            c.getColFiguras(); 
            for(TipoFigura tp : c.getColFiguras()){
                if(tp instanceof Centro){
                    if(!res){
                       c.eliminarFigura(tp);
                       notifyObserver(Evento.Modificar_Figuras, null);
                       return false;
                    }                   
                    hay = true;
                }                       
            }
            if(!hay && res){
                c.agregarFigura(new Centro());
                notifyObserver(Evento.Modificar_Figuras, null);
                return true;
            }
          }    
        return false;  
      }
    

    @Override
    public void agregarJugador(Jugador j) throws RemoteException {
        int cantidadJugadores = 0; 
        for(Configuracion c : gc.obtenerConfiguraciones()){
            cantidadJugadores = c.getCantJugadores();
        }
        gp.getPartida().agregarJugador(j);
        if(gp.getPartida().getColJugadores().size() == cantidadJugadores){
            gp.getPartida().empezarPartida();  
            calcularNumerosBolillero();
            notifyObserver(Evento.Partida_Iniciada, null);            
            calcularPozoPartida();           
            repartirNumeros();  
            sortearBolilla();
        }
        else{           
            notifyObserver(Evento.Jugador_Agregado, null);
            gp.getPartida().setEstado(EstadoPartida.En_Espera);    
        }
    }
    
    @Override
    public void eliminarJugador(Jugador j) throws RemoteException {
       if(gp.getPartida() == null || gp.getPartida().getColJugadores().isEmpty()) {
           for (ThreadSimple h : colHilos) {
               h.parar();
           }
           colHilos = new ArrayList<>();
           return;
       }
       restarSaldoJugador(j);
       gp.getPartida().eliminarJugador(j);
       notifyObserver(Evento.Jugador_Eliminado, null);      
       if(partidaFinalizada()){
            notifyObserver(gp.getPartida().getColJugadores().get(0), Evento.Partida_Finalizada);             
            gp.getPartida().setEstado(EstadoPartida.Finalizada);
            asignarSaldoJugadorGanador(gp.getPartida().getColJugadores().get(0));      
            gp.nuevaPartida();
            gp.setConfig(getConfiguracion());
       }
    }
    
    public void asignarSaldoJugadorGanador(Jugador jGanador){
        int valor = jGanador.getSaldo() + gp.getPartida().calcularValorPozo();
        for(Usuario j : gu.getColUsuarios()){
            if(j instanceof Jugador){
               if(j.getNombreUsuario().equals(jGanador.getNombreUsuario())){
                  ((Jugador)j).setSaldo(valor);   
               }                
            }               
        }
    }
    

    @Override
    public int getCantidadJugadores() throws RemoteException {
        return gp.getPartida().getColJugadores().size();
    }

    @Override
    public int getCantFilas() throws RemoteException {
        int cantFilas = 0;
        for(Configuracion c : gc.obtenerConfiguraciones()){
            c.getCantFilas();
            cantFilas =  c.getCantFilas();
        }
        return cantFilas;
    }

    @Override
    public int getCantColumnas() throws RemoteException {
        int cantColumnas = 0;
        for(Configuracion c: gc.obtenerConfiguraciones()){
            c.getCantColumnas();
            cantColumnas = c.getCantColumnas();
        }
        return cantColumnas;
    }
    
    @Override
    public int getCantCartones() throws RemoteException {
        int cantCartonesMax = 0;
        for(Configuracion c : gc.obtenerConfiguraciones()){
            c.getCartonesPorJugador();
            cantCartonesMax = c.getCartonesPorJugador();
        }
        return cantCartonesMax;
    }
    
    @Override
    public int getValorCarton() throws RemoteException {
        int valorCarton = 0;
        for(Configuracion c : gc.obtenerConfiguraciones()){
            c.getValorCarton();
            valorCarton = c.getValorCarton();
        }
        return valorCarton;
    }
    
    @Override
    public Partida getPartida() throws RemoteException {
        return gp.getPartida();
    }
    

    @Override
    public boolean partidaActiva() throws RemoteException {
        if(gp.getPartida().getEstado() == EstadoPartida.Iniciada){
            return true;
        }
        return false;
    }
    
    public void calcularNumerosBolillero(){
        int cantFilas = 0;
        int cantColumnas = 0;       
        for(Configuracion c : gc.obtenerConfiguraciones()){          
            cantFilas = c.getCantFilas();
            cantColumnas = c.getCantColumnas();
        }
        int cantCartonesTotalesJugador = 0;
        for(Jugador j : gp.getPartida().getColJugadores()){
            cantCartonesTotalesJugador += j.getColCartones().size();
        }
        int valorFinal = (cantFilas * cantColumnas) * cantCartonesTotalesJugador; 
        ArrayList<Integer> bolillas = new ArrayList<>();
        for(int i = 1; i <= valorFinal; i++){
            bolillas.add(i);
        }    
        gp.getPartida().getBolillero().setNumerosBolillero(bolillas);
    }
    
    public void sortearBolilla() throws RemoteException{
        gp.getPartida().setUltimaBolillaSorteada(gp.getPartida().getBolillero().sortearBolilla());
        notifyObserver(Evento.Bolilla_Sorteada, null);   
    }
    
    public void borrarHilo(Jugador j) throws RemoteException{
        for(ThreadSimple ts : colHilos){
            if(ts.getJugador().getNombreUsuario().equals(j.getNombreUsuario())){               
                colHilos.remove(ts);
                break;
            }
        }
        if(colHilos.isEmpty()){
            sortearBolilla();
        }
    }

    @Override
    public int mostrarUltimaBolillaSorteada() throws RemoteException {
        return gp.getPartida().getUltimaBolillaSorteada();
    }

    @Override
    public void preguntarContinuidad(Jugador j) throws RemoteException {                   
           ThreadSimple ts = new ThreadSimple(j,this, getConfiguracion());
           colHilos.add(ts);
           ts.iniciar();           
    }   
    

    @Override
    public int calcularPozoPartida() throws RemoteException {
        return gp.getPartida().calcularValorPozo();
    }
    

    @Override
    public void desactivarHilo(Jugador j) throws RemoteException {
        for(ThreadSimple ts : colHilos){
            if(ts.getJugador().getNombreUsuario().equals(j.getNombreUsuario())){
                ts.parar();
                colHilos.remove(ts);
                break;
            }
        }
        if(colHilos.isEmpty()){
            sortearBolilla();
        }
    }

    public void repartirNumeros() {      
       gp.getPartida().repartirNumeros();    
       for(Jugador j : gp.getPartida().getColJugadores()){          
           notifyObserver(Evento.Numeros_Repartidos, j);
       }
    }

    public boolean partidaFinalizada() {
        if(gp.getPartida().getColJugadores().size() == 1 && gp.getPartida().getEstado() != EstadoPartida.En_Espera){
            return true;
        }
        return false;
    }
    
    @Override
    public ArrayList<Integer> getBolillasSorteadas() {
        return gp.getPartida().getBolillero().getBolillasSorteadas();
    }

    @Override
    public void guardarMatrizDeCarton(Integer[][] matriz, String nombreUsuario) {
        Usuario u = gp.getPartida().buscarJugadorPorNombreUsuario(nombreUsuario);
        ((Jugador)u).getColCartones().get(0).setMatriz(matriz);       
    }

   @Override
    public boolean comprobarGanador(Jugador j) throws RemoteException {
        Jugador jugadorGanador = gp.getPartida().comprobarVictoria();       
        if(jugadorGanador != null && ((Usuario)j).getNombreUsuario().equals(((Usuario)jugadorGanador).getNombreUsuario())) {
            notifyObserver(jugadorGanador, Evento.Partida_Finalizada);
            gp.getPartida().setEstado(EstadoPartida.Finalizada);
            asignarSaldoJugadorGanador(jugadorGanador);
            gp.nuevaPartida();
            gp.setConfig(getConfiguracion());
            return true;
        }
        return false;
    }
    
    @Override
    public void restarSaldoJugador(Jugador j) throws RemoteException {
        Jugador jugadorEliminado = (Jugador)gu.obtenerUsuarioPorNombre(j.getNombreUsuario());
        jugadorEliminado.setSaldo(j.getSaldo());
    }

    @Override
    public void restarSaldoPerdedor(Jugador j) throws RemoteException {
        Jugador jugadorEliminado = (Jugador)gu.obtenerUsuarioPorNombre(j.getNombreUsuario());
        int saldoArestar = j.getColCartones().size() * gp.getPartida().getConfig().getValorCarton();
        jugadorEliminado.setSaldo(j.getSaldo() - saldoArestar);
    }


  

   
  
   

 

  

  

    
  

   
   

  

}

   
    
   
    
    
    
    
    


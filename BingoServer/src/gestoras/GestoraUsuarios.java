/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestoras;

import bingoserver.conexionDB;
import dominio.Administrador;
import dominio.Jugador;
import dominio.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago
 */
public class GestoraUsuarios implements Serializable {
    private ArrayList<Usuario> colUsuarios;
    private static GestoraUsuarios instancia = null;
    
    public static GestoraUsuarios getInstance(){
        if(instancia == null){
            return new GestoraUsuarios();
        }
        return instancia;
    }

    public GestoraUsuarios() {
        colUsuarios = new ArrayList<>();
        cargarUsuarios();
    }

    public ArrayList<Usuario> getColUsuarios() {
        return colUsuarios;
    }

    public void setColUsuarios(ArrayList<Usuario> colUsuarios) {
        this.colUsuarios = colUsuarios;
    }
    
    
    
    public void cargarUsuarios(){
        Administrador a1 = new Administrador("santiagoadmin", "123");
        Administrador a2 = new Administrador("lucasadmin", "123");
        Jugador j1 = new Jugador(100, "ssilva", "123");
        Jugador j2 = new Jugador(150, "mazzanga", "123");
        Jugador j3 = new Jugador(200, "suarez", "123");
        Jugador j4 = new Jugador(200, "cavani", "123");
        Jugador j5 = new Jugador(300, "forlan", "123");
        Jugador j6 = new Jugador(250, "muslera", "123");
        Jugador j7 = new Jugador(15, "sequeira", "123");
        
        colUsuarios.add(a1);
        colUsuarios.add(a2);       
        colUsuarios.add(j1);       
        colUsuarios.add(j2); 
        colUsuarios.add(j3);
        colUsuarios.add(j4);
        colUsuarios.add(j5);
        colUsuarios.add(j6);
        colUsuarios.add(j7);
//        agregarAdminBD(a1);
//        agregarAdminBD(a2);
//        agregarJugadorBD(j1);
//        agregarJugadorBD(j2);
//        agregarJugadorBD(j3);
//        agregarJugadorBD(j4);
//        agregarJugadorBD(j5);
//        agregarJugadorBD(j6);
//        agregarJugadorBD(j7);
    }
    
    public boolean iniciarSesion(String username, String password){
         if (username == null || username.trim().length() == 0 || password == null || password.trim().length() == 0) {
            return false;
        }
        Usuario u = obtenerUsuarioPorNombre(username);
        if (u != null && u.getContrasena().equals(password) && !u.isLogueado()) {
            u.setLogueado(true);            
            return true;
        }
        return false;
    }
    
    public Usuario obtenerUsuarioPorNombre(String username){
        Usuario u = null;
        for (Usuario user : colUsuarios) {
            if (user.getNombreUsuario().equals(username)) {
                u = user;
                break;
            }
        }
        return u;
    }
    
     public boolean cerrarSesion(String nick) {
        for (Usuario usu : colUsuarios) {
            if (usu.getNombreUsuario().equals(nick)) {
                usu.setLogueado(false);              
                return true;
            }
        }
        return false;
    }
     
      public void agregarAdminBD(Usuario u) {
        conexionDB db = new conexionDB();
        Connection conn = null;
        
        PreparedStatement ps = null;
        PreparedStatement pslineas = null;        
       
        
        try {
            conn = db.getConnection();
            conn.setAutoCommit(true);
            
            String query = "INSERT INTO administradores (nombreUsuario, contrasena) VALUES(?,?)";
            
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getContrasena());            
            
            int rs = ps.executeUpdate();          
        } catch (SQLException ex) {
            // roll back the transaction
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {                
            }            
        } catch (Exception ex) {
            Logger.getLogger(GestoraUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
      
       public void agregarJugadorBD(Usuario u) {
        conexionDB db = new conexionDB();
        Connection conn = null;
        
        PreparedStatement ps = null;
        PreparedStatement pslineas = null;        
       
        
        try {
            conn = db.getConnection();
            conn.setAutoCommit(true);
            
            String query = "INSERT INTO jugadores (nombreUsuario, contrasena, saldo) VALUES(?,?,?)";
            
            ps = conn.prepareStatement(query);
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u.getContrasena()); 
            ps.setInt(3, ((Jugador)u).getSaldo());
            
            int rs = ps.executeUpdate();          
        } catch (SQLException ex) {
            // roll back the transaction
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {                
            }            
        } catch (Exception ex) {
            Logger.getLogger(GestoraUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    
            
    
}

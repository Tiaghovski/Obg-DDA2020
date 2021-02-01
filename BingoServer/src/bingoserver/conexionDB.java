/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingoserver;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public class conexionDB {
    static String ipBD;
    static String portBD;
    static String serviceBD;
    static String userBD;
    static String passwd;
    static String url;
    static boolean logConect=true;

    Connection conn=null;

    public conexionDB(){
        try {            
            ipBD = "localhost";
            serviceBD = "bingodb";
            userBD = "root";
            passwd = "llegandolosmonos";
            portBD = "3306";
            
            String hostname = java.net.InetAddress.getLocalHost().getHostName().trim();            

            url = "jdbc:mysql://localhost/bingodb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";            
            Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            conn = DriverManager.getConnection (url, userBD, passwd);
           
            if (conn != null) {
                conn.setAutoCommit(false);
                this.printSystem("Conexión a Base de Datos exitosa!");
            } else {
                this.printSystem("No se conectó a la Base de Datos");
            }
            logConect=false;
        } catch(Exception e) {
            System.out.println("Error: "+e);
        }
    }

    public Connection getConnection() throws Exception{
        try {
            if(this.conn == null){
                Class.forName ("com.mysql.jdbc.Driver").newInstance ();
                conn = DriverManager.getConnection (url, userBD, passwd);

                if (conn != null) {
                    conn.setAutoCommit(false);
                }else{
                    throw (new Exception("null") );
                }
            }
        }
        catch (Exception e) {
            if(conn!=null){
                conn.rollback();
                conn.close();
            }
            throw(new Exception("Sin Conexion Base de Datos. ["+e.getMessage()+"]"));
        }
        return conn;
    }
    
    public void printSystem(String msj){
        if(logConect) System.out.println(msj);
    }
}

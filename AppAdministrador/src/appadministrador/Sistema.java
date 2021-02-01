/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appadministrador;

import dominio.Administrador;
import dominio.Configuracion;
import servicio.IFachadaRemota;

/**
 *
 * @author Santiago
 */
public class Sistema {
    private static IFachadaRemota fachada;
    private static Administrador admin;  
  
    private Sistema() {
    }

    public static IFachadaRemota getFachada() {
        return fachada;
    }

    public static void setFachada(IFachadaRemota fachada) {
        Sistema.fachada = fachada;
    }

    public static Administrador getAdmin() {
        return admin;
    }

    public static void setAdmin(Administrador admin) {
        Sistema.admin = admin;
    }

   

    
   
    

   
    
    
    
    
    
    
}

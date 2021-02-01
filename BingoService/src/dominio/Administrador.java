/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;

/**
 *
 * @author Santiago
 */
public class Administrador extends Usuario implements Serializable {

    public Administrador(String nombreUsuario, String contrasena) {
        super(nombreUsuario, contrasena);
    }
    
    
}

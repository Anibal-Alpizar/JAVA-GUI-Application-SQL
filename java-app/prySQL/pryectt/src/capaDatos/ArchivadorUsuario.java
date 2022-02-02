/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaDatos;

import capaLogica.Usuario;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author aniba
 */
public class ArchivadorUsuario {

    //la informacion se puede almacenar en un solo array, pero para mayor orden se decide hacer 2.
    private static ArrayList<Usuario> arrayUsuarios = new ArrayList<>();
    private static ArrayList<Usuario> arrayAdmin = new ArrayList<>();

    public static void agregarUsuario(Usuario usuario) {
        arrayUsuarios.add(usuario);

    }

    public static void agregarAdmin(Usuario admin) {
        arrayAdmin.add(admin);
    }

    public static String verAdminCorreo() {

        for (Usuario u : arrayAdmin) {
            return u.getCorreo();
        }

        return null;

    }

    //return correo
    public static String verUsuario() {

        for (Usuario u : arrayUsuarios) {
            return u.getCorreo();
        }

        return null;

    }

    public static String verContrasegnaAdmin() {
        for (Usuario c : arrayAdmin) {
            return c.getClave();
        }
        return null;
    }

    //return clave
    public static String verContrasegna() {
        for (Usuario c : arrayUsuarios) {
            return c.getClave();
        }
        return null;
    }

    public static String verNombreCompleto() {
        for (Usuario c : arrayUsuarios) {
            return c.getNombre();
        }
        return null;
    }

    public static String verPais() {
        for (Usuario c : arrayUsuarios) {
            return c.getPaisOrigen();
        }
        return null;
    }

    public static String verDireccion() {
        for (Usuario c : arrayUsuarios) {
            return c.getDireccion();
        }
        return null;
    }

    public static int verTelefono() {
        for (Usuario c : arrayUsuarios) {
            return c.getTelefono();

        }
        return 0;
        
    }


//me devuelve la lista completa de usuario, podiendo filtrarlos
public static String listaUsuarios() {
        String hilera = "";
        for (Usuario usuario : arrayUsuarios) {
            hilera += usuario.getCorreo() + "\t" + usuario.getClave() + "\n";
        }
        return hilera;
    }

}

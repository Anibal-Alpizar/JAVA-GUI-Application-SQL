/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica;

import capaDatos.ArchivadorUsuario;

/**
 *
 * @author aniba
 */
public class Usuario {

    private String correo;
    private String clave;
    private String nombre;
    private String paisOrigen;
    private String direccion;
    private int telefono;

    //Para el admin
//    public Usuario(String correo, String clave) {
//        this.correo = correo;   //admin
//        this.clave = clave;     //123
//    }
    //Para nuevo
    public Usuario(String correo, String clave, String nombre, String paisOrigen, String direccion, int telefono) {
        this.correo = correo;
        this.clave = clave;
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public boolean comprobacionUsuario() {

        if (this.correo.equals(ArchivadorUsuario.verAdminCorreo()) && this.clave.equals(ArchivadorUsuario.verContrasegnaAdmin())) {  //cuenta admin
            return true;

        } else if (this.correo.equals(ArchivadorUsuario.verUsuario()) && this.clave.equals(ArchivadorUsuario.verContrasegna())) {   //cuenta nueva
            return true;

        }
        return false;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
//
//    public TipoUsuario getTipoUsuario() {
//        return tipoUsuario;
//    }
//
//    public void setTipoUsuario(TipoUsuario tipoUsuario) {
//        this.tipoUsuario = tipoUsuario;
//    }

}

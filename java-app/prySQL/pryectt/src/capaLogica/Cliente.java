/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 *
 * @author aniba
 */
public class Cliente {

    private String nombre;
    private String paisOrigen;
    private String direccion;
    private int telefono;
    private String tipoCliente;

    public Cliente(String nombre, String paisOrigen, String direccion, int telefono, String tipoCliente) {
        this.nombre = nombre;
        this.paisOrigen = paisOrigen;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoCliente = tipoCliente;
    }

    public Cliente() {
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

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

}

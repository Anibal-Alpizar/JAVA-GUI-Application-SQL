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
public class Parte {

    private String numeroParte;
    private String tipo;
    private int modelo;
    private double precio;
    private String estado;

    public Parte() {
    }

    public Parte(String numeroParte) {
        this.numeroParte = numeroParte;
    }

    public Parte(String numeroParte, String tipo, int modelo, double precio, String estado) {
        this.numeroParte = numeroParte;
        this.tipo = tipo;
        this.modelo = modelo;
        this.precio = precio;
        this.estado = estado;
    }

    public String getNumeroParte() {
        return numeroParte;
    }

    public void setNumeroParte(String numeroParte) {
        this.numeroParte = numeroParte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}

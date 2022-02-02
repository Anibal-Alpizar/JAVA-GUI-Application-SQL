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
public class Vehiculo {

    private String codigoVehiculo;
    private int modelo;
    private double precio;
    private String tipo;
    private String extras;

    public Vehiculo() {
    }

    public Vehiculo(String codigoVehiculo, int modelo, double precio, String tipo, String extras) {
        this.codigoVehiculo = codigoVehiculo;
        this.modelo = modelo;
        this.precio = precio;
        this.tipo = tipo;
        this.extras = extras;
    }

    public String getCodigoVehiculo() {
        return codigoVehiculo;
    }

    public void setCodigoVehiculo(String codigoVehiculo) {
        this.codigoVehiculo = codigoVehiculo;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
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

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void guardarAuto(PrintWriter Escribe) {
        Escribe.println(codigoVehiculo);
        Escribe.println(modelo);
        Escribe.println(precio);
        Escribe.println(tipo);
        Escribe.println(extras);
    }

    public Vehiculo cargarVehiculos(BufferedReader Almacen) {
        String CV;
        int Mod;
        String Extra;
        double Pre;
        String Tip;
        try {
            CV = Almacen.readLine();    //primera instancial del buffer
            Mod = Integer.parseInt(Almacen.readLine());
            Pre = Double.parseDouble(Almacen.readLine());
            Tip = Almacen.readLine();
            Extra = Almacen.readLine();

            return new Vehiculo(CV, Mod, Pre, Tip, Extra);
        } catch (Exception e) {
        }
        return null;
    }

}

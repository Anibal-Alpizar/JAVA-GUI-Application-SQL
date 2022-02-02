/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package capaLogica;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Date;

/**
 *
 * @author aniba
 */
public class Factura {

    private String noFactura;
    private String tipoPago;
    private String cliente;
    private String direccion;
    private int telefonoContacto;
    private String vendedor;
    private String totalPagar;

    public Factura(String noFactura, String cliente, int telefonoContacto, String totalPagar) {
        this.noFactura = noFactura;
        this.cliente = cliente;
        this.telefonoContacto = telefonoContacto;
        this.totalPagar = totalPagar;
    }


    public Factura() {
    }

    public String getNoFactura() {
        return noFactura;
    }

    public void setNoFactura(String noFactura) {
        this.noFactura = noFactura;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(int telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(String totalPagar) {
        this.totalPagar = totalPagar;
    }

    public void guardar(PrintWriter Escribe) {
        Escribe.println(noFactura);
        Escribe.println(cliente);
        Escribe.println(telefonoContacto);
        Escribe.println(totalPagar);
    }

    public Factura cargar(BufferedReader Almacen) {
        String NO, Cl, TP;
        int TE;
        try {
            NO = Almacen.readLine();
            Cl = Almacen.readLine();
            TE = Integer.parseInt(Almacen.readLine());
            TP = Almacen.readLine();
            
            return new Factura (NO, Cl, TE ,TP);
        } catch (Exception e) {
        }
        return null;
    }

}

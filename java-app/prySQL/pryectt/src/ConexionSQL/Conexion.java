/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author aniba
 */
public class Conexion {

    //para partes 
    public static Connection getConexion() {

        String url = "jdbc:sqlserver://localhost:1433;"
                + "database=partes;"
                + "user=sa;"
                + "password=12345678";

        try {
            Connection con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }

    }

    //para clientes
    public static Connection getConexionClientes() {

        String url = "jdbc:sqlserver://localhost:1433;"
                + "database=clientes;"
                + "user=sa;"
                + "password=12345678";

        try {
            Connection con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }

    }
}

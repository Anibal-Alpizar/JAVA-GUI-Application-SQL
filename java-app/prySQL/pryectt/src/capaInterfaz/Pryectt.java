/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capaInterfaz;

import capaLogica.Usuario;
import capaDatos.ArchivadorUsuario;

/**
 *
 * @author aniba
 */
public class Pryectt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        frmLogin frameLogin = new frmLogin();
        frameLogin.setVisible(true);
        frameLogin.setResizable(false);
        frameLogin.setLocationRelativeTo(null);
        ArchivadorUsuario.agregarAdmin(new Usuario("admin", "123", null, null, null, 0));
        frameLogin.setTitle("BMW \t" + "- Fabricante de automóviles");

    }

}

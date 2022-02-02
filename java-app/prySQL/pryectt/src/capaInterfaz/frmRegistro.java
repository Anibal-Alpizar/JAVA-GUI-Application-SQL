/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package capaInterfaz;

import capaLogica.Usuario;
import capaDatos.ArchivadorUsuario;
import javax.swing.JOptionPane;

/**
 *
 * @author aniba
 */
public class frmRegistro extends javax.swing.JFrame {

    /**
     * Creates new form frame_login
     */
    frmLogin frmLogin = new frmLogin();
    private Usuario usuario;
    frmCuerpo programa = new frmCuerpo();

    public frmRegistro() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jtxtUser = new javax.swing.JTextField();
        btnVolver = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        logBmw = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jtxtTelefono = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jtxtPais = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnRegistrarUsuario = new javax.swing.JButton();
        jtxtPass = new javax.swing.JPasswordField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtDireccion = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(54, 127, 168));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtUser.setBackground(new java.awt.Color(54, 127, 168));
        jtxtUser.setForeground(new java.awt.Color(204, 204, 204));
        jtxtUser.setText("Username");
        jtxtUser.setBorder(null);
        jtxtUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtUserFocusGained(evt);
            }
        });
        jPanel3.add(jtxtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 26, 244, 30));

        btnVolver.setBackground(new java.awt.Color(54, 127, 168));
        btnVolver.setForeground(new java.awt.Color(204, 204, 204));
        btnVolver.setText("Volver");
        btnVolver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        jPanel3.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(715, 381, 103, 40));

        jPanel1.setBackground(new java.awt.Color(35, 45, 49));

        logBmw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/BMWico.png"))); // NOI18N

        jLabel5.setBackground(new java.awt.Color(186, 79, 84));
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(187, 187, 187));
        jLabel5.setText("B M W ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(83, Short.MAX_VALUE)
                .addComponent(logBmw)
                .addGap(81, 81, 81))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(logBmw)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 224, 19));

        jSeparator2.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 64, 224, 19));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mostrarIco.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(641, 83, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/passwordIco.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 83, -1, 42));

        jtxtNombre.setBackground(new java.awt.Color(54, 127, 168));
        jtxtNombre.setForeground(new java.awt.Color(204, 204, 204));
        jtxtNombre.setText("Nombre completo");
        jtxtNombre.setBorder(null);
        jtxtNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtNombreFocusGained(evt);
            }
        });
        jPanel3.add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 134, 244, 32));

        jSeparator3.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator3.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 109, 224, 19));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/telIco.png"))); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, -1, 42));

        jtxtTelefono.setBackground(new java.awt.Color(54, 127, 168));
        jtxtTelefono.setForeground(new java.awt.Color(204, 204, 204));
        jtxtTelefono.setText("Teléfono");
        jtxtTelefono.setBorder(null);
        jtxtTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtTelefonoFocusGained(evt);
            }
        });
        jPanel3.add(jtxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, 244, 32));

        jSeparator4.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator4.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 172, 224, 19));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nombreIco.png"))); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 134, -1, 42));

        jtxtPais.setBackground(new java.awt.Color(54, 127, 168));
        jtxtPais.setForeground(new java.awt.Color(204, 204, 204));
        jtxtPais.setText("Pais");
        jtxtPais.setBorder(null);
        jtxtPais.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtPaisFocusGained(evt);
            }
        });
        jPanel3.add(jtxtPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 244, 32));

        jSeparator5.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator5.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 224, 19));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paisIcov2.png"))); // NOI18N
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, 42));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(74, 31, 61));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(793, 11, 57, 40));

        btnRegistrarUsuario.setBackground(new java.awt.Color(54, 127, 168));
        btnRegistrarUsuario.setForeground(new java.awt.Color(204, 204, 204));
        btnRegistrarUsuario.setText("Registrar");
        btnRegistrarUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        btnRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUsuarioActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegistrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(606, 381, 103, 40));

        jtxtPass.setBackground(new java.awt.Color(54, 127, 168));
        jtxtPass.setForeground(new java.awt.Color(204, 204, 204));
        jtxtPass.setText("Ingresar contraseña");
        jtxtPass.setBorder(null);
        jtxtPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtPassFocusGained(evt);
            }
        });
        jtxtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtPassKeyTyped(evt);
            }
        });
        jPanel3.add(jtxtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 83, 224, -1));

        jRadioButton1.setBackground(new java.awt.Color(54, 127, 168));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 83, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/userIco (2).png"))); // NOI18N
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 33, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paisIcov2.png"))); // NOI18N
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, -1, 42));

        jtxtDireccion.setBackground(new java.awt.Color(54, 127, 168));
        jtxtDireccion.setForeground(new java.awt.Color(204, 204, 204));
        jtxtDireccion.setText("Direccion");
        jtxtDireccion.setBorder(null);
        jtxtDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtxtDireccionFocusGained(evt);
            }
        });
        jPanel3.add(jtxtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 244, 32));

        jSeparator6.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator6.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 224, 19));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        jtxtUser.requestFocus();

    }//GEN-LAST:event_formWindowOpened

    private void btnRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarUsuarioActionPerformed

        String correo = jtxtUser.getText();
        String clave = "";
        String nombre = jtxtNombre.getText();
        String pais = jtxtPais.getText();
        String direccion = jtxtDireccion.getText();
        int telefono = Integer.parseInt(jtxtTelefono.getText());

        //obtener contraseña y pasarla a un string 
        char[] password = jtxtPass.getPassword();
        for (int x = 0; x < password.length; x++) {
            clave += password[x];

        }

        if (password.length > 8 && password.length < 12) {

            usuario = new Usuario(correo, clave, nombre, pais, direccion, telefono);
            ArchivadorUsuario.agregarUsuario(usuario);

            JOptionPane.showMessageDialog(null, "Cuenta creada con satifaccion");

            this.dispose();
            frmLogin.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Contraseña debe tener entre 8 y 12 letras, donde debe incluir caracteres, letras y numeros");
            jtxtPass.requestFocus();
            //jtxtPass.selectAll();
        }


    }//GEN-LAST:event_btnRegistrarUsuarioActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jtxtPaisFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtPaisFocusGained

        jtxtPais.setText("");
    }//GEN-LAST:event_jtxtPaisFocusGained

    private void jtxtTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtTelefonoFocusGained

        jtxtTelefono.setText("");

    }//GEN-LAST:event_jtxtTelefonoFocusGained

    private void jtxtNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtNombreFocusGained

        jtxtNombre.setText("");
    }//GEN-LAST:event_jtxtNombreFocusGained

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed

        this.setVisible(false);
        frmLogin.setVisible(true);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void jtxtUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtUserFocusGained

        jtxtUser.setText("");
    }//GEN-LAST:event_jtxtUserFocusGained

    private void jtxtPassFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtPassFocusGained
        jtxtPass.setText("");
    }//GEN-LAST:event_jtxtPassFocusGained

    private void jtxtPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtPassKeyTyped

        String s1 = String.valueOf(evt.getKeyChar());

        if (!(s1.matches("[a-zA-Z0-9!@#$%^*()-_=+{}]"))) {    //return boolean
            evt.consume();
            getToolkit().beep();
        }

    }//GEN-LAST:event_jtxtPassKeyTyped

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed

        if (jRadioButton1.isSelected()) {
            jtxtPass.setEchoChar((char) 0);
        } else {
            jtxtPass.setEchoChar('*');
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // jtxtPass.setEchoChar((char) 0);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        //jtxtPass.setEchoChar('*');
    }//GEN-LAST:event_jLabel1MouseExited

    private void jtxtDireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtxtDireccionFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDireccionFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarUsuario;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jtxtDireccion;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtPais;
    private javax.swing.JPasswordField jtxtPass;
    private javax.swing.JTextField jtxtTelefono;
    private javax.swing.JTextField jtxtUser;
    private javax.swing.JLabel logBmw;
    // End of variables declaration//GEN-END:variables
}
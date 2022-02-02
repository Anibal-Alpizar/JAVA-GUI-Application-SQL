/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package capaInterfaz;

import ConexionSQL.Conexion;
import capaDatos.ArchivadorUsuario;
import capaLogica.Cliente;
import capaLogica.Factura;
import capaLogica.Parte;
import capaLogica.TipoPagoEnum;
import capaLogica.Vehiculo;
import capaLogica.VehiculoEnum;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.Statement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author aniba
 */
public class frmCuerpo extends javax.swing.JFrame implements Runnable {

    //sql
    String hora, minutos, segundos;
    Thread hilo;

    static int numeroFactura = 1;

    //public ArrayList<Parte> Lis;
    private ArrayList<Vehiculo> LisVehi;
    // private ArrayList<Cliente> LisClie;
    private ArrayList<Factura> ListFac;

    public frmCuerpo() {

        //Arrays 
        //Lis = new ArrayList<Parte>();
        LisVehi = new ArrayList<Vehiculo>();
        //  LisClie = new ArrayList<Cliente>();
        ListFac = new ArrayList<Factura>();

        initComponents();

        //sql
        txtId.setVisible(false);
        txtidCliente.setVisible(false);
        cargarTablaSQL();
        cargarTablaSQLClientes();
        cargarTablaSQLMini();

        //complementos
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/bwmLogo2.png")).getImage());

        jtableFacturasRegistradas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtableFacturasRegistradas.getTableHeader().setOpaque(false);
        jtableFacturasRegistradas.getTableHeader().setBackground(new Color(60, 141, 188));
        jtableFacturasRegistradas.getTableHeader().setForeground(new Color(255, 255, 255));
        jtableFacturasRegistradas.setRowHeight(25);

        jtableReportesClientesRegistrados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtableReportesClientesRegistrados.getTableHeader().setOpaque(false);
        jtableReportesClientesRegistrados.getTableHeader().setBackground(new Color(60, 141, 188));
        jtableReportesClientesRegistrados.getTableHeader().setForeground(new Color(255, 255, 255));
        jtableReportesClientesRegistrados.setRowHeight(25);

        jtableReporteVehiculos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtableReporteVehiculos.getTableHeader().setOpaque(false);
        jtableReporteVehiculos.getTableHeader().setBackground(new Color(60, 141, 188));
        jtableReporteVehiculos.getTableHeader().setForeground(new Color(255, 255, 255));
        jtableReporteVehiculos.setRowHeight(25);

        jtableVehiculosCompra.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtableVehiculosCompra.getTableHeader().setOpaque(false);
        jtableVehiculosCompra.getTableHeader().setBackground(new Color(60, 141, 188));
        jtableVehiculosCompra.getTableHeader().setForeground(new Color(255, 255, 255));
        jtableVehiculosCompra.setRowHeight(25);

        jtablePartesAdicionalesCompra.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtablePartesAdicionalesCompra.getTableHeader().setOpaque(false);
        jtablePartesAdicionalesCompra.getTableHeader().setBackground(new Color(60, 141, 188));
        jtablePartesAdicionalesCompra.getTableHeader().setForeground(new Color(255, 255, 255));
        jtablePartesAdicionalesCompra.setRowHeight(25);

        jtableVehiculo.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtableVehiculo.getTableHeader().setOpaque(false);
        jtableVehiculo.getTableHeader().setBackground(new Color(60, 141, 188));
        jtableVehiculo.getTableHeader().setForeground(new Color(255, 255, 255));
        jtableVehiculo.setRowHeight(25);

        jtablePieza2.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtablePieza2.getTableHeader().setOpaque(false);
        jtablePieza2.getTableHeader().setBackground(new Color(60, 141, 188));
        jtablePieza2.getTableHeader().setForeground(new Color(255, 255, 255));
        jtablePieza2.setRowHeight(25);

        jtableClientesRegistrados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtableClientesRegistrados.getTableHeader().setOpaque(false);
        jtableClientesRegistrados.getTableHeader().setBackground(new Color(60, 141, 188));
        jtableClientesRegistrados.getTableHeader().setForeground(new Color(255, 255, 255));
        jtableClientesRegistrados.setRowHeight(25);

        jtablePieza.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jtablePieza.getTableHeader().setOpaque(false);
        jtablePieza.getTableHeader().setBackground(new Color(60, 141, 188));
        jtablePieza.getTableHeader().setForeground(new Color(255, 255, 255));
        jtablePieza.setRowHeight(25);

        //archivos / ficheros
        cargarVehiculos();
        cargarFacturas();

        //fecha hora
        hilo = new Thread(this);
        hilo.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnHERE = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtUsuarioActivo = new javax.swing.JTextField();
        jtxtOnline = new javax.swing.JTextField();
        btnDashBoard = new javax.swing.JLabel();
        btnReportesPartes = new javax.swing.JLabel();
        btnMantenPartes = new javax.swing.JLabel();
        btnManteClientes = new javax.swing.JLabel();
        btnManteVehiculo = new javax.swing.JLabel();
        btnSalir = new javax.swing.JLabel();
        btnReportesVehiculos = new javax.swing.JLabel();
        btnReportesClientes = new javax.swing.JLabel();
        btnPersoFactura = new javax.swing.JLabel();
        btnReportesFacturas = new javax.swing.JLabel();
        principalHERE = new javax.swing.JPanel();
        jlabelHora = new javax.swing.JLabel();
        jlabelFecha = new javax.swing.JLabel();
        jpanelesHERE = new javax.swing.JPanel();
        jpnDash = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jpnPerso = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        txtUsuarioComprar = new javax.swing.JTextField();
        jpaneltipoClienteCompra = new javax.swing.JPanel();
        jpanelCorporativoCompra = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        txtCedJuridicaCompra = new javax.swing.JTextField();
        jpanelPersonalCompra = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        txtCedulaFisicaCompra = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jrdPersonalCompra = new javax.swing.JRadioButton();
        jrdCorporativoCompra = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtableVehiculosCompra = new javax.swing.JTable();
        txtConsultaTipoDeseado = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator29 = new javax.swing.JSeparator();
        jLabel78 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtablePartesAdicionalesCompra = new javax.swing.JTable();
        btnCotizar = new javax.swing.JButton();
        jLabel79 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        btnComprarVehiculo = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtTotalIva = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jSeparator30 = new javax.swing.JSeparator();
        jSeparator31 = new javax.swing.JSeparator();
        jpnReportesPartes = new javax.swing.JPanel();
        jpnHeader = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtablePieza1 = new javax.swing.JTable();
        jpnMantVehiculos = new javax.swing.JPanel();
        jpnHeader2 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jcbTipoVehiculo = new javax.swing.JComboBox<>();
        txtCodigoAuto = new javax.swing.JTextField();
        txtModeloAuto = new javax.swing.JTextField();
        txtPrecioAuto = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtablePieza2 = new javax.swing.JTable();
        btnAgregarVehiculo = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableVehiculo = new javax.swing.JTable();
        btnEliminarVehiculo = new javax.swing.JButton();
        btnModificarVehiculo = new javax.swing.JButton();
        btnGuardarVehiculos = new javax.swing.JButton();
        txtConsultaCodigoVehiculo = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jpnMantClientes = new javax.swing.JPanel();
        jpnHeader4 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtPaisCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        jpaneltipoCliente = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jrdPersonal = new javax.swing.JRadioButton();
        jrdCorporativo = new javax.swing.JRadioButton();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        txtidCliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtableClientesRegistrados = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnModificarCliente = new javax.swing.JButton();
        txtConsultaCliente = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jSeparator18 = new javax.swing.JSeparator();
        jpnMantPartes = new javax.swing.JPanel();
        jpnHeader1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtNoPiezas = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator23 = new javax.swing.JSeparator();
        jSeparator24 = new javax.swing.JSeparator();
        jSeparator25 = new javax.swing.JSeparator();
        jSeparator26 = new javax.swing.JSeparator();
        txtId = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtablePieza = new javax.swing.JTable();
        btnEliminarParte = new javax.swing.JButton();
        txtBuscarNoParte = new javax.swing.JTextField();
        btnModificarParte = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        jSeparator27 = new javax.swing.JSeparator();
        jSeparator28 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jpnReportesVehiculos = new javax.swing.JPanel();
        jpnHeader3 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtableReporteVehiculos = new javax.swing.JTable();
        jpnReportesCliente = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtableReportesClientesRegistrados = new javax.swing.JTable();
        jpnHeader5 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jpnFacturacion = new javax.swing.JPanel();
        jpnHeader6 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        txtNumeroFactura = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        txtFechaFactura = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jcbTipoPago = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txtDireccionFacturacion = new javax.swing.JTextField();
        txtNombreClienteFacturacion = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        txtTelefonoFacturacion = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txtPaisFacturacion = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        txtTipoCliente = new javax.swing.JTextField();
        txtSubTotalFacturacion = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txtIVA = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();
        btnComprarArchivos = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jpnReporteFacturas = new javax.swing.JPanel();
        jpnHeader7 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtableFacturasRegistradas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BMW");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHERE.setBackground(new java.awt.Color(35, 45, 49));
        btnHERE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarioIco3.png"))); // NOI18N
        btnHERE.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel2.setBackground(new java.awt.Color(54, 127, 168));
        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("¡Bienvenido!");
        jLabel2.setToolTipText("");
        jLabel2.setOpaque(true);
        btnHERE.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 50));

        jtxtUsuarioActivo.setEditable(false);
        jtxtUsuarioActivo.setBackground(new java.awt.Color(54, 127, 168));
        jtxtUsuarioActivo.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jtxtUsuarioActivo.setForeground(new java.awt.Color(255, 255, 255));
        jtxtUsuarioActivo.setBorder(null);
        jtxtUsuarioActivo.setOpaque(false);
        jtxtUsuarioActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtUsuarioActivoActionPerformed(evt);
            }
        });
        btnHERE.add(jtxtUsuarioActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 170, 30));

        jtxtOnline.setEditable(false);
        jtxtOnline.setBackground(new java.awt.Color(54, 127, 168));
        jtxtOnline.setFont(new java.awt.Font("Segoe UI Semilight", 1, 11)); // NOI18N
        jtxtOnline.setForeground(new java.awt.Color(0, 255, 0));
        jtxtOnline.setText("Online");
        jtxtOnline.setBorder(null);
        jtxtOnline.setOpaque(false);
        btnHERE.add(jtxtOnline, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, 10));

        btnDashBoard.setBackground(new java.awt.Color(34, 45, 49));
        btnDashBoard.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnDashBoard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dashboardIco.png"))); // NOI18N
        btnDashBoard.setText("Dashboard");
        btnDashBoard.setOpaque(true);
        btnDashBoard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDashBoardMouseClicked(evt);
            }
        });
        btnHERE.add(btnDashBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 290, 40));

        btnReportesPartes.setBackground(new java.awt.Color(34, 45, 49));
        btnReportesPartes.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnReportesPartes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportesPartes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporteIco.png"))); // NOI18N
        btnReportesPartes.setText("Reportes partes");
        btnReportesPartes.setOpaque(true);
        btnReportesPartes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportesPartesMouseClicked(evt);
            }
        });
        btnHERE.add(btnReportesPartes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 290, 40));

        btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
        btnMantenPartes.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnMantenPartes.setForeground(new java.awt.Color(255, 255, 255));
        btnMantenPartes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mantenimientosIco.png"))); // NOI18N
        btnMantenPartes.setText("Manten. Partes del vehiculo");
        btnMantenPartes.setOpaque(true);
        btnMantenPartes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMantenPartesMouseClicked(evt);
            }
        });
        btnHERE.add(btnMantenPartes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 290, 40));

        btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
        btnManteClientes.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnManteClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnManteClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mantenimientosIco.png"))); // NOI18N
        btnManteClientes.setText("Manten. Clientes");
        btnManteClientes.setOpaque(true);
        btnManteClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnManteClientesMouseClicked(evt);
            }
        });
        btnHERE.add(btnManteClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 290, 40));

        btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
        btnManteVehiculo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnManteVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnManteVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mantenimientosIco.png"))); // NOI18N
        btnManteVehiculo.setText("Manten. del vehiculo");
        btnManteVehiculo.setOpaque(true);
        btnManteVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnManteVehiculoMouseClicked(evt);
            }
        });
        btnHERE.add(btnManteVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 290, 40));

        btnSalir.setBackground(new java.awt.Color(34, 45, 49));
        btnSalir.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logoutIco.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setOpaque(true);
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalirMouseClicked(evt);
            }
        });
        btnHERE.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 610, 290, 40));

        btnReportesVehiculos.setBackground(new java.awt.Color(34, 45, 49));
        btnReportesVehiculos.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnReportesVehiculos.setForeground(new java.awt.Color(255, 255, 255));
        btnReportesVehiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporteIco.png"))); // NOI18N
        btnReportesVehiculos.setText("Reportes vehiculos");
        btnReportesVehiculos.setOpaque(true);
        btnReportesVehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportesVehiculosMouseClicked(evt);
            }
        });
        btnHERE.add(btnReportesVehiculos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 290, 40));

        btnReportesClientes.setBackground(new java.awt.Color(34, 45, 49));
        btnReportesClientes.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnReportesClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportesClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporteIco.png"))); // NOI18N
        btnReportesClientes.setText("Reportes clientes");
        btnReportesClientes.setOpaque(true);
        btnReportesClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportesClientesMouseClicked(evt);
            }
        });
        btnHERE.add(btnReportesClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 290, 40));

        btnPersoFactura.setBackground(new java.awt.Color(34, 45, 49));
        btnPersoFactura.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnPersoFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnPersoFactura.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/personalizarIco.png"))); // NOI18N
        btnPersoFactura.setText("Personalizacion y Facturacion ");
        btnPersoFactura.setOpaque(true);
        btnPersoFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPersoFacturaMouseClicked(evt);
            }
        });
        btnHERE.add(btnPersoFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 290, 40));

        btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
        btnReportesFacturas.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        btnReportesFacturas.setForeground(new java.awt.Color(255, 255, 255));
        btnReportesFacturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporteIco.png"))); // NOI18N
        btnReportesFacturas.setText("Reportes facturas");
        btnReportesFacturas.setOpaque(true);
        btnReportesFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportesFacturasMouseClicked(evt);
            }
        });
        btnHERE.add(btnReportesFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, 290, 40));

        jPanel1.add(btnHERE, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 660));

        principalHERE.setBackground(new java.awt.Color(60, 141, 188));
        principalHERE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlabelHora.setText("0:00");
        principalHERE.add(jlabelHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        jlabelFecha.setText("yy/mn/dd");
        principalHERE.add(jlabelFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, -1, -1));

        jPanel1.add(principalHERE, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 740, 50));

        jpanelesHERE.setLayout(new java.awt.CardLayout());

        jpnDash.setBackground(new java.awt.Color(236, 239, 244));
        jpnDash.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel10.setText("Dashboard");
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jpnDash.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 100, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText(" Version beta");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jpnDash.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 60, 20));

        jPanel5.setBackground(new java.awt.Color(210, 214, 223));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dashboardIco.png"))); // NOI18N
        jLabel11.setText("Home >");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Dashboard");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, -2, 100, 30));

        jpnDash.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 690, 30));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(0, 167, 89));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/githubIco.png"))); // NOI18N
        jLabel6.setOpaque(true);
        jPanel7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 120));

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel15.setText("Desarrollado por: ");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        jLabel16.setBackground(new java.awt.Color(102, 102, 102));
        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("https://github.com/Anibal-Alpizar");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 170, 20));

        jpnDash.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 330, 120));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(0, 192, 239));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventasIco.png"))); // NOI18N
        jLabel4.setOpaque(true);
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 120));
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jLabel14.setText("Incremento del 17% ");
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 130, 20));

        jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("en ventas");
        jLabel18.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 130, 20));

        jpnDash.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 320, 120));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(243, 156, 15));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clientesIco(2).png"))); // NOI18N
        jLabel8.setOpaque(true);
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 120));
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jLabel17.setText("Contamos con una plantilla");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel8.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 170, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("de 125.000 emplados");
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 130, 20));

        jpnDash.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 320, 120));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel80.setBackground(new java.awt.Color(255, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gmailIco.png"))); // NOI18N
        jPanel2.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 120));

        jLabel81.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        jLabel81.setText("anibal.alpizar14@gmail.com");
        jPanel2.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        jLabel82.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel82.setText("Integrantes:");
        jPanel2.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        jpnDash.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 320, 120));

        jpanelesHERE.add(jpnDash, "card2");

        jpnPerso.setBackground(new java.awt.Color(236, 239, 244));
        jpnPerso.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(236, 239, 244));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Informacion personal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 13), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarioIco.png"))); // NOI18N
        jLabel46.setText("Usuario:");
        jPanel16.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 38, 130, 30));

        txtUsuarioComprar.setEditable(false);
        txtUsuarioComprar.setBackground(new java.awt.Color(236, 239, 244));
        txtUsuarioComprar.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        txtUsuarioComprar.setBorder(null);
        jPanel16.add(txtUsuarioComprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 280, 20));

        jpaneltipoClienteCompra.setBackground(new java.awt.Color(236, 239, 244));
        jpaneltipoClienteCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpanelCorporativoCompra.setBackground(new java.awt.Color(236, 239, 244));
        jpanelCorporativoCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Digite la cedula juridica");
        jpanelCorporativoCompra.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 130, 20));
        jpanelCorporativoCompra.add(txtCedJuridicaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 130, 30));

        jpaneltipoClienteCompra.add(jpanelCorporativoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 130, 60));

        jpanelPersonalCompra.setBackground(new java.awt.Color(236, 239, 244));
        jpanelPersonalCompra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Digite la cedula fisica");
        jpanelPersonalCompra.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 140, -1));
        jpanelPersonalCompra.add(txtCedulaFisicaCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 140, 30));

        jpaneltipoClienteCompra.add(jpanelPersonalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 140, 60));

        jLabel49.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/empleadosIco.png"))); // NOI18N
        jLabel49.setText("Tipo");
        jpaneltipoClienteCompra.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 80, 30));

        jrdPersonalCompra.setBackground(new java.awt.Color(236, 239, 244));
        buttonGroup1.add(jrdPersonalCompra);
        jrdPersonalCompra.setText("Personal");
        jrdPersonalCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrdPersonalCompraMouseClicked(evt);
            }
        });
        jpaneltipoClienteCompra.add(jrdPersonalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 80, -1));

        jrdCorporativoCompra.setBackground(new java.awt.Color(236, 239, 244));
        buttonGroup1.add(jrdCorporativoCompra);
        jrdCorporativoCompra.setText("Corporativo");
        jrdCorporativoCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrdCorporativoCompraMouseClicked(evt);
            }
        });
        jpaneltipoClienteCompra.add(jrdCorporativoCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jPanel16.add(jpaneltipoClienteCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 620, 70));
        jPanel16.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 290, -1));

        jpnPerso.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 690, 150));

        jPanel17.setBackground(new java.awt.Color(236, 239, 244));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Escoge y personaliza tu vehiculo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 13), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtableVehiculosCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtableVehiculosCompra.setFocusable(false);
        jtableVehiculosCompra.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtableVehiculosCompra.setRowHeight(25);
        jtableVehiculosCompra.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jtableVehiculosCompra.setShowVerticalLines(false);
        jtableVehiculosCompra.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(jtableVehiculosCompra);

        jPanel17.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 580, 100));

        txtConsultaTipoDeseado.setBackground(new java.awt.Color(236, 239, 244));
        txtConsultaTipoDeseado.setBorder(null);
        txtConsultaTipoDeseado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConsultaTipoDeseadoKeyTyped(evt);
            }
        });
        jPanel17.add(txtConsultaTipoDeseado, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 150, 20));

        jLabel50.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        jLabel50.setText("¿Digita el tipo de vehiculo que deseas?");
        jPanel17.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        jLabel51.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel51.setText("Fosil");
        jPanel17.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, -1, -1));

        jLabel52.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel52.setText("Electrico");
        jPanel17.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, -1, -1));

        jLabel53.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel53.setText("Hibrido");
        jPanel17.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, -1, -1));
        jPanel17.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 580, 10));
        jPanel17.add(jSeparator29, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 52, 150, 10));

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cocheIco.png"))); // NOI18N
        jPanel17.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 40, 30));

        jpnPerso.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 690, 210));

        jPanel18.setBackground(new java.awt.Color(236, 239, 244));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Partes adicionales - Seleccione el extra que desea agregar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 13), new java.awt.Color(102, 102, 120))); // NOI18N
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtablePartesAdicionalesCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "No pieza", "Tipo", "Title 4", "precio", "estado"
            }
        ));
        jtablePartesAdicionalesCompra.setToolTipText("");
        jtablePartesAdicionalesCompra.setFocusable(false);
        jtablePartesAdicionalesCompra.setRowHeight(18);
        jtablePartesAdicionalesCompra.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jtablePartesAdicionalesCompra.setShowVerticalLines(false);
        jtablePartesAdicionalesCompra.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(jtablePartesAdicionalesCompra);

        jPanel18.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 590, 90));

        btnCotizar.setBackground(new java.awt.Color(210, 214, 223));
        btnCotizar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnCotizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cotizarPrecioIco2.png"))); // NOI18N
        btnCotizar.setText("Cotizar precio");
        btnCotizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCotizarActionPerformed(evt);
            }
        });
        jPanel18.add(btnCotizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, -1, 40));

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mecanicoIco.png"))); // NOI18N
        jPanel18.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 40, 40));

        jpnPerso.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 690, 180));

        txtSubTotal.setEditable(false);
        txtSubTotal.setBackground(new java.awt.Color(236, 239, 244));
        txtSubTotal.setBorder(null);
        txtSubTotal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtSubTotalPropertyChange(evt);
            }
        });
        jpnPerso.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 570, 90, 20));

        jLabel54.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pago-en-efectivoIco.png"))); // NOI18N
        jLabel54.setText("Total + IVA");
        jpnPerso.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 570, 120, -1));

        btnComprarVehiculo.setBackground(new java.awt.Color(210, 214, 223));
        btnComprarVehiculo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnComprarVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pagar2Ico.png"))); // NOI18N
        btnComprarVehiculo.setText("Facturar");
        btnComprarVehiculo.setEnabled(false);
        btnComprarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarVehiculoActionPerformed(evt);
            }
        });
        jpnPerso.add(btnComprarVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 560, 150, 40));

        jLabel58.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel58.setText("\t$");
        jpnPerso.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 570, 30, 20));

        jLabel59.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sinIvaIco.png"))); // NOI18N
        jLabel59.setText("Sub-total");
        jpnPerso.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 100, -1));

        txtTotalIva.setEditable(false);
        txtTotalIva.setBackground(new java.awt.Color(236, 239, 244));
        txtTotalIva.setBorder(null);
        jpnPerso.add(txtTotalIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 570, 170, -1));

        jLabel60.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel60.setText("\t$");
        jpnPerso.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 570, 40, 20));
        jpnPerso.add(jSeparator30, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 590, 90, -1));
        jpnPerso.add(jSeparator31, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 590, 170, -1));

        jpanelesHERE.add(jpnPerso, "card3");

        jpnReportesPartes.setBackground(new java.awt.Color(236, 239, 244));
        jpnReportesPartes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnHeader.setBackground(new java.awt.Color(54, 71, 96));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Aqui podras visualizar las partes disponibles en el momento");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Reportes de partes");

        javax.swing.GroupLayout jpnHeaderLayout = new javax.swing.GroupLayout(jpnHeader);
        jpnHeader.setLayout(jpnHeaderLayout);
        jpnHeaderLayout.setHorizontalGroup(
            jpnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHeaderLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel23)
                .addContainerGap(350, Short.MAX_VALUE))
            .addGroup(jpnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnHeaderLayout.createSequentialGroup()
                    .addGap(74, 74, 74)
                    .addComponent(jLabel24)
                    .addContainerGap(366, Short.MAX_VALUE)))
        );
        jpnHeaderLayout.setVerticalGroup(
            jpnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHeaderLayout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(54, 54, 54))
            .addGroup(jpnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnHeaderLayout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel24)
                    .addContainerGap(70, Short.MAX_VALUE)))
        );

        jpnReportesPartes.add(jpnHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 150));

        jtablePieza1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jtablePieza1.setForeground(new java.awt.Color(51, 51, 51));
        jtablePieza1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No piezas", "Tipo", "modelo", "estado", "precio"
            }
        ));
        jtablePieza1.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jtablePieza1);

        jpnReportesPartes.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 650, 160));

        jpanelesHERE.add(jpnReportesPartes, "card4");

        jpnMantVehiculos.setBackground(new java.awt.Color(236, 239, 244));
        jpnMantVehiculos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnMantVehiculosMouseClicked(evt);
            }
        });
        jpnMantVehiculos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnHeader2.setBackground(new java.awt.Color(54, 71, 96));
        jpnHeader2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Aqui podras agregar, eliminar, modificar y consultar los vehiculos en cualquier momento!");
        jpnHeader2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mantenimientoAutosIco.png"))); // NOI18N
        jLabel28.setText("Mantenimiento de vehiculos");
        jpnHeader2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jpnMantVehiculos.add(jpnHeader2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 120));

        jPanel11.setBackground(new java.awt.Color(236, 239, 244));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo vehiculo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 13), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setBackground(new java.awt.Color(54, 127, 168));
        jLabel29.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(54, 71, 96));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/codigoIco.png"))); // NOI18N
        jLabel29.setText("Codigo del Auto");
        jPanel11.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(54, 71, 96));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modeloIco2.png"))); // NOI18N
        jLabel30.setText("Modelo");
        jPanel11.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 160, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(54, 71, 96));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/precioIco.png"))); // NOI18N
        jLabel31.setText("Precio");
        jPanel11.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 160, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(54, 71, 96));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tipoAutoIco.png"))); // NOI18N
        jLabel32.setText("Tipo");
        jPanel11.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 130, 30));

        jPanel11.add(jcbTipoVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 100, 30));

        txtCodigoAuto.setBackground(new java.awt.Color(236, 239, 244));
        txtCodigoAuto.setBorder(null);
        jPanel11.add(txtCodigoAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 150, 20));

        txtModeloAuto.setBackground(new java.awt.Color(236, 239, 244));
        txtModeloAuto.setBorder(null);
        jPanel11.add(txtModeloAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 150, 20));

        txtPrecioAuto.setBackground(new java.awt.Color(236, 239, 244));
        txtPrecioAuto.setBorder(null);
        jPanel11.add(txtPrecioAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 150, 20));

        jPanel12.setBackground(new java.awt.Color(236, 239, 244));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Partes extras para el vehiculo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 13), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtablePieza2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Tipo"
            }
        ));
        jtablePieza2.setFocusable(false);
        jtablePieza2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtablePieza2.setRowHeight(25);
        jtablePieza2.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jtablePieza2.setShowVerticalLines(false);
        jtablePieza2.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jtablePieza2);

        jPanel12.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 290, 90));

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 320, 160));

        btnAgregarVehiculo.setBackground(new java.awt.Color(210, 214, 223));
        btnAgregarVehiculo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnAgregarVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregarAutoIco.png"))); // NOI18N
        btnAgregarVehiculo.setText("Agregar vehiculo");
        btnAgregarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarVehiculoActionPerformed(evt);
            }
        });
        jPanel11.add(btnAgregarVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 170, 50));
        jPanel11.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 150, 10));
        jPanel11.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 150, 10));
        jPanel11.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 150, 10));

        jpnMantVehiculos.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 700, 250));

        jPanel13.setBackground(new java.awt.Color(236, 239, 244));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vehiculos disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 13), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtableVehiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Modelo", "Precio", "Tipo", "Partes extras"
            }
        ));
        jtableVehiculo.setFocusable(false);
        jtableVehiculo.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtableVehiculo.setRowHeight(25);
        jtableVehiculo.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jtableVehiculo.setShowVerticalLines(false);
        jtableVehiculo.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtableVehiculo);

        jPanel13.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 500, 100));

        btnEliminarVehiculo.setBackground(new java.awt.Color(210, 214, 223));
        btnEliminarVehiculo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnEliminarVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deleteIco.png"))); // NOI18N
        btnEliminarVehiculo.setText("Eliminar Vehiculo");
        btnEliminarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarVehiculoActionPerformed(evt);
            }
        });
        jPanel13.add(btnEliminarVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 160, 40));

        btnModificarVehiculo.setBackground(new java.awt.Color(210, 214, 223));
        btnModificarVehiculo.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnModificarVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificarIco.png"))); // NOI18N
        btnModificarVehiculo.setText("Modificar vehiculo");
        btnModificarVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarVehiculoActionPerformed(evt);
            }
        });
        jPanel13.add(btnModificarVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 160, 40));

        btnGuardarVehiculos.setBackground(new java.awt.Color(210, 214, 223));
        btnGuardarVehiculos.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnGuardarVehiculos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/saveIco.png"))); // NOI18N
        btnGuardarVehiculos.setText("Guardar tabla");
        btnGuardarVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVehiculosActionPerformed(evt);
            }
        });
        jPanel13.add(btnGuardarVehiculos, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 160, 50));

        txtConsultaCodigoVehiculo.setBackground(new java.awt.Color(236, 239, 244));
        txtConsultaCodigoVehiculo.setBorder(null);
        txtConsultaCodigoVehiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConsultaCodigoVehiculoKeyTyped(evt);
            }
        });
        jPanel13.add(txtConsultaCodigoVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 160, 20));

        jLabel55.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(54, 71, 96));
        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscarAuto.png"))); // NOI18N
        jLabel55.setText("Hacer una busqueda por codigo");
        jPanel13.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 250, -1));
        jPanel13.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 160, 10));

        jpnMantVehiculos.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 700, 200));

        jpanelesHERE.add(jpnMantVehiculos, "card5");

        jpnMantClientes.setBackground(new java.awt.Color(236, 239, 244));
        jpnMantClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnHeader4.setBackground(new java.awt.Color(54, 71, 96));
        jpnHeader4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Aqui podras agregar, eliminar, modificar y consultar los clientes en cualquier momento");
        jpnHeader4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mantenimientoClieenteIco.png"))); // NOI18N
        jLabel36.setText("Mantenimiento de clientes");
        jpnHeader4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jpnMantClientes.add(jpnHeader4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 120));

        jPanel14.setBackground(new java.awt.Color(236, 239, 244));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setBackground(new java.awt.Color(54, 127, 168));
        jLabel37.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(54, 71, 96));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nameIco.png"))); // NOI18N
        jLabel37.setText("Nombre completo");
        jPanel14.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, -1));

        jLabel38.setBackground(new java.awt.Color(54, 127, 168));
        jLabel38.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(54, 71, 96));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paisIco3.png"))); // NOI18N
        jLabel38.setText("Pais de origen");
        jPanel14.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 120, -1));

        jLabel39.setBackground(new java.awt.Color(54, 127, 168));
        jLabel39.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(54, 71, 96));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/direccionIco.png"))); // NOI18N
        jLabel39.setText("Direccion");
        jPanel14.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 80, 120, 30));

        jLabel40.setBackground(new java.awt.Color(54, 127, 168));
        jLabel40.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(54, 71, 96));
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/teleIco.png"))); // NOI18N
        jLabel40.setText("Telefono");
        jPanel14.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 120, -1));

        txtNombreCliente.setBackground(new java.awt.Color(236, 239, 244));
        txtNombreCliente.setBorder(null);
        jPanel14.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 160, 20));

        txtPaisCliente.setBackground(new java.awt.Color(236, 239, 244));
        txtPaisCliente.setBorder(null);
        jPanel14.add(txtPaisCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 160, 20));

        txtDireccionCliente.setBackground(new java.awt.Color(236, 239, 244));
        txtDireccionCliente.setBorder(null);
        jPanel14.add(txtDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 200, 20));

        txtTelefonoCliente.setBackground(new java.awt.Color(236, 239, 244));
        txtTelefonoCliente.setBorder(null);
        jPanel14.add(txtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 200, 20));

        jpaneltipoCliente.setBackground(new java.awt.Color(236, 239, 244));
        jpaneltipoCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel41.setBackground(new java.awt.Color(54, 127, 168));
        jLabel41.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(54, 71, 96));
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tipoEmpleadoIco.png"))); // NOI18N
        jLabel41.setText("Tipo");
        jpaneltipoCliente.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 50));

        jrdPersonal.setBackground(new java.awt.Color(236, 239, 244));
        buttonGroup1.add(jrdPersonal);
        jrdPersonal.setForeground(new java.awt.Color(54, 71, 96));
        jrdPersonal.setText("Personal");
        jrdPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrdPersonalMouseClicked(evt);
            }
        });
        jpaneltipoCliente.add(jrdPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 80, -1));

        jrdCorporativo.setBackground(new java.awt.Color(236, 239, 244));
        buttonGroup1.add(jrdCorporativo);
        jrdCorporativo.setForeground(new java.awt.Color(54, 71, 96));
        jrdCorporativo.setText("Corporativo");
        jrdCorporativo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrdCorporativoMouseClicked(evt);
            }
        });
        jpaneltipoCliente.add(jrdCorporativo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, -1, -1));
        jpaneltipoCliente.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));
        jpaneltipoCliente.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));
        jpaneltipoCliente.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));
        jpaneltipoCliente.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        jPanel14.add(jpaneltipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 620, 70));
        jPanel14.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 160, 10));
        jPanel14.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, -1));
        jPanel14.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, -1, -1));
        jPanel14.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 160, 10));
        jPanel14.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));
        jPanel14.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 200, 10));
        jPanel14.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 200, 20));
        jPanel14.add(txtidCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 40, -1));

        jButton1.setBackground(new java.awt.Color(210, 214, 223));
        jButton1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/saveIco.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 120, 40));

        jpnMantClientes.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 720, 270));

        jPanel15.setBackground(new java.awt.Color(236, 239, 244));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes registrados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtableClientesRegistrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nombre", "Pais de origen", "Direccion", "Telefono", "Tipo cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtableClientesRegistrados.setFocusable(false);
        jtableClientesRegistrados.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtableClientesRegistrados.setRowHeight(25);
        jtableClientesRegistrados.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jtableClientesRegistrados.setShowVerticalLines(false);
        jtableClientesRegistrados.getTableHeader().setReorderingAllowed(false);
        jtableClientesRegistrados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtableClientesRegistradosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jtableClientesRegistrados);

        jPanel15.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 550, 110));

        btnEliminar.setBackground(new java.awt.Color(210, 214, 223));
        btnEliminar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deleteIco.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel15.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 120, 40));

        btnModificarCliente.setBackground(new java.awt.Color(210, 214, 223));
        btnModificarCliente.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnModificarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificarIco.png"))); // NOI18N
        btnModificarCliente.setText("Modificar");
        btnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarClienteActionPerformed(evt);
            }
        });
        jPanel15.add(btnModificarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 120, 40));

        txtConsultaCliente.setBackground(new java.awt.Color(236, 239, 244));
        txtConsultaCliente.setBorder(null);
        txtConsultaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConsultaClienteKeyTyped(evt);
            }
        });
        jPanel15.add(txtConsultaCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 160, 20));

        jLabel56.setFont(new java.awt.Font("Segoe UI Symbol", 0, 15)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(54, 71, 96));
        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscarClienteIco.png"))); // NOI18N
        jLabel56.setText("Hacer una busqueda por id");
        jPanel15.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 290, -1));
        jPanel15.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 160, 10));

        jpnMantClientes.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 720, 210));

        jpanelesHERE.add(jpnMantClientes, "card6");

        jpnMantPartes.setBackground(new java.awt.Color(236, 239, 244));
        jpnMantPartes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnHeader1.setBackground(new java.awt.Color(54, 71, 96));
        jpnHeader1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Aqui podras agregar, eliminar, modificar y consultar las partes en cualquier momento!");
        jpnHeader1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mantenimientoAutosIco.png"))); // NOI18N
        jLabel26.setText("Mantenimiento de partes");
        jpnHeader1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jpnMantPartes.add(jpnHeader1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 120));

        jPanel4.setBackground(new java.awt.Color(236, 239, 244));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva parte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 13), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(54, 127, 168));
        jLabel7.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(54, 71, 96));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/codigoIco.png"))); // NOI18N
        jLabel7.setText("Numero parte");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 140, -1));

        txtNoPiezas.setBackground(new java.awt.Color(236, 239, 244));
        txtNoPiezas.setBorder(null);
        jPanel4.add(txtNoPiezas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 210, -1));

        jLabel19.setBackground(new java.awt.Color(54, 127, 168));
        jLabel19.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(54, 71, 96));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tipoParteIco.png"))); // NOI18N
        jLabel19.setText("Tipo");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, -1));

        txtTipo.setBackground(new java.awt.Color(236, 239, 244));
        txtTipo.setBorder(null);
        jPanel4.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 210, -1));

        jLabel20.setBackground(new java.awt.Color(54, 127, 168));
        jLabel20.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(54, 71, 96));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modeloIco2.png"))); // NOI18N
        jLabel20.setText("Modelo");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 120, -1));

        txtModelo.setBackground(new java.awt.Color(236, 239, 244));
        txtModelo.setBorder(null);
        jPanel4.add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 210, -1));

        jLabel21.setBackground(new java.awt.Color(54, 127, 168));
        jLabel21.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(54, 71, 96));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/precioIco.png"))); // NOI18N
        jLabel21.setText("Precio");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, -1));

        txtPrecio.setBackground(new java.awt.Color(236, 239, 244));
        txtPrecio.setBorder(null);
        jPanel4.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 210, -1));

        jLabel22.setBackground(new java.awt.Color(54, 127, 168));
        jLabel22.setFont(new java.awt.Font("Segoe UI Symbol", 0, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(54, 71, 96));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pagar2Ico.png"))); // NOI18N
        jLabel22.setText("Estado");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 120, -1));

        txtEstado.setBackground(new java.awt.Color(236, 239, 244));
        txtEstado.setBorder(null);
        jPanel4.add(txtEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 210, -1));
        jPanel4.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 210, -1));
        jPanel4.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));
        jPanel4.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));
        jPanel4.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 92, 210, 0));
        jPanel4.add(jSeparator23, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 92, 210, 10));
        jPanel4.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 132, 210, 10));
        jPanel4.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 172, 210, 10));
        jPanel4.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 212, 210, 10));
        jPanel4.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 40, 30));

        btnGuardar.setBackground(new java.awt.Color(210, 214, 223));
        btnGuardar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/saveIco.png"))); // NOI18N
        btnGuardar.setText("Guardar datos");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, -1, 40));

        jpnMantPartes.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 710, 240));

        jPanel9.setBackground(new java.awt.Color(236, 239, 244));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Partes disponibles", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semilight", 0, 13), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtablePieza.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "No piezas", "Tipo", "modelo", "estado", "precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtablePieza.setFocusable(false);
        jtablePieza.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtablePieza.setRowHeight(25);
        jtablePieza.setSelectionBackground(new java.awt.Color(204, 204, 204));
        jtablePieza.getTableHeader().setReorderingAllowed(false);
        jtablePieza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtablePiezaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtablePieza);

        jPanel9.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 540, 130));

        btnEliminarParte.setBackground(new java.awt.Color(210, 214, 223));
        btnEliminarParte.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnEliminarParte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deleteIco.png"))); // NOI18N
        btnEliminarParte.setText("Eliminar fila");
        btnEliminarParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarParteActionPerformed(evt);
            }
        });
        jPanel9.add(btnEliminarParte, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 140, -1));

        txtBuscarNoParte.setBackground(new java.awt.Color(236, 239, 244));
        txtBuscarNoParte.setBorder(null);
        txtBuscarNoParte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarNoParteKeyTyped(evt);
            }
        });
        jPanel9.add(txtBuscarNoParte, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 170, 20));

        btnModificarParte.setBackground(new java.awt.Color(210, 214, 223));
        btnModificarParte.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        btnModificarParte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificarIco.png"))); // NOI18N
        btnModificarParte.setText("Modificar fila");
        btnModificarParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarParteActionPerformed(evt);
            }
        });
        jPanel9.add(btnModificarParte, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 140, 40));

        jLabel57.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(54, 71, 96));
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscarParteIco.png"))); // NOI18N
        jLabel57.setText("Hacer una busqueda por id");
        jPanel9.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 270, -1));
        jPanel9.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));
        jPanel9.add(jSeparator28, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, 170, 10));

        jButton2.setBackground(new java.awt.Color(210, 214, 223));
        jButton2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/broomIco.png"))); // NOI18N
        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 140, 40));

        jpnMantPartes.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 710, 210));

        jpanelesHERE.add(jpnMantPartes, "card7");

        jpnReportesVehiculos.setBackground(new java.awt.Color(236, 239, 244));
        jpnReportesVehiculos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnHeader3.setBackground(new java.awt.Color(54, 71, 96));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Aqui podras visualizar los vehiculos disponibles en el momento");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporteIco2.png"))); // NOI18N
        jLabel34.setText("Reportes de vehiculos");

        javax.swing.GroupLayout jpnHeader3Layout = new javax.swing.GroupLayout(jpnHeader3);
        jpnHeader3.setLayout(jpnHeader3Layout);
        jpnHeader3Layout.setHorizontalGroup(
            jpnHeader3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHeader3Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel33)
                .addContainerGap(266, Short.MAX_VALUE))
            .addGroup(jpnHeader3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnHeader3Layout.createSequentialGroup()
                    .addGap(74, 74, 74)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(238, Short.MAX_VALUE)))
        );
        jpnHeader3Layout.setVerticalGroup(
            jpnHeader3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHeader3Layout.createSequentialGroup()
                .addContainerGap(82, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(52, 52, 52))
            .addGroup(jpnHeader3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnHeader3Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel34)
                    .addContainerGap(54, Short.MAX_VALUE)))
        );

        jpnReportesVehiculos.add(jpnHeader3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 150));

        jtableReporteVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtableReporteVehiculos.setFocusable(false);
        jtableReporteVehiculos.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtableReporteVehiculos.setRowHeight(25);
        jtableReporteVehiculos.setSelectionBackground(new java.awt.Color(232, 56, 95));
        jtableReporteVehiculos.setShowVerticalLines(false);
        jtableReporteVehiculos.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jtableReporteVehiculos);

        jpnReportesVehiculos.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 680, 420));

        jpanelesHERE.add(jpnReportesVehiculos, "card4");

        jpnReportesCliente.setBackground(new java.awt.Color(236, 239, 244));
        jpnReportesCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtableReportesClientesRegistrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Direccion", "Telefono", "Tipo cliente"
            }
        ));
        jtableReportesClientesRegistrados.setFocusable(false);
        jtableReportesClientesRegistrados.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtableReportesClientesRegistrados.setRowHeight(25);
        jtableReportesClientesRegistrados.setSelectionBackground(new java.awt.Color(232, 56, 95));
        jtableReportesClientesRegistrados.setShowVerticalLines(false);
        jtableReportesClientesRegistrados.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(jtableReportesClientesRegistrados);

        jpnReportesCliente.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 660, 420));

        jpnHeader5.setBackground(new java.awt.Color(54, 71, 96));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Aqui podras visualizar los clientes registrados ");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporteIco2.png"))); // NOI18N
        jLabel45.setText("Reportes de clientes");

        javax.swing.GroupLayout jpnHeader5Layout = new javax.swing.GroupLayout(jpnHeader5);
        jpnHeader5.setLayout(jpnHeader5Layout);
        jpnHeader5Layout.setHorizontalGroup(
            jpnHeader5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHeader5Layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel44)
                .addContainerGap(359, Short.MAX_VALUE))
            .addGroup(jpnHeader5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnHeader5Layout.createSequentialGroup()
                    .addGap(74, 74, 74)
                    .addComponent(jLabel45)
                    .addContainerGap(276, Short.MAX_VALUE)))
        );
        jpnHeader5Layout.setVerticalGroup(
            jpnHeader5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHeader5Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(jLabel44)
                .addGap(48, 48, 48))
            .addGroup(jpnHeader5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnHeader5Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel45)
                    .addContainerGap(54, Short.MAX_VALUE)))
        );

        jpnReportesCliente.add(jpnHeader5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 150));

        jpanelesHERE.add(jpnReportesCliente, "card9");

        jpnFacturacion.setBackground(new java.awt.Color(236, 239, 244));
        jpnFacturacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnHeader6.setBackground(new java.awt.Color(54, 71, 96));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("A continuacion se presenta tu factura, gracias por confiar !");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/facturaIco.png"))); // NOI18N
        jLabel62.setText("Gracias por comprar en BMW");

        javax.swing.GroupLayout jpnHeader6Layout = new javax.swing.GroupLayout(jpnHeader6);
        jpnHeader6.setLayout(jpnHeader6Layout);
        jpnHeader6Layout.setHorizontalGroup(
            jpnHeader6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHeader6Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(jLabel61)
                .addContainerGap(502, Short.MAX_VALUE))
            .addGroup(jpnHeader6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnHeader6Layout.createSequentialGroup()
                    .addGap(74, 74, 74)
                    .addComponent(jLabel62)
                    .addContainerGap(344, Short.MAX_VALUE)))
        );
        jpnHeader6Layout.setVerticalGroup(
            jpnHeader6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnHeader6Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(jLabel61)
                .addGap(64, 64, 64))
            .addGroup(jpnHeader6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpnHeader6Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel62)
                    .addContainerGap(70, Short.MAX_VALUE)))
        );

        jpnFacturacion.add(jpnHeader6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel19.setBackground(new java.awt.Color(236, 239, 244));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/facturaIco2.png"))); // NOI18N
        jLabel64.setText("Numero Factura:");
        jPanel19.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtNumeroFactura.setEditable(false);
        jPanel19.add(txtNumeroFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 190, -1));

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dateFecha.png"))); // NOI18N
        jLabel66.setText("Fecha: ");
        jPanel19.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        txtFechaFactura.setEditable(false);
        txtFechaFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel19.add(txtFechaFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 120, 30));

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tipoPagoIco.png"))); // NOI18N
        jLabel67.setText("Tipo pago: ");
        jPanel19.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 120, -1));

        jcbTipoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CONTADO", "CREDITO" }));
        jcbTipoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoPagoActionPerformed(evt);
            }
        });
        jPanel19.add(jcbTipoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 110, -1));

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/yoIco.png"))); // NOI18N
        jLabel69.setText("Vendedor:");
        jPanel19.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 120, -1));

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jTextField6.setText("Anibal Alpizar Campos");
        jPanel19.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 270, -1));

        jpnFacturacion.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 700, 130));

        jPanel20.setBackground(new java.awt.Color(236, 239, 244));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/clienteIco.png"))); // NOI18N
        jLabel63.setText("Cliente: ");
        jPanel20.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, -1));

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/direccionIco.png"))); // NOI18N
        jLabel65.setText("Direccion:");
        jPanel20.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 110, -1));

        txtDireccionFacturacion.setEditable(false);
        jPanel20.add(txtDireccionFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 280, -1));

        txtNombreClienteFacturacion.setEditable(false);
        jPanel20.add(txtNombreClienteFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 280, -1));

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/teleIco.png"))); // NOI18N
        jLabel68.setText("Telefono de contacto ");
        jPanel20.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 180, -1));

        txtTelefonoFacturacion.setEditable(false);
        jPanel20.add(txtTelefonoFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 160, -1));

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/paisIco3.png"))); // NOI18N
        jLabel73.setText("Pais: ");
        jPanel20.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 110, -1));

        txtPaisFacturacion.setEditable(false);
        jPanel20.add(txtPaisFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 280, -1));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tipoEmpleadoIco.png"))); // NOI18N
        jLabel75.setText("Tipo cliente");
        jPanel20.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 110, -1));

        txtTipoCliente.setEditable(false);
        jPanel20.add(txtTipoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 210, -1));

        jpnFacturacion.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 700, 170));
        jpnFacturacion.add(txtSubTotalFacturacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 490, 140, -1));

        jLabel71.setText("IVA:");
        jpnFacturacion.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 520, 50, -1));
        jpnFacturacion.add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 520, 140, -1));

        jLabel72.setText("Total a pagar:");
        jpnFacturacion.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 580, -1, -1));
        jpnFacturacion.add(txtTotalPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 580, 140, -1));

        btnComprarArchivos.setBackground(new java.awt.Color(210, 214, 223));
        btnComprarArchivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pagarIco.png"))); // NOI18N
        btnComprarArchivos.setText("Finalizar compra");
        btnComprarArchivos.setToolTipText("");
        btnComprarArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarArchivosActionPerformed(evt);
            }
        });
        jpnFacturacion.add(btnComprarArchivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 180, 60));

        jLabel74.setText("Descuentos:");
        jpnFacturacion.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 550, -1, -1));
        jpnFacturacion.add(txtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 550, 140, -1));

        jLabel83.setText("Sub.Total:");
        jpnFacturacion.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 490, -1, 20));

        jpanelesHERE.add(jpnFacturacion, "card10");

        jpnReporteFacturas.setBackground(new java.awt.Color(236, 239, 244));
        jpnReporteFacturas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnHeader7.setBackground(new java.awt.Color(54, 71, 96));
        jpnHeader7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("A continuacion se mostraran todas las facturas registradas en el sistema");
        jpnHeader7.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/reporteIco2.png"))); // NOI18N
        jLabel77.setText("Facturas registradas");
        jpnHeader7.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 11, 663, -1));

        jpnReporteFacturas.add(jpnHeader7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 150));

        jtableFacturasRegistradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Numero factura", "Cliente", "Telefono", "Total facturado"
            }
        ));
        jtableFacturasRegistradas.setFocusable(false);
        jtableFacturasRegistradas.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jtableFacturasRegistradas.setRowHeight(25);
        jtableFacturasRegistradas.setSelectionBackground(new java.awt.Color(232, 56, 95));
        jtableFacturasRegistradas.setShowVerticalLines(false);
        jtableFacturasRegistradas.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(jtableFacturasRegistradas);

        jpnReporteFacturas.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 168, 699, 420));

        jpanelesHERE.add(jpnReporteFacturas, "card11");

        jPanel1.add(jpanelesHERE, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 740, 610));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtUsuarioActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtUsuarioActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtUsuarioActivoActionPerformed


    private void btnDashBoardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDashBoardMouseClicked

        btnDashBoard.setBackground(new java.awt.Color(44, 59, 66));
        jpnDash.setVisible(true);

        btnPersoFactura.setBackground(new java.awt.Color(34, 45, 49));
        jpnPerso.setVisible(false);

        btnReportesPartes.setBackground(new java.awt.Color(34, 45, 49));
        jpnReportesPartes.setVisible(false);

        btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantVehiculos.setVisible(false);

        btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantClientes.setVisible(false);

        btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantPartes.setVisible(false);

        btnReportesClientes.setBackground(new java.awt.Color(34, 45, 49));
        jpnReportesCliente.setVisible(false);

        btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
        jpnReporteFacturas.setVisible(false);

        jpnFacturacion.setVisible(false);


    }//GEN-LAST:event_btnDashBoardMouseClicked


    private void btnReportesPartesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesPartesMouseClicked

        btnDashBoard.setBackground(new java.awt.Color(34, 45, 49));
        jpnDash.setVisible(false);

        btnPersoFactura.setBackground(new java.awt.Color(34, 45, 49));
        jpnPerso.setVisible(false);

        btnReportesClientes.setBackground(new java.awt.Color(34, 45, 49));
        jpnReportesCliente.setVisible(false);

        btnReportesPartes.setBackground(new java.awt.Color(44, 59, 66));
        jpnReportesPartes.setVisible(true);

        btnReportesVehiculos.setBackground(new java.awt.Color(34, 45, 49));
        jpnReportesVehiculos.setVisible(false);

        btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantVehiculos.setVisible(false);

        btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantClientes.setVisible(false);

        jpnFacturacion.setVisible(false);

        btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
        jpnReporteFacturas.setVisible(false);

        btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantPartes.setVisible(false);


    }//GEN-LAST:event_btnReportesPartesMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        //usuario conectado
        jtxtUsuarioActivo.setText(capaDatos.ArchivadorUsuario.verUsuario());
        if (jtxtUsuarioActivo.getText().length() == 0) {
            jtxtUsuarioActivo.setText(capaDatos.ArchivadorUsuario.verAdminCorreo());
        }

        Calendar fecha = new GregorianCalendar();
        String agno = Integer.toString(fecha.get(Calendar.YEAR));
        String mes = Integer.toString(fecha.get(Calendar.MONTH));
        String dia = Integer.toString(fecha.get(Calendar.DATE));

        String fechaCompleta = agno + "-" + mes + "-" + dia;

        String hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));    //00 - 23 hora militar
        String minutos = Integer.toString(fecha.get(Calendar.MINUTE));

        String horaCompleta = hora + ":" + minutos;

        jlabelFecha.setText(fechaCompleta);
        txtFechaFactura.setText(fechaCompleta);

        if (!jtxtUsuarioActivo.getText().equals("admin")) {

            btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantClientes.setVisible(false);
            btnManteClientes.setForeground(new java.awt.Color(154, 152, 154));

            btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantVehiculos.setVisible(false);
            btnManteVehiculo.setForeground(new java.awt.Color(154, 152, 154));

            btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantPartes.setVisible(false);
            btnMantenPartes.setForeground(new java.awt.Color(154, 152, 154));

            btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
            jpnReporteFacturas.setVisible(false);
            btnReportesFacturas.setForeground(new java.awt.Color(154, 152, 154));

        }


    }//GEN-LAST:event_formWindowOpened

    private void btnSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseClicked

        System.exit(0);
    }//GEN-LAST:event_btnSalirMouseClicked

    private void btnManteVehiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnManteVehiculoMouseClicked
        if (jtxtUsuarioActivo.getText().equals("admin")) {
            btnDashBoard.setBackground(new java.awt.Color(34, 45, 49));
            jpnDash.setVisible(false);

            btnPersoFactura.setBackground(new java.awt.Color(34, 45, 49));
            jpnPerso.setVisible(false);

            btnReportesVehiculos.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesVehiculos.setVisible(false);

            btnReportesClientes.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesCliente.setVisible(false);

            btnReportesPartes.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesPartes.setVisible(false);

            btnManteVehiculo.setBackground(new java.awt.Color(44, 59, 66));
            jpnMantVehiculos.setVisible(true);

            btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantClientes.setVisible(false);

            jpnFacturacion.setVisible(false);

            btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
            jpnReporteFacturas.setVisible(false);

            btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantPartes.setVisible(false);

            for (VehiculoEnum veEn : VehiculoEnum.values()) {
                jcbTipoVehiculo.addItem(veEn.toString());
            }

        } else {
            btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantVehiculos.setVisible(false);
        }

    }//GEN-LAST:event_btnManteVehiculoMouseClicked

    private void btnManteClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnManteClientesMouseClicked

        if (jtxtUsuarioActivo.getText().equals("admin")) {
            btnDashBoard.setBackground(new java.awt.Color(34, 45, 49));
            jpnDash.setVisible(false);

            btnPersoFactura.setBackground(new java.awt.Color(34, 45, 49));
            jpnPerso.setVisible(false);

            btnReportesVehiculos.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesVehiculos.setVisible(false);

            btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
            jpnReporteFacturas.setVisible(false);

            btnReportesPartes.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesPartes.setVisible(false);

            btnReportesClientes.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesCliente.setVisible(false);

            btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantVehiculos.setVisible(false);

            btnManteClientes.setBackground(new java.awt.Color(44, 59, 66));
            jpnMantClientes.setVisible(true);

            jpnFacturacion.setVisible(false);

            btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantPartes.setVisible(false);

        } else {
            btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantClientes.setVisible(false);
        }

    }//GEN-LAST:event_btnManteClientesMouseClicked

    private void btnMantenPartesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMantenPartesMouseClicked

        if (jtxtUsuarioActivo.getText().equals("admin")) {

            btnDashBoard.setBackground(new java.awt.Color(34, 45, 49));
            jpnDash.setVisible(false);

            btnPersoFactura.setBackground(new java.awt.Color(34, 45, 49));
            jpnPerso.setVisible(false);

            btnReportesPartes.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesPartes.setVisible(false);

            btnReportesClientes.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesCliente.setVisible(false);

            btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantVehiculos.setVisible(false);

            btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantClientes.setVisible(false);

            btnReportesVehiculos.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesVehiculos.setVisible(false);

            btnMantenPartes.setBackground(new java.awt.Color(44, 59, 66));
            jpnMantPartes.setVisible(true);

            btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
            jpnReporteFacturas.setVisible(false);

            jpnFacturacion.setVisible(false);

        } else {
            btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
            btnMantenPartes.setForeground(new java.awt.Color(154, 152, 154));
            jpnMantPartes.setVisible(false);

        }


    }//GEN-LAST:event_btnMantenPartesMouseClicked

    private void btnEliminarParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarParteActionPerformed

        int id = Integer.parseInt(txtId.getText());

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM partes WHERE id=?");
            ps.setInt(1, id);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro eliminado");
            limpiarSQL();
            cargarTablaSQL();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_btnEliminarParteActionPerformed

    private void btnModificarParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarParteActionPerformed

        int id = Integer.parseInt(txtId.getText());
        String numero = txtNoPiezas.getText();
        String tipo = txtTipo.getText();
        int modelo = Integer.parseInt(txtModelo.getText());
        float precio = Float.parseFloat(txtPrecio.getText());
        String estado = txtEstado.getText();

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("UPDATE partes SET numero=?,tipo=?,modelo=?,precio=?,estado=? WHERE id=?");
            ps.setString(1, numero);
            ps.setString(2, tipo);
            ps.setInt(3, modelo);
            ps.setFloat(4, precio);
            ps.setString(5, estado);
            ps.setInt(6, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro modificado");
            limpiarSQL();
            cargarTablaSQL();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_btnModificarParteActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

//        //desea guardar los cambios? al estilo word / excel / pp / etc
//        String botones[] = {"Si", "No"};    //si = 0 , no = 1
//
//        int n = JOptionPane.showOptionDialog(null, "Desea guardar la informacion?", "Titulo", 0, 0, null, botones, null);
//
//        if (n == 0) {
//            guardarPartes();
//        }
//
    }//GEN-LAST:event_formWindowClosing

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        String numero = txtNoPiezas.getText();
        String tipo = txtTipo.getText();
        int modelo = Integer.parseInt(txtModelo.getText());
        float precio = Float.parseFloat(txtPrecio.getText());
        String estado = txtEstado.getText();

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO partes (numero,tipo,modelo,precio,estado) VALUES (?,?,?,?,?)");
            ps.setString(1, numero);
            ps.setString(2, tipo);
            ps.setInt(3, modelo);
            ps.setFloat(4, precio);
            ps.setString(5, estado);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro guardado");
            limpiarSQL();
            cargarTablaSQL();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jpnMantVehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnMantVehiculosMouseClicked
    }//GEN-LAST:event_jpnMantVehiculosMouseClicked

    private void btnAgregarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarVehiculoActionPerformed

        String CodiVe, TipoVe;
        int ModeloVe;
        String Extras;
        double PrecioVe;

        try {
            CodiVe = txtCodigoAuto.getText();
            TipoVe = (String) jcbTipoVehiculo.getSelectedItem();
            ModeloVe = Integer.parseInt(txtModeloAuto.getText());
            PrecioVe = Double.parseDouble(txtPrecioAuto.getText());
            Extras = jtablePieza2.getValueAt(jtablePieza2.getSelectedRow(), 0).toString();
            LisVehi.add(new Vehiculo(CodiVe, ModeloVe, PrecioVe, TipoVe, Extras));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifica los datos");
        }
        limpiarVehi();
        verDatosVehi();
    }//GEN-LAST:event_btnAgregarVehiculoActionPerformed

    private void btnEliminarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarVehiculoActionPerformed
        int c;
        try {
            c = jtableVehiculo.getSelectedRow();
            LisVehi.remove(c);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Favor seleccione una fila");
        }
        verDatosVehi();

    }//GEN-LAST:event_btnEliminarVehiculoActionPerformed

    private void btnModificarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarVehiculoActionPerformed

        int c, modelo;
        String codigoVehiculo, tipo;

        double precio;
        Vehiculo aux;

        try {
            c = jtableVehiculo.getSelectedRow();
            aux = LisVehi.get(c);       //un vehiculo almacenado
            codigoVehiculo = JOptionPane.showInputDialog("Digite el nuevo codigo el vehiculo");
            modelo = Integer.parseInt(JOptionPane.showInputDialog("Digite el modelo del auto"));
            precio = Double.parseDouble(JOptionPane.showInputDialog("Digite el nuevo precio del auto"));
            tipo = JOptionPane.showInputDialog("Digite el nuevo tipo del auto");

            aux.setCodigoVehiculo(codigoVehiculo);
            aux.setModelo(modelo);
            aux.setPrecio(precio);
            aux.setTipo(tipo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila que desea modificar");
        }
        verDatosVehi();

    }//GEN-LAST:event_btnModificarVehiculoActionPerformed

    private void btnGuardarVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVehiculosActionPerformed
        String botones[] = {"Si", "No"};    //si = 0 , no = 1

        int n = JOptionPane.showOptionDialog(null, "Desea guardar la informacion?", "Titulo", 0, 0, null, botones, null);

        if (n == 0) {
            guardarAutos();
        }
    }//GEN-LAST:event_btnGuardarVehiculosActionPerformed

    private void btnReportesVehiculosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesVehiculosMouseClicked

        btnDashBoard.setBackground(new java.awt.Color(34, 45, 49));
        jpnDash.setVisible(false);

        btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
        jpnReporteFacturas.setVisible(false);

        btnPersoFactura.setBackground(new java.awt.Color(34, 45, 49));
        jpnPerso.setVisible(false);

        btnReportesPartes.setBackground(new java.awt.Color(34, 45, 49));
        jpnReportesPartes.setVisible(false);

        btnReportesClientes.setBackground(new java.awt.Color(34, 45, 49));
        jpnReportesCliente.setVisible(false);

        btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantVehiculos.setVisible(false);

        jpnFacturacion.setVisible(false);

        btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantClientes.setVisible(false);

        btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantPartes.setVisible(false);

        btnReportesVehiculos.setBackground(new java.awt.Color(44, 59, 66));
        jpnReportesVehiculos.setVisible(true);

        String MatVehi[][] = new String[LisVehi.size()][5];
        Vehiculo aux;
        for (int i = 0; i < LisVehi.size(); i++) {

            aux = LisVehi.get(i);
            MatVehi[i][0] = aux.getCodigoVehiculo();
            MatVehi[i][1] = Integer.toString(aux.getModelo());
            MatVehi[i][2] = Double.toString(aux.getPrecio());
            MatVehi[i][3] = aux.getTipo();
            MatVehi[i][4] = aux.getExtras();
        }
        jtableReporteVehiculos.setModel(new javax.swing.table.DefaultTableModel(
                MatVehi,
                new String[]{
                    "Codigo", "Modelo", "Precio", "Tipo", "Partes extras"
                }
        ));


    }//GEN-LAST:event_btnReportesVehiculosMouseClicked

    private void jrdPersonalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrdPersonalMouseClicked


    }//GEN-LAST:event_jrdPersonalMouseClicked

    private void jrdCorporativoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrdCorporativoMouseClicked


    }//GEN-LAST:event_jrdCorporativoMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int id = Integer.parseInt(txtidCliente.getText());

        try {

            Connection con = Conexion.getConexionClientes();
            PreparedStatement ps = con.prepareStatement("DELETE FROM clientes WHERE id=?");

            ps.setInt(1, id);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro eliminado");
            limpiarClienteSQL();
            cargarTablaSQLClientes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarClienteActionPerformed

        int id = Integer.parseInt(txtidCliente.getText());
        String nombre = txtNombreCliente.getText();
        String pais = txtPaisCliente.getText();
        String direccion = txtDireccionCliente.getText();
        int telefono = Integer.parseInt(txtTelefonoCliente.getText());
        String tipocliente;

        if (jrdPersonal.isSelected() == true) {
            tipocliente = "P";
        } else if (jrdCorporativo.isSelected() == true) {
            tipocliente = "F";
        } else {
            tipocliente = "-";
        }
        try {

            Connection con = Conexion.getConexionClientes();
            PreparedStatement ps = con.prepareStatement("UPDATE clientes set nombre=?,pais=?,direccion=?,telefono=?,tipo=? WHERE id=?");
            ps.setString(1, nombre);
            ps.setString(2, pais);
            ps.setString(3, direccion);
            ps.setInt(4, telefono);
            ps.setString(5, tipocliente);
            ps.setInt(6, id);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro modificado");
            limpiarClienteSQL();
            cargarTablaSQLClientes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_btnModificarClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String nombre = txtNombreCliente.getText();
        String pais = txtPaisCliente.getText();
        String direccion = txtDireccionCliente.getText();
        int telefono = Integer.parseInt(txtTelefonoCliente.getText());
        String tipocliente;

        if (jrdPersonal.isSelected() == true) {
            tipocliente = "P";
        } else if (jrdCorporativo.isSelected() == true) {
            tipocliente = "F";
        } else {
            tipocliente = "-";
        }
        try {

            Connection con = Conexion.getConexionClientes();
            PreparedStatement ps = con.prepareStatement("INSERT INTO clientes (nombre,pais,direccion,telefono,tipo) VALUES (?,?,?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, pais);
            ps.setString(3, direccion);
            ps.setInt(4, telefono);
            ps.setString(5, tipocliente);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro guardado");
            limpiarClienteSQL();
            cargarTablaSQLClientes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnReportesClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesClientesMouseClicked

        btnDashBoard.setBackground(new java.awt.Color(34, 45, 49));
        jpnDash.setVisible(false);

        btnPersoFactura.setBackground(new java.awt.Color(34, 45, 49));
        jpnPerso.setVisible(false);

        btnReportesPartes.setBackground(new java.awt.Color(34, 45, 49));
        jpnReportesPartes.setVisible(false);

        btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantVehiculos.setVisible(false);

        jpnFacturacion.setVisible(false);

        btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantClientes.setVisible(false);

        btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
        jpnReporteFacturas.setVisible(false);

        btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantPartes.setVisible(false);

        btnReportesVehiculos.setBackground(new java.awt.Color(34, 45, 49));
        jpnReportesVehiculos.setVisible(false);

        btnReportesClientes.setBackground(new java.awt.Color(44, 59, 66));
        jpnReportesCliente.setVisible(true);

        DefaultTableModel modeloTabla = (DefaultTableModel) jtableReportesClientesRegistrados.getModel();
        modeloTabla.setRowCount(0);//reinicie todo las filas

        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;

        int columnas;

        try {

            Connection con = Conexion.getConexionClientes();
            ps = con.prepareStatement("SELECT id,nombre,pais,direccion,telefono,tipo FROM clientes");
            rs = ps.executeQuery();

            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {
                    fila[indice] = rs.getObject(indice + 1);
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_btnReportesClientesMouseClicked

    private void jrdPersonalCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrdPersonalCompraMouseClicked
        jpanelPersonalCompra.setVisible(true);
        jpanelCorporativoCompra.setVisible(false);
    }//GEN-LAST:event_jrdPersonalCompraMouseClicked

    private void jrdCorporativoCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrdCorporativoCompraMouseClicked
        jpanelPersonalCompra.setVisible(false);
        jpanelCorporativoCompra.setVisible(true);
    }//GEN-LAST:event_jrdCorporativoCompraMouseClicked
    TableRowSorter trs;
    private void txtConsultaTipoDeseadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsultaTipoDeseadoKeyTyped

        txtConsultaTipoDeseado.addKeyListener(new KeyAdapter() {        //Anonima

            @Override //metodo reconocedor de keyListener

            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter(txtConsultaTipoDeseado.getText(), 3));

            }

        });

        trs = new TableRowSorter(jtableVehiculosCompra.getModel());
        jtableVehiculosCompra.setRowSorter(trs);


    }//GEN-LAST:event_txtConsultaTipoDeseadoKeyTyped

    private void txtConsultaCodigoVehiculoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsultaCodigoVehiculoKeyTyped

        txtConsultaCodigoVehiculo.addKeyListener(new KeyAdapter() {

            @Override //metodo reconocedor de keyListener

            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter(txtConsultaCodigoVehiculo.getText(), 0));

            }

        });

        trs = new TableRowSorter(jtableVehiculo.getModel());
        jtableVehiculo.setRowSorter(trs);

    }//GEN-LAST:event_txtConsultaCodigoVehiculoKeyTyped

    private void txtConsultaClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConsultaClienteKeyTyped

        txtConsultaCliente.addKeyListener(new KeyAdapter() {

            @Override //metodo reconocedor de keyListener

            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter(txtConsultaCliente.getText(), 0));

            }

        });

        trs = new TableRowSorter(jtableClientesRegistrados.getModel());
        jtableClientesRegistrados.setRowSorter(trs);


    }//GEN-LAST:event_txtConsultaClienteKeyTyped

    private void txtBuscarNoParteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNoParteKeyTyped

        txtBuscarNoParte.addKeyListener(new KeyAdapter() {

            @Override //metodo reconocedor de keyListener

            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter(txtBuscarNoParte.getText(), 0));

            }

        });

        trs = new TableRowSorter(jtablePieza.getModel());
        jtablePieza.setRowSorter(trs);


    }//GEN-LAST:event_txtBuscarNoParteKeyTyped

    private void txtSubTotalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtSubTotalPropertyChange

    }//GEN-LAST:event_txtSubTotalPropertyChange

    private void btnCotizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCotizarActionPerformed

        if (jrdCorporativoCompra.isSelected() == false && jrdPersonalCompra.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Seleccione el tipo de cliente al que perteneces");
        } else {

            double dato1 = 0;
            double dato2 = 0;
            final double IVA = 0.13;
            DecimalFormat df = new DecimalFormat("#.00");

            try {
                dato1 = Double.parseDouble(jtableVehiculosCompra.getValueAt(jtableVehiculosCompra.getSelectedRow(), 2).toString());
                dato2 = Double.parseDouble(jtablePartesAdicionalesCompra.getValueAt(jtablePartesAdicionalesCompra.getSelectedRow(), 5).toString());
                btnComprarVehiculo.setEnabled(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Verificar los datos");

            }

            //comprobaciones de usuario y descuentos + iva
            if (jtableVehiculosCompra.getValueAt(jtableVehiculosCompra.getSelectedRow(), 3).toString().equalsIgnoreCase("Hibrido") && jrdCorporativoCompra.isSelected()) {
                txtSubTotal.setText(String.valueOf(df.format(((dato1 + dato2) - 0.25) - 0.3)));
                txtTotalIva.setText(String.valueOf(df.format(((dato1 + dato2) - 0.25) - 0.3 + IVA)));
            } else if (jtableVehiculosCompra.getValueAt(jtableVehiculosCompra.getSelectedRow(), 3).toString().equalsIgnoreCase("Hibrido") && jrdPersonalCompra.isSelected()) {
                txtSubTotal.setText(String.valueOf(df.format(((dato1 + dato2) - 0.15) - 0.3)));
                txtTotalIva.setText(String.valueOf(df.format(((dato1 + dato2) - 0.15) - 0.3 + IVA)));
            } else if (jtableVehiculosCompra.getValueAt(jtableVehiculosCompra.getSelectedRow(), 3).toString().equalsIgnoreCase("Electrico") && jrdCorporativoCompra.isSelected()) {
                txtSubTotal.setText(String.valueOf(df.format(((dato1 + dato2) - 0.25) - 0.5)));
                txtTotalIva.setText(String.valueOf(df.format(((dato1 + dato2) - 0.25) - 0.5 + IVA)));
            } else if (jtableVehiculosCompra.getValueAt(jtableVehiculosCompra.getSelectedRow(), 3).toString().equalsIgnoreCase("Electrico") && jrdPersonalCompra.isSelected()) {
                txtSubTotal.setText(String.valueOf(df.format(((dato1 + dato2) - 0.15) - 0.5)));
                txtTotalIva.setText(String.valueOf(df.format(((dato1 + dato2) - 0.15) - 0.5 + IVA)));
            } else if (jtableVehiculosCompra.getValueAt(jtableVehiculosCompra.getSelectedRow(), 3).toString().equalsIgnoreCase("Fosil") && jrdCorporativoCompra.isSelected()) {
                txtSubTotal.setText(String.valueOf(df.format(((dato1 + dato2) - 0.25) - 0)));
                txtTotalIva.setText(String.valueOf(df.format(((dato1 + dato2) - 0.25) - 0 + IVA)));
            } else if (jtableVehiculosCompra.getValueAt(jtableVehiculosCompra.getSelectedRow(), 3).toString().equalsIgnoreCase("Fosil") && jrdPersonalCompra.isSelected()) {
                txtSubTotal.setText(String.valueOf(df.format(((dato1 + dato2) - 0.15) - 0)));
                txtTotalIva.setText(String.valueOf(df.format(((dato1 + dato2) - 0.15) - 0 + IVA)));
            }

        }
    }//GEN-LAST:event_btnCotizarActionPerformed

    private void btnPersoFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPersoFacturaMouseClicked

        btnDashBoard.setBackground(new java.awt.Color(34, 45, 49));
        jpnDash.setVisible(false);

        btnPersoFactura.setBackground(new java.awt.Color(34, 45, 49));
        jpnPerso.setVisible(false);

        btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
        jpnReporteFacturas.setVisible(false);

        btnReportesPartes.setBackground(new java.awt.Color(34, 45, 49));
        jpnReportesPartes.setVisible(false);

        btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantVehiculos.setVisible(false);

        btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantClientes.setVisible(false);

        btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
        jpnMantPartes.setVisible(false);

        btnReportesClientes.setBackground(new java.awt.Color(34, 45, 49));
        jpnReportesCliente.setVisible(false);

        btnComprarVehiculo.setEnabled(false);
        jpnFacturacion.setVisible(false);

        btnPersoFactura.setBackground(new java.awt.Color(44, 59, 66));
        jpnPerso.setVisible(true);

        txtUsuarioComprar.setText(jtxtUsuarioActivo.getText());

        String MatVehi[][] = new String[LisVehi.size()][5];
        Vehiculo aux;
        for (int i = 0; i < LisVehi.size(); i++) {

            aux = LisVehi.get(i);
            MatVehi[i][0] = aux.getCodigoVehiculo();
            MatVehi[i][1] = Integer.toString(aux.getModelo());
            MatVehi[i][2] = Double.toString(aux.getPrecio());
            MatVehi[i][3] = aux.getTipo();
            MatVehi[i][4] = aux.getExtras();
        }
        jtableVehiculosCompra.setModel(new javax.swing.table.DefaultTableModel(
                MatVehi,
                new String[]{
                    "Codigo", "Modelo", "Precio", "Tipo", "Partes extras"
                }
        ));

        jpanelPersonalCompra.setVisible(false);
        jpanelCorporativoCompra.setVisible(false);
        jpnFacturacion.setVisible(false);

        DefaultTableModel modeloTabla = (DefaultTableModel) jtablePartesAdicionalesCompra.getModel();
        modeloTabla.setRowCount(0);

        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        try {

            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT id,numero,tipo,modelo,estado,precio FROM partes");

            rs = ps.executeQuery();//genera la consulta
            rsmd = rs.getMetaData();//traiga los metadatos de la consulta
            columnas = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {
                    fila[indice] = rs.getObject(indice + 1); //esta en 0
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_btnPersoFacturaMouseClicked

    private void btnComprarVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarVehiculoActionPerformed

        String tipoCliente = "Sin seleccionar";
        if (jrdPersonalCompra.isSelected() == true) {
            tipoCliente = "Cliente personal";
        } else if (jrdCorporativoCompra.isSelected() == true) {
            tipoCliente = "Cliente corporativo";
        }

        JOptionPane.showMessageDialog(null, "Gracias por la compra, a continuacion se mostrara la facturacion mas detallada");
        jpnPerso.setVisible(false);
        jpnFacturacion.setVisible(true);

//        for (TipoPagoEnum tiPago : TipoPagoEnum.values()) {
//            jcbTipoPago.addItem(tiPago.toString());
//        }
        txtNumeroFactura.setText(String.valueOf(numeroFactura++));
        txtNombreClienteFacturacion.setText(ArchivadorUsuario.verNombreCompleto());
        txtDireccionFacturacion.setText(ArchivadorUsuario.verDireccion());
        txtPaisFacturacion.setText(ArchivadorUsuario.verPais());
        txtTelefonoFacturacion.setText(String.valueOf(ArchivadorUsuario.verTelefono()));
        txtSubTotalFacturacion.setText(txtSubTotal.getText());//menos trabajo a la maquina
        txtTotalPagar.setText(txtTotalIva.getText());
        txtIVA.setText("13 %");
        txtTipoCliente.setText(tipoCliente);

        
       

//no
//pais
//di
//tef
//tipo
//SQL CONSULTA // INSERT 
        String nombre = txtNombreClienteFacturacion.getText();
        String pais = txtPaisFacturacion.getText();
        String direccion = txtDireccionFacturacion.getText();
        int telefono = Integer.parseInt(txtTelefonoFacturacion.getText());
        String tipocliente;

        if (jrdPersonalCompra.isSelected() == true) {
            tipocliente = "P";
        } else if (jrdCorporativoCompra.isSelected() == true) {
            tipocliente = "F";
        } else {
            tipocliente = "-";
        }
        try {

            Connection con = Conexion.getConexionClientes();
            PreparedStatement ps = con.prepareStatement("INSERT INTO clientes (nombre,pais,direccion,telefono,tipo) VALUES (?,?,?,?,?)");
            ps.setString(1, nombre);
            ps.setString(2, pais);
            ps.setString(3, direccion);
            ps.setInt(4, telefono);
            ps.setString(5, tipocliente);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Perfil creado con exito");
            limpiarClienteSQL();
            cargarTablaSQLClientes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_btnComprarVehiculoActionPerformed

    private void btnComprarArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarArchivosActionPerformed

        String NO, Cli;
        String Tot;
        int TE;

        try {
            NO = txtNumeroFactura.getText();
            Cli = txtNombreClienteFacturacion.getText();
            TE = Integer.parseInt(txtTelefonoFacturacion.getText());
            Tot = txtTotalPagar.getText();
            ListFac.add(new Factura(NO, Cli, TE, Tot));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifica los datos");
        }

        verDatosFactura();
        guardarFactura();

        jpnFacturacion.setVisible(false);
        jpnDash.setVisible(true);

    }//GEN-LAST:event_btnComprarArchivosActionPerformed

    private void jcbTipoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoPagoActionPerformed
        if (jcbTipoPago.getSelectedItem() == "CONTADO") {
            txtDescuento.setText("5 %");

        } else {
            txtDescuento.setText("0");
        }
    }//GEN-LAST:event_jcbTipoPagoActionPerformed

    private void btnReportesFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesFacturasMouseClicked
        if (jtxtUsuarioActivo.getText().equals("admin")) {

            btnDashBoard.setBackground(new java.awt.Color(34, 45, 49));
            jpnDash.setVisible(false);

            btnPersoFactura.setBackground(new java.awt.Color(34, 45, 49));
            jpnPerso.setVisible(false);

            btnReportesPartes.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesPartes.setVisible(false);

            btnManteVehiculo.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantVehiculos.setVisible(false);

            btnManteClientes.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantClientes.setVisible(false);

            btnMantenPartes.setBackground(new java.awt.Color(34, 45, 49));
            jpnMantPartes.setVisible(false);

            btnReportesVehiculos.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesVehiculos.setVisible(false);

            btnReportesClientes.setBackground(new java.awt.Color(34, 45, 49));
            jpnReportesCliente.setVisible(false);

            jpnFacturacion.setVisible(false);

            btnReportesFacturas.setBackground(new java.awt.Color(44, 59, 66));
            jpnReporteFacturas.setVisible(true);

        } else {
            btnReportesFacturas.setBackground(new java.awt.Color(34, 45, 49));
            btnReportesFacturas.setForeground(new java.awt.Color(154, 152, 154));
            jpnReporteFacturas.setVisible(false);
        }
    }//GEN-LAST:event_btnReportesFacturasMouseClicked

    private void jtablePiezaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtablePiezaMouseClicked

        //2 formas
        //directamente desde la tabla
        //o hacer la consulta en sql sobre la tabla seleccionada
        try {

            int fila = jtablePieza.getSelectedRow();    //fila?
            int id = Integer.parseInt(jtablePieza.getValueAt(fila, 0).toString());

            PreparedStatement ps;
            ResultSet rs;

            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT id,numero,tipo,modelo,estado,precio FROM partes WHERE id=?");

            ps.setInt(1, id);
            rs = ps.executeQuery();//genera la consulta

            while (rs.next()) {
                txtId.setText(String.valueOf(id));
                txtNoPiezas.setText(rs.getString("numero"));
                txtTipo.setText(rs.getString("tipo"));
                txtModelo.setText(rs.getString("modelo"));
                txtPrecio.setText(rs.getString("precio"));
                txtEstado.setText(rs.getString("estado"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_jtablePiezaMouseClicked

    private void jtableClientesRegistradosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtableClientesRegistradosMouseClicked

        try {
            int fila = jtableClientesRegistrados.getSelectedRow();
            int id = Integer.parseInt(jtableClientesRegistrados.getValueAt(fila, 0).toString());

            PreparedStatement ps;
            ResultSet rs;

            Connection con = Conexion.getConexionClientes();
            ps = con.prepareStatement("SELECT nombre,pais,direccion,telefono,tipo FROM clientes WHERE id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {

                txtidCliente.setText(String.valueOf(id));
                txtNombreCliente.setText(rs.getString("nombre"));
                txtPaisCliente.setText(rs.getString("pais"));
                txtDireccionCliente.setText(rs.getString("direccion"));
                txtTelefonoCliente.setText(rs.getString("telefono"));

                if (rs.getString("tipo").equals("P")) {
                    jrdPersonal.setSelected(true);
                } else if (rs.getString("tipo").equals("C")) {
                    jrdCorporativo.setSelected(true);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }


    }//GEN-LAST:event_jtableClientesRegistradosMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        limpiarSQL();
    }//GEN-LAST:event_jButton2ActionPerformed
    private void verDatosFactura() {

        String Mat[][] = new String[ListFac.size()][4];
        Factura aux;
        for (int i = 0; i < ListFac.size(); i++) {
            aux = ListFac.get(i);
            Mat[i][0] = aux.getNoFactura();
            Mat[i][1] = aux.getCliente();
            Mat[i][2] = Integer.toString(aux.getTelefonoContacto());
            Mat[i][3] = aux.getTotalPagar();
        }
        jtableFacturasRegistradas.setModel(new javax.swing.table.DefaultTableModel(
                Mat,
                new String[]{
                    "Numero factura", "Cliente", "Telefono", "Total facturado"
                }
        ));

    }

    private void limpiarCliente() {
        txtNombreCliente.setText("");
        txtPaisCliente.setText("");
        txtDireccionCliente.setText("");
        txtTelefonoCliente.setText("");
        txtTipo.setText("");

    }

    private void limpiarVehi() {
        txtCodigoAuto.setText("");
        //txtTipoAuto.setText("");
        txtModeloAuto.setText("");
        txtPrecioAuto.setText("");
    }

    private void verDatosVehi() {

        String MatVehi[][] = new String[LisVehi.size()][5];
        Vehiculo aux;
        for (int i = 0; i < LisVehi.size(); i++) {

            aux = LisVehi.get(i);
            MatVehi[i][0] = aux.getCodigoVehiculo();
            MatVehi[i][1] = Integer.toString(aux.getModelo());
            MatVehi[i][2] = Double.toString(aux.getPrecio());
            MatVehi[i][3] = aux.getTipo();
            MatVehi[i][4] = aux.getExtras();

        }
        jtableVehiculo.setModel(new javax.swing.table.DefaultTableModel(
                MatVehi,
                new String[]{
                    "Codigo", "Modelo", "Precio", "Tipo", "Partes extras"
                }
        ));

    }

    private void guardarAutos() {

        File file = new File("Vehiculos.txt");
        PrintWriter Escribe;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
            }
        }

        try {
            Vehiculo aux;
            Escribe = new PrintWriter(file, "utf-8");
            for (int i = 0; i < LisVehi.size(); i++) {
                aux = LisVehi.get(i);
                aux.guardarAuto(Escribe);
            }
            Escribe.close();
        } catch (Exception e) {
        }

    }

    public void cargarVehiculos() {
        File Archivo = new File("Vehiculos.txt");
        FileReader Leer;
        BufferedReader Almacen;
        Vehiculo veh, aux = new Vehiculo();
        try {
            Leer = new FileReader(Archivo);
            Almacen = new BufferedReader(Leer);
            veh = aux.cargarVehiculos(Almacen);

            while (veh != null) {
                LisVehi.add(veh);
                veh = aux.cargarVehiculos(Almacen); //llamar a otro vehiculo
            }
            Almacen.close();
            Leer.close();

        } catch (Exception e) {
        }
        verDatosVehi();
    }

    private void limpiar() {
        txtEstado.setText("");
        txtModelo.setText("");
        txtNoPiezas.setText("");
        txtPrecio.setText("");
        txtTipo.setText("");
    }

    private void mostrarVehiculo(Vehiculo aux) {

        String MatVehi[][] = new String[1][4];

        MatVehi[0][0] = aux.getCodigoVehiculo();
        MatVehi[0][1] = Integer.toString(aux.getModelo());
        MatVehi[0][2] = Double.toString(aux.getPrecio());
        MatVehi[0][3] = aux.getTipo();

        jtableVehiculo.setModel(new javax.swing.table.DefaultTableModel(
                MatVehi,
                new String[]{
                    "Codigo", "Modelo", "Precio", "Tipo", "Partes extras"
                }
        ));

    }

    private void guardarFactura() {

        File file = new File("Facturas.txt");
        PrintWriter Escribe;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
            }
        }
        try {
            Factura aux;
            Escribe = new PrintWriter(file, "utf-8");

            for (int i = 0; i < ListFac.size(); i++) {
                aux = ListFac.get(i);
                aux.guardar(Escribe);
            }
            Escribe.close();
        } catch (Exception e) {
        }

    }

    public void cargarFacturas() {

        File Archivo = new File("Facturas.txt");
        FileReader Leer;
        BufferedReader Almacen;
        Factura fac, aux = new Factura();
        try {
            Leer = new FileReader(Archivo);
            Almacen = new BufferedReader(Leer);
            fac = aux.cargar(Almacen);
            while (fac != null) {
                ListFac.add(fac);
                fac = aux.cargar(Almacen);
            }
            Almacen.close();
            Leer.close();
        } catch (Exception e) {
        }
        verDatosFactura();

    }

    private void cargarTablaSQLClientes() {

        DefaultTableModel modeloTabla = (DefaultTableModel) jtableClientesRegistrados.getModel();
        modeloTabla.setRowCount(0);//reinicie todo las filas

        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;

        int columnas;

        try {

            Connection con = Conexion.getConexionClientes();
            ps = con.prepareStatement("SELECT id,nombre,pais,direccion,telefono,tipo FROM clientes");
            rs = ps.executeQuery();

            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {
                    fila[indice] = rs.getObject(indice + 1);
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void cargarTablaSQL() {
        DefaultTableModel modeloTabla = (DefaultTableModel) jtablePieza.getModel();
        modeloTabla.setRowCount(0);

        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        try {

            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT id,numero,tipo,modelo,estado,precio FROM partes");

            rs = ps.executeQuery();//genera la consulta
            rsmd = rs.getMetaData();//traiga los metadatos de la consulta
            columnas = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {
                    fila[indice] = rs.getObject(indice + 1); //esta en 0
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void cargarTablaSQLMini() {
        DefaultTableModel modeloTabla = (DefaultTableModel) jtablePieza2.getModel();
        modeloTabla.setRowCount(0);

        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;

        try {

            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT tipo FROM partes");

            rs = ps.executeQuery();//genera la consulta
            rsmd = rs.getMetaData();//traiga los metadatos de la consulta
            columnas = rsmd.getColumnCount();

            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {
                    fila[indice] = rs.getObject(indice + 1); //esta en 0
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    private void limpiarSQL() {
        txtId.setText("");
        txtNoPiezas.setText("");
        txtTipo.setText("");
        txtModelo.setText("");
        txtPrecio.setText("");
        txtEstado.setText("");
    }

    private void limpiarClienteSQL() {
        txtNombreCliente.setText("");
        txtTelefonoCliente.setText("");
        txtPaisCliente.setText("");
        txtDireccionCliente.setText("");
        buttonGroup1.clearSelection();
    }

    public void hora() {    //hora del sistema 
        Calendar calendario = new GregorianCalendar();
        Date horaActual = new Date();
        calendario.setTime(horaActual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);

    }

    @Override
//IMPLEMENTS 
    public void run() {
        Thread current = Thread.currentThread();

        while (current == hilo) {
            hora();
            jlabelHora.setText(hora + ":" + minutos + ":" + segundos);
        }
    }

    /**
     * @param args the command line arguments
     */
    //sql
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        //cambiar el estilo 'look' del table
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmCuerpo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCuerpo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCuerpo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCuerpo.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCuerpo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarVehiculo;
    private javax.swing.JButton btnComprarArchivos;
    private javax.swing.JButton btnComprarVehiculo;
    private javax.swing.JButton btnCotizar;
    public javax.swing.JLabel btnDashBoard;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminarParte;
    private javax.swing.JButton btnEliminarVehiculo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarVehiculos;
    private javax.swing.JPanel btnHERE;
    private javax.swing.JLabel btnManteClientes;
    private javax.swing.JLabel btnManteVehiculo;
    private javax.swing.JLabel btnMantenPartes;
    private javax.swing.JButton btnModificarCliente;
    private javax.swing.JButton btnModificarParte;
    private javax.swing.JButton btnModificarVehiculo;
    public javax.swing.JLabel btnPersoFactura;
    private javax.swing.JLabel btnReportesClientes;
    private javax.swing.JLabel btnReportesFacturas;
    private javax.swing.JLabel btnReportesPartes;
    private javax.swing.JLabel btnReportesVehiculos;
    private javax.swing.JLabel btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JComboBox<String> jcbTipoPago;
    private javax.swing.JComboBox<String> jcbTipoVehiculo;
    private javax.swing.JLabel jlabelFecha;
    private javax.swing.JLabel jlabelHora;
    private javax.swing.JPanel jpanelCorporativoCompra;
    private javax.swing.JPanel jpanelPersonalCompra;
    private javax.swing.JPanel jpanelesHERE;
    private javax.swing.JPanel jpaneltipoCliente;
    private javax.swing.JPanel jpaneltipoClienteCompra;
    private javax.swing.JPanel jpnDash;
    private javax.swing.JPanel jpnFacturacion;
    private javax.swing.JPanel jpnHeader;
    private javax.swing.JPanel jpnHeader1;
    private javax.swing.JPanel jpnHeader2;
    private javax.swing.JPanel jpnHeader3;
    private javax.swing.JPanel jpnHeader4;
    private javax.swing.JPanel jpnHeader5;
    private javax.swing.JPanel jpnHeader6;
    private javax.swing.JPanel jpnHeader7;
    private javax.swing.JPanel jpnMantClientes;
    private javax.swing.JPanel jpnMantPartes;
    private javax.swing.JPanel jpnMantVehiculos;
    private javax.swing.JPanel jpnPerso;
    private javax.swing.JPanel jpnReporteFacturas;
    private javax.swing.JPanel jpnReportesCliente;
    private javax.swing.JPanel jpnReportesPartes;
    private javax.swing.JPanel jpnReportesVehiculos;
    private javax.swing.JRadioButton jrdCorporativo;
    private javax.swing.JRadioButton jrdCorporativoCompra;
    private javax.swing.JRadioButton jrdPersonal;
    private javax.swing.JRadioButton jrdPersonalCompra;
    private javax.swing.JTable jtableClientesRegistrados;
    private javax.swing.JTable jtableFacturasRegistradas;
    private javax.swing.JTable jtablePartesAdicionalesCompra;
    private javax.swing.JTable jtablePieza;
    private javax.swing.JTable jtablePieza1;
    private javax.swing.JTable jtablePieza2;
    private javax.swing.JTable jtableReporteVehiculos;
    private javax.swing.JTable jtableReportesClientesRegistrados;
    private javax.swing.JTable jtableVehiculo;
    private javax.swing.JTable jtableVehiculosCompra;
    private javax.swing.JTextField jtxtOnline;
    public javax.swing.JTextField jtxtUsuarioActivo;
    private javax.swing.JPanel principalHERE;
    private javax.swing.JTextField txtBuscarNoParte;
    private javax.swing.JTextField txtCedJuridicaCompra;
    private javax.swing.JTextField txtCedulaFisicaCompra;
    private javax.swing.JTextField txtCodigoAuto;
    private javax.swing.JTextField txtConsultaCliente;
    private javax.swing.JTextField txtConsultaCodigoVehiculo;
    private javax.swing.JTextField txtConsultaTipoDeseado;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtDireccionFacturacion;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFechaFactura;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtModeloAuto;
    private javax.swing.JTextField txtNoPiezas;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreClienteFacturacion;
    private javax.swing.JTextField txtNumeroFactura;
    private javax.swing.JTextField txtPaisCliente;
    private javax.swing.JTextField txtPaisFacturacion;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPrecioAuto;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtSubTotalFacturacion;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoFacturacion;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTipoCliente;
    private javax.swing.JTextField txtTotalIva;
    private javax.swing.JTextField txtTotalPagar;
    private javax.swing.JTextField txtUsuarioComprar;
    private javax.swing.JTextField txtidCliente;
    // End of variables declaration//GEN-END:variables

}

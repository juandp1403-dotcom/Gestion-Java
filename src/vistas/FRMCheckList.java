/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

package vistas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static modelo.ConexionBD.conexion;
import modelo.HistorialRevision;
import modelo.InventarioAmbiente;
import modelo.Movimiento;

public class FRMCheckList extends javax.swing.JInternalFrame {

    private int idAmbiente;
    private int idUsuario = 1;
    private javax.swing.JDesktopPane escritorio;

    public FRMCheckList() {
        initComponents();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        escritorio = getDesktopPane();
    }

    public void cargarCheckList(int idAmbiente) {
        this.idAmbiente = idAmbiente;

        try {
            PreparedStatement ps = conexion.prepareStatement(
                "SELECT nombre FROM ambiente WHERE id = ?"
            );
            ps.setInt(1, idAmbiente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lb_tituloAmbiente.setText(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        cargarItems();
    }

    private void cargarItems() {
        DefaultTableModel modelo = (DefaultTableModel) tb_checklist.getModel();
        modelo.setRowCount(0);

        InventarioAmbiente ia = new InventarioAmbiente();
        var lista = ia.listarPorAmbiente(idAmbiente);

        while (lista.hasNext()) {
            InventarioAmbiente item = lista.next();
            if ("Sin registros".equals(item.getNombreArticulo())) continue;
            String estado = item.tieneStockBajo() ? "Stock bajo" : "Normal";
            modelo.addRow(new Object[]{
                Boolean.FALSE,
                item.getNombreArticulo(),
                item.getCantidad() + " / " + item.getCantidadMinima(),
                estado,
                "",
                item.getIdInventarioAmbiente(),
                item.getIdArticulo()
            });
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lb_tituloAmbiente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_checklist = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        bt_guardar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Checklist de Inventario");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Revisa cada art\u00edculo. Marca como revisado si est\u00e1 presente y en buen estado. Opcionalmente agrega observaciones por cada uno.");

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel3.setText("Ambiente:");

        lb_tituloAmbiente.setFont(new java.awt.Font("Segoe UI", 1, 14));

        tb_checklist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Revisado", "Art\u00edculo", "Actual / Min", "Estado", "Observaciones", "idInventario", "idArticulo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_checklist.getColumnModel().getColumn(0).setPreferredWidth(60);
        tb_checklist.getColumnModel().getColumn(1).setPreferredWidth(200);
        tb_checklist.getColumnModel().getColumn(2).setPreferredWidth(80);
        tb_checklist.getColumnModel().getColumn(3).setPreferredWidth(70);
        tb_checklist.getColumnModel().getColumn(4).setPreferredWidth(250);
        tb_checklist.removeColumn(tb_checklist.getColumnModel().getColumn(5));
        tb_checklist.removeColumn(tb_checklist.getColumnModel().getColumn(5));
        jScrollPane1.setViewportView(tb_checklist);

        bt_guardar.setText("Guardar Checklist");
        bt_guardar.addActionListener(this::bt_guardarActionPerformed);

        bt_cancelar.setText("Cancelar");
        bt_cancelar.addActionListener(this::bt_cancelarActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bt_guardar)
                .addGap(18, 18, 18)
                .addComponent(bt_cancelar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_guardar)
                    .addComponent(bt_cancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lb_tituloAmbiente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lb_tituloAmbiente))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
    }

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel modelo = (DefaultTableModel) tb_checklist.getModel();
        int filas = modelo.getRowCount();

        if (filas == 0) {
            JOptionPane.showMessageDialog(this, "No hay art\u00edculos para guardar.");
            return;
        }

        StringBuilder observacionesGlobales = new StringBuilder();
        int revisados = 0;

        Timestamp ahora = new Timestamp(System.currentTimeMillis());

        for (int i = 0; i < filas; i++) {
            boolean revisado = (boolean) modelo.getValueAt(i, 0);
            String obs = (String) modelo.getValueAt(i, 4);
            if (obs == null) obs = "";

            int idInvAmbiente = (int) modelo.getValueAt(i, 5);
            int idArt = (int) modelo.getValueAt(i, 6);

            Movimiento mov = new Movimiento();
            mov.setIdArticulo(idArt);
            mov.setIdUsuario(idUsuario);
            mov.setTipo("checklist");
            mov.setCantidad(revisado ? 1 : 0);
            mov.setObservacion(obs);
            mov.setFechaMovimiento(ahora);
            mov.insertar();

            if (revisado) revisados++;
            if (!obs.isEmpty()) {
                if (observacionesGlobales.length() > 0) observacionesGlobales.append("; ");
                observacionesGlobales.append(obs);
            }
        }

        String descripcion = "Checklist general realizado para ambiente #" + idAmbiente;
        if (observacionesGlobales.length() > 0) {
            descripcion += " - Observaci\u00f3n: " + observacionesGlobales.toString();
        }

        HistorialRevision hr = new HistorialRevision();
        hr.setIdAmbiente(idAmbiente);
        hr.setFechaRevision(ahora);
        hr.setTipoAccion("checklist");
        hr.setDescripcion(descripcion);
        hr.setIdReferencia(idAmbiente);
        hr.setIdUsuario(idUsuario);
        hr.insertar();

        JOptionPane.showMessageDialog(this,
            "Checklist guardado correctamente.\n" + revisados + " de " + filas + " art\u00edculo(s) revisado(s).");

        javax.swing.JDesktopPane dp = getDesktopPane();
        if (dp != null) {
            for (javax.swing.JInternalFrame f : dp.getAllFrames()) {
                if (f instanceof FRMDetalleInventario) {
                    f.setVisible(true);
                    break;
                }
            }
        }
        this.dispose();
    }

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {
        int opcion = JOptionPane.showConfirmDialog(
            this,
            "\u00bfEst\u00e1 seguro de cancelar? Los cambios no se guardar\u00e1n.",
            "Cancelar Checklist",
            JOptionPane.YES_NO_OPTION
        );
        if (opcion == JOptionPane.YES_OPTION) {
            javax.swing.JDesktopPane dp = getDesktopPane();
            if (dp != null) {
                for (javax.swing.JInternalFrame f : dp.getAllFrames()) {
                    if (f instanceof FRMDetalleInventario) {
                        f.setVisible(true);
                        break;
                    }
                }
            }
            this.dispose();
        }
    }

    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_tituloAmbiente;
    private javax.swing.JTable tb_checklist;
}

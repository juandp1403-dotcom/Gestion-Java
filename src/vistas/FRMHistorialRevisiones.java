package vistas;

import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import static modelo.ConexionBD.conexion;
import modelo.HistorialRevision;

public class FRMHistorialRevisiones extends javax.swing.JInternalFrame {

    private int idAmbiente;

    public FRMHistorialRevisiones() {
        initComponents();
        
        jPanel1.setBackground(new Color(40, 167, 69));
        for (Component c : jPanel1.getComponents()) {
            if (c instanceof JLabel) {
                ((JLabel) c).setForeground(Color.WHITE);
            }
            if (c instanceof JButton) {
                ((JButton) c).setBackground(new Color(33, 136, 56));
                ((JButton) c).setForeground(Color.WHITE);
                ((JButton) c).setFocusPainted(false);
                ((JButton) c).setBorderPainted(false);
            }
        }
        jLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 11));
        jLabel3.setFont(new Font("Arial", Font.BOLD, 14));
        jLabel3.setForeground(new Color(40, 167, 69));
        lb_NumAmbiente.setFont(new Font("Arial", Font.BOLD, 14));
        lb_NumAmbiente.setForeground(new Color(51, 51, 51));
        jLabel4.setFont(new Font("Arial", Font.PLAIN, 12));
        jLabel4.setForeground(new Color(51, 51, 51));
        
        tb_historial.setSelectionBackground(new Color(40, 167, 69));
        tb_historial.setSelectionForeground(Color.WHITE);
        tb_historial.setGridColor(new Color(220, 220, 220));
        tb_historial.setRowHeight(28);
        tb_historial.getTableHeader().setBackground(new Color(40, 167, 69));
        tb_historial.getTableHeader().setForeground(Color.WHITE);
        tb_historial.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tb_historial.getTableHeader().setReorderingAllowed(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tb_historial.getColumnCount(); i++) {
            tb_historial.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        bt_volver.setBackground(new Color(40, 167, 69));
        bt_volver.setForeground(Color.WHITE);
        bt_volver.setFocusPainted(false);
        bt_volver.setBorderPainted(false);
        bt_volver.setOpaque(true);
        bt_volver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt_Cerrar_sesion.setBackground(Color.WHITE);
        bt_Cerrar_sesion.setForeground(new Color(40, 167, 69));
        bt_Cerrar_sesion.setFocusPainted(false);
        bt_Cerrar_sesion.setBorder(new LineBorder(new Color(40, 167, 69), 2));
        bt_Cerrar_sesion.setOpaque(true);
        bt_Cerrar_sesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.getContentPane().setBackground(new Color(245, 245, 245));
    }

    public void cargarInformacionInventario(int idAmbiente) {
        this.idAmbiente = idAmbiente;

        try {
            PreparedStatement ps = conexion.prepareStatement(
                "SELECT nombre FROM ambiente WHERE id = ?");
            ps.setInt(1, idAmbiente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lb_NumAmbiente.setText(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

        cargarHistorial();
    }

    private void cargarHistorial() {
        DefaultTableModel modelo = (DefaultTableModel) tb_historial.getModel();
        modelo.setRowCount(0);

        HistorialRevision hr = new HistorialRevision();
        var lista = hr.listarPorAmbiente(idAmbiente);

        while (lista.hasNext()) {
            HistorialRevision item = lista.next();
            String tipo = item.getTipoAccion();
            switch (tipo) {
                case "checklist": tipo = "Checklist"; break;
                case "reporte": tipo = "Reporte"; break;
                case "solicitud": tipo = "Solicitud"; break;
                case "edicion_inventario": tipo = "Edición"; break;
            }
            modelo.addRow(new Object[]{
                tipo,
                item.getDescripcion(),
                item.getFechaRevision() != null ? item.getFechaRevision().toString() : ""
            });
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bt_Cerrar_sesion = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lb_NumAmbiente = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bt_volver = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_historial = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sistema de Gesti\u00f3n de Inventarios");

        jLabel2.setText("Usuario: admin");

        bt_Cerrar_sesion.setText("Cerrar sesi\u00f3n");
        bt_Cerrar_sesion.addActionListener(this::bt_Cerrar_sesionActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 107, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(bt_Cerrar_sesion)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_Cerrar_sesion)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Historial de Revisiones");

        lb_NumAmbiente.setFont(new java.awt.Font("Segoe UI", 1, 14));

        jLabel4.setText("Registro de todas las acciones realizadas en este ambiente");

        bt_volver.setText("Volver al inventario");
        bt_volver.addActionListener(this::bt_volverActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lb_NumAmbiente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(bt_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lb_NumAmbiente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tb_historial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo", "Descripci\u00f3n", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_historial);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void bt_volverActionPerformed(java.awt.event.ActionEvent evt) {
        javax.swing.JDesktopPane dp = getDesktopPane();
        if (dp == null) return;
        for (javax.swing.JInternalFrame f : dp.getAllFrames()) {
            if (f instanceof FRMDetalleInventario) {
                f.setVisible(true);
                break;
            }
        }
        this.dispose();
    }

    private void bt_Cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {
        int opcion = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "\u00bfDesea cerrar sesi\u00f3n?",
            "Cerrar sesi\u00f3n",
            javax.swing.JOptionPane.YES_NO_OPTION
        );
        if (opcion == javax.swing.JOptionPane.YES_OPTION) {
            javax.swing.JDesktopPane dp = getDesktopPane();
            FRMLogin login = null;
            if (dp != null) {
                for (javax.swing.JInternalFrame f : dp.getAllFrames()) {
                    if (f instanceof FRMLogin) {
                        login = (FRMLogin) f;
                        break;
                    }
                }
            }
            if (login == null) {
                login = new FRMLogin();
                if (dp != null) dp.add(login);
            }
            login.setVisible(true);
            this.dispose();
        }
    }

    private javax.swing.JButton bt_Cerrar_sesion;
    private javax.swing.JButton bt_volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_NumAmbiente;
    private javax.swing.JTable tb_historial;
}

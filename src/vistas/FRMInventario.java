/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import modelo.Ambiente;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


/**
 *
 * @author lemag
 */
public class FRMInventario extends javax.swing.JInternalFrame {

    private javax.swing.JDesktopPane escritorio;

    /**
     * Creates new form FRMInventario
     */
    public FRMInventario() {
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
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.WHITE);
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 11));
        jLabel3.setFont(new Font("Arial", Font.BOLD, 14));
        jLabel3.setForeground(new Color(40, 167, 69));
        jLabel4.setFont(new Font("Arial", Font.PLAIN, 12));
        jLabel4.setForeground(new Color(51, 51, 51));
        
        txt_buscar_ambiente.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(206, 212, 218), 1, true),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        txt_buscar_ambiente.setFont(new Font("Arial", Font.PLAIN, 13));
        bt_añadir_amb.setBackground(new Color(40, 167, 69));
        bt_añadir_amb.setForeground(Color.WHITE);
        bt_añadir_amb.setFocusPainted(false);
        bt_añadir_amb.setBorderPainted(false);
        bt_añadir_amb.setOpaque(true);
        bt_añadir_amb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt_añadir_amb.setFont(new Font("Arial", Font.BOLD, 12));
        bt_Cerrar_sesion.setBackground(Color.WHITE);
        bt_Cerrar_sesion.setForeground(new Color(40, 167, 69));
        bt_Cerrar_sesion.setFocusPainted(false);
        bt_Cerrar_sesion.setBorder(new LineBorder(new Color(40, 167, 69), 2));
        bt_Cerrar_sesion.setOpaque(true);
        bt_Cerrar_sesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt_volver.setBackground(Color.WHITE);
        bt_volver.setForeground(new Color(40, 167, 69));
        bt_volver.setFocusPainted(false);
        bt_volver.setBorder(new LineBorder(new Color(40, 167, 69), 2));
        bt_volver.setOpaque(true);
        bt_volver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        panelTarjetas.setLayout(new java.awt.FlowLayout(
            java.awt.FlowLayout.LEFT,15,15));
        panelTarjetas.setBackground(new Color(245, 245, 245));
        
        txt_buscar_ambiente.setText("Buscar por nombre...");
        txt_buscar_ambiente.setForeground(Color.GRAY);
        
        cargarAmbientes();
        addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent e) {
                javax.swing.JDesktopPane dp = escritorio;
                if (dp == null) return;
                for (javax.swing.JInternalFrame f : dp.getAllFrames()) {
                    if (f instanceof FRMLogin) return;
                }
                for (javax.swing.JInternalFrame f : dp.getAllFrames()) {
                    if (f instanceof FRMMenu) {
                        f.setVisible(true);
                        return;
                    }
                }
            }
        });
    }

    @Override
    public void addNotify() {
        super.addNotify();
        escritorio = getDesktopPane();
    }
    
    public void agregarInventario(Ambiente amb) {
        JPanel tarjeta = crearTarjeta(amb);
        panelTarjetas.add(tarjeta);
        panelTarjetas.revalidate();
        panelTarjetas.repaint();
    }

    private JPanel crearTarjeta(Ambiente amb) {
        JPanel tarjeta = new JPanel();
        tarjeta.setPreferredSize(new Dimension(250, 140));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(40, 167, 69), 2, true),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

        JLabel lblNombre = new JLabel(amb.getNombre(), SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setForeground(new Color(40, 167, 69));
        lblNombre.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JLabel lblTipo = new JLabel(amb.getTipo(), SwingConstants.CENTER);
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTipo.setForeground(new Color(51, 51, 51));
        lblTipo.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JButton btnVer = new JButton("Ver inventario");
        btnVer.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        btnVer.setBackground(new Color(40, 167, 69));
        btnVer.setForeground(Color.WHITE);
        btnVer.setFocusPainted(false);
        btnVer.setBorderPainted(false);
        btnVer.setOpaque(true);
        btnVer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVer.setFont(new Font("Arial", Font.BOLD, 11));
        btnVer.addActionListener(e -> {
            FRMDetalleInventario detalle = new FRMDetalleInventario();
            detalle.cargarInformacionInventario(amb.getIdAmbiente());
            getDesktopPane().add(detalle);
            detalle.setVisible(true);
            setVisible(false);
        });

        tarjeta.add(javax.swing.Box.createVerticalGlue());
        tarjeta.add(lblNombre);
        tarjeta.add(javax.swing.Box.createVerticalStrut(10));
        tarjeta.add(lblTipo);
        tarjeta.add(javax.swing.Box.createVerticalStrut(10));
        tarjeta.add(btnVer);
        tarjeta.add(javax.swing.Box.createVerticalGlue());

        return tarjeta;
    }

    public void cargarAmbientes() {
        panelTarjetas.removeAll();

        Ambiente a = new Ambiente();
        var lista = a.listar();

        while (lista.hasNext()) {
            Ambiente amb = lista.next();
            agregarInventario(amb);
        }

        panelTarjetas.revalidate();
        panelTarjetas.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bt_Cerrar_sesion = new javax.swing.JButton();
        bt_volver = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_buscar_ambiente = new javax.swing.JTextField();
        bt_añadir_amb = new javax.swing.JButton();
        panelTarjetas = new javax.swing.JPanel();

        setIconifiable(true);
        setMaximizable(true);

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sistema de Gestion de Inventarios");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Usuario: admin ");

        bt_Cerrar_sesion.setText("Cerrar sesión");
        bt_Cerrar_sesion.addActionListener(this::bt_Cerrar_sesionActionPerformed);

        bt_volver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bt_volver.setText("<-");
        bt_volver.addActionListener(this::bt_volverActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bt_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(bt_Cerrar_sesion)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bt_Cerrar_sesion)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Inventario general");

        jLabel4.setText("Selecciona un ambiente para ver su inventario ");

        bt_añadir_amb.setText("Añadir Ambiente");
        bt_añadir_amb.addActionListener(this::bt_añadir_ambActionPerformed);

        javax.swing.GroupLayout panelTarjetasLayout = new javax.swing.GroupLayout(panelTarjetas);
        panelTarjetas.setLayout(panelTarjetasLayout);
        panelTarjetasLayout.setHorizontalGroup(
            panelTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );
        panelTarjetasLayout.setVerticalGroup(
            panelTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 223, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txt_buscar_ambiente, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(bt_añadir_amb, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_buscar_ambiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_añadir_amb))
                .addGap(18, 18, 18)
                .addComponent(panelTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_añadir_ambActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_añadir_ambActionPerformed
        FRMNuevoAmbiente nuevoAmb = new FRMNuevoAmbiente(this);

        this.getDesktopPane().add(nuevoAmb);
        nuevoAmb.setVisible(true);

        this.setVisible(false);
    }//GEN-LAST:event_bt_añadir_ambActionPerformed

    private void bt_Cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_Cerrar_sesionActionPerformed
        int opcion = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Desea cerrar sesión?",
            "Cerrar sesión",
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
    }//GEN-LAST:event_bt_Cerrar_sesionActionPerformed

    private void bt_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_volverActionPerformed
        javax.swing.JDesktopPane dp = getDesktopPane();
        if (dp == null) return;
        for (javax.swing.JInternalFrame f : dp.getAllFrames()) {
            if (f instanceof FRMMenu) {
                f.setVisible(true);
                break;
            }
        }
        this.dispose();
    }//GEN-LAST:event_bt_volverActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Cerrar_sesion;
    private javax.swing.JButton bt_añadir_amb;
    private javax.swing.JButton bt_volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelTarjetas;
    private javax.swing.JTextField txt_buscar_ambiente;
    // End of variables declaration//GEN-END:variables
}

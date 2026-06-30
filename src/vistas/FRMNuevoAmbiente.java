/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vistas;

import javax.swing.JOptionPane;
import modelo.Ambiente;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FRMNuevoAmbiente extends javax.swing.JInternalFrame {

    private Ambiente ambienteActual;
    private boolean editando = false;
    private final FRMInventario frmInventario;
    private javax.swing.JButton bt_eliminar;

    public FRMNuevoAmbiente(FRMInventario frmInventario) {
         this.frmInventario = frmInventario;

         bt_eliminar = new javax.swing.JButton("Eliminar");
         bt_eliminar.setVisible(false);

         initComponents();

         bt_eliminar.addActionListener(this::bt_eliminarActionPerformed);

          panelArticulos.setLayout(
            new BoxLayout(panelArticulos, BoxLayout.Y_AXIS));
    }

    public void cargarAmbiente(Ambiente amb) {
        ambienteActual = amb;
        editando = true;

        txt_Ambiente.setText(amb.getNombre());
        txt_ubicacion.setText(amb.getUbicacion());

        for (int i = 0; i < cbx_tipo.getItemCount(); i++) {
            if (cbx_tipo.getItemAt(i).equalsIgnoreCase(amb.getTipo())) {
                cbx_tipo.setSelectedIndex(i);
                break;
            }
        }

        bt_crearAmbiente.setText("Actualizar Ambiente");
        bt_eliminar.setVisible(true);
        jLabel3.setText("Editar Ambiente");
    }

    private void limpiarFormulario() {
        txt_Ambiente.setText("");
        txt_ubicacion.setText("");
        cbx_tipo.setSelectedIndex(0);

        panelArticulos.removeAll();
        panelArticulos.revalidate();
        panelArticulos.repaint();

        ambienteActual = null;
        editando = false;

        bt_crearAmbiente.setText("Crear Ambiente");
        bt_eliminar.setVisible(false);
        jLabel3.setText("Nuevo Ambiente");
    }
    

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
        txt_Ambiente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbx_tipo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txt_ubicacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        bt_Cancelar = new javax.swing.JButton();
        panelArticulos = new javax.swing.JPanel();
        bt_añadirArticulo = new javax.swing.JButton();
        bt_crearAmbiente = new javax.swing.JButton();

        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBackground(new java.awt.Color(44, 171, 44));

        jLabel1.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sisteme de Gestión de Inventarios");

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 63, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(bt_Cerrar_sesion)
                                .addGap(217, 217, 217))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(bt_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_Cerrar_sesion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Bookman Old Style", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nuevo Ambiente");

        jLabel4.setText("Número/ Nombre del ambiente");

        jLabel5.setText("Tipo");

        cbx_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Aula", "Laboratorio", "Oficina", "Bodega" }));

        jLabel6.setText("Ubicación");

        jLabel7.setText("Articulos del inventario");

        bt_Cancelar.setText("Cancelar");
        bt_Cancelar.addActionListener(this::bt_CancelarActionPerformed);

        javax.swing.GroupLayout panelArticulosLayout = new javax.swing.GroupLayout(panelArticulos);
        panelArticulos.setLayout(panelArticulosLayout);
        panelArticulosLayout.setHorizontalGroup(
            panelArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelArticulosLayout.setVerticalGroup(
            panelArticulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 75, Short.MAX_VALUE)
        );

        bt_añadirArticulo.setText("Agregar Articulo");
        bt_añadirArticulo.addActionListener(this::bt_añadirArticuloActionPerformed);

        bt_crearAmbiente.setText("Crear Ambiente");
        bt_crearAmbiente.addActionListener(this::bt_crearAmbienteActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_añadirArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(txt_Ambiente)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_ubicacion)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelArticulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_crearAmbiente, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_Ambiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbx_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelArticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_añadirArticulo)
                .addGap(12, 12, 12)
                .addComponent(bt_crearAmbiente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_Cancelar)
                .addContainerGap(43, Short.MAX_VALUE))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_Cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_Cerrar_sesionActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this,
            "¿Desea cerrar sesión?", "Cerrar sesión", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            FRMLogin login = new FRMLogin();
            this.getDesktopPane().add(login);
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_bt_Cerrar_sesionActionPerformed

    private void bt_añadirArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_añadirArticuloActionPerformed
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblNombre = new JLabel("Artículo:");
        JTextField txtNombre = new JTextField(10);

        JLabel lblCant = new JLabel("Cantidad:");
        JTextField txtCantidad = new JTextField(5);

        JLabel lblMin = new JLabel("Mín:");
        JTextField txtMinimo = new JTextField(5);

        JButton btnEliminar = new JButton("X");

    btnEliminar.addActionListener(e -> {
        panelArticulos.remove(fila);
        panelArticulos.revalidate();
        panelArticulos.repaint();
    });

    fila.add(lblNombre);
    fila.add(txtNombre);
    fila.add(lblCant);
    fila.add(txtCantidad);
    fila.add(lblMin);
    fila.add(txtMinimo);
    fila.add(btnEliminar);

    panelArticulos.add(fila);

    panelArticulos.revalidate();
    panelArticulos.repaint();
    }//GEN-LAST:event_bt_añadirArticuloActionPerformed

    private void bt_crearAmbienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_crearAmbienteActionPerformed
        String nombre = txt_Ambiente.getText().trim();
        String tipo = cbx_tipo.getSelectedItem().toString();
        String ubicacion = txt_ubicacion.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese el nombre del ambiente");
            return;
        }

        if (cbx_tipo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un tipo de ambiente");
            return;
        }

        if (ubicacion.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese la ubicación del ambiente");
            return;
        }

        try {
            Ambiente a = new Ambiente();
            a.setNombre(nombre);
            a.setTipo(tipo);
            a.setUbicacion(ubicacion);
            a.setDescripcion("");

            if (editando && ambienteActual != null) {
                a.setIdAmbiente(
                        ambienteActual.getIdAmbiente());
                a.modificar();

                JOptionPane.showMessageDialog(this,
                        "Ambiente actualizado correctamente");
            } else {
                a.insertar();

                JOptionPane.showMessageDialog(this,
                    "Ambiente creado correctamente");
            }

            frmInventario.cargarAmbientes();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage());
        }
    }//GEN-LAST:event_bt_crearAmbienteActionPerformed

    private void bt_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CancelarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_bt_CancelarActionPerformed

    private void bt_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_volverActionPerformed
        frmInventario.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bt_volverActionPerformed


    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if (ambienteActual == null) {
            return;
        }

        try {
            ambienteActual.eliminar();
            limpiarFormulario();
            frmInventario.cargarAmbientes();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Cancelar;
    private javax.swing.JButton bt_Cerrar_sesion;
    private javax.swing.JButton bt_añadirArticulo;
    private javax.swing.JButton bt_crearAmbiente;
    private javax.swing.JButton bt_volver;
    private javax.swing.JComboBox<String> cbx_tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelArticulos;
    private javax.swing.JTextField txt_Ambiente;
    private javax.swing.JTextField txt_ubicacion;
    // End of variables declaration//GEN-END:variables
}

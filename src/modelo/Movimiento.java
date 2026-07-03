package modelo;

import java.sql.Timestamp;

public class Movimiento {

    private int id;
    private int idArticulo;
    private int idUsuario;
    private String tipo;
    private int cantidad;
    private String observacion;
    private Timestamp fechaMovimiento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Timestamp getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Timestamp fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public void insertar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "INSERT INTO movimiento (id_articulo, id_usuario, tipo, cantidad, observacion, fecha_movimiento) "
                + "VALUES (?, ?, ?, ?, ?, ?)"
            );
            sql.setInt(1, this.idArticulo);
            sql.setInt(2, this.idUsuario);
            sql.setString(3, this.tipo);
            sql.setInt(4, this.cantidad);
            sql.setString(5, this.observacion != null ? this.observacion : "");
            sql.setTimestamp(6, this.fechaMovimiento != null ? this.fechaMovimiento : new Timestamp(System.currentTimeMillis()));
            sql.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al insertar movimiento: " + ex.getMessage());
        }
    }
}

package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class InventarioAmbiente {

    private int idInventarioAmbiente;
    private int idAmbiente;
    private int idArticulo;
    private int cantidad;
    private int cantidadMinima;
    private Timestamp fechaActualizacion;
    private String nombreAmbiente;
    private String nombreArticulo;

    public int getIdInventarioAmbiente() {
        return idInventarioAmbiente;
    }

    public void setIdInventarioAmbiente(int idInventarioAmbiente) {
        this.idInventarioAmbiente = idInventarioAmbiente;
    }

    public int getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNombreAmbiente() {
        return nombreAmbiente;
    }

    public void setNombreAmbiente(String nombreAmbiente) {
        this.nombreAmbiente = nombreAmbiente;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    @Override
    public String toString() {
        return nombreArticulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InventarioAmbiente)) {
            return false;
        }
        return this.idInventarioAmbiente == ((InventarioAmbiente) o).idInventarioAmbiente;
    }

    private InventarioAmbiente mapearFila(ResultSet rs) throws Exception {
        InventarioAmbiente ia = new InventarioAmbiente();
        ia.setIdInventarioAmbiente(rs.getInt("id"));
        ia.setIdAmbiente(rs.getInt("id_ambiente"));
        ia.setIdArticulo(rs.getInt("id_articulo"));
        ia.setCantidad(rs.getInt("cantidad"));
        ia.setCantidadMinima(rs.getInt("cantidad_minima"));
        ia.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion"));
        ia.setNombreAmbiente(rs.getString("nombre_ambiente"));
        ia.setNombreArticulo(rs.getString("nombre_articulo"));
        return ia;
    }

    public Iterator<InventarioAmbiente> listar() {
        ArrayList<InventarioAmbiente> lista = new ArrayList<>();
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "SELECT ia.*, a.nombre AS nombre_ambiente, ar.nombre AS nombre_articulo "
                + "FROM inventario_ambiente ia "
                + "INNER JOIN ambiente a ON ia.id_ambiente = a.id "
                + "INNER JOIN articulo ar ON ia.id_articulo = ar.id"
            );
            var rs = sql.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar inventario: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            InventarioAmbiente vacio = new InventarioAmbiente();
            vacio.setNombreArticulo("Sin registros");
            lista.add(vacio);
        }
        return lista.iterator();
    }

    public Iterator<InventarioAmbiente> listarPorAmbiente(int idAmbiente) {
        ArrayList<InventarioAmbiente> lista = new ArrayList<>();
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "SELECT ia.*, a.nombre AS nombre_ambiente, ar.nombre AS nombre_articulo "
                + "FROM inventario_ambiente ia "
                + "INNER JOIN ambiente a ON ia.id_ambiente = a.id "
                + "INNER JOIN articulo ar ON ia.id_articulo = ar.id "
                + "WHERE ia.id_ambiente = ?"
            );
            sql.setInt(1, idAmbiente);
            var rs = sql.executeQuery();

            while (rs.next()) {
                lista.add(mapearFila(rs));
            }
        } catch (Exception ex) {
            System.err.println("Error al listar inventario por ambiente: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            InventarioAmbiente vacio = new InventarioAmbiente();
            vacio.setNombreArticulo("Sin registros");
            lista.add(vacio);
        }
        return lista.iterator();
    }

    public void insertar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "INSERT INTO inventario_ambiente (id_ambiente, id_articulo, cantidad, cantidad_minima) "
                + "VALUES (?, ?, ?, ?)"
            );

            sql.setInt(1, this.idAmbiente);
            sql.setInt(2, this.idArticulo);
            sql.setInt(3, this.cantidad);
            sql.setInt(4, this.cantidadMinima);
            sql.executeUpdate();
            System.out.println("Inventario insertado correctamente");
        } catch (Exception ex) {
            System.err.println("Error al insertar inventario: " + ex.getMessage());
        }
    }

    public boolean modificar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "UPDATE inventario_ambiente SET id_ambiente=?, id_articulo=?, cantidad=?, cantidad_minima=? WHERE id=?"
            );

            sql.setInt(1, this.idAmbiente);
            sql.setInt(2, this.idArticulo);
            sql.setInt(3, this.cantidad);
            sql.setInt(4, this.cantidadMinima);
            sql.setInt(5, this.idInventarioAmbiente);
            sql.executeUpdate();
            System.out.println("Inventario modificado correctamente");
        } catch (Exception ex) {
            System.err.println("Error al modificar inventario: " + ex.getMessage());
        }
        return cantidad < cantidadMinima;
    }

    public void eliminarPorAmbiente(int idAmbiente) {
        try {
            var sql = ConexionBD.conexion.prepareStatement("DELETE FROM inventario_ambiente WHERE id_ambiente=?");

            sql.setInt(1, idAmbiente);
            sql.executeUpdate();
            System.out.println("Inventario eliminado por ambiente");
        } catch (Exception ex) {
            System.err.println("Error al eliminar inventario por ambiente: " + ex.getMessage());
        }
    }

    public void eliminar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement("DELETE FROM inventario_ambiente WHERE id=?");

            sql.setInt(1, this.idInventarioAmbiente);
            sql.executeUpdate();
            System.out.println("Inventario eliminado");
        } catch (Exception ex) {
            System.err.println("Error al eliminar inventario: " + ex.getMessage());
        }
    }

    public boolean tieneStockBajo() {
        return cantidad < cantidadMinima;
    }
}

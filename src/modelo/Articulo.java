package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Timestamp;

public class Articulo {

    private int idArticulo;
    private String nombre;
    private String codigo;
    private String descripcion;
    private int idCategoria;
    private int cantidad;
    private String estado;
    private int nivelMinimo;
    private Timestamp fechaPublicacion;
    private String nombreCategoria;

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNivelMinimo() {
        return nivelMinimo;
    }

    public void setNivelMinimo(int nivelMinimo) {
        this.nivelMinimo = nivelMinimo;
    }

    public Timestamp getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Timestamp fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Articulo)) {
            return false;
        }
        return this.idArticulo == ((Articulo) o).idArticulo;
    }

    public Iterator<Articulo> listar() {
        ArrayList<Articulo> lista = new ArrayList<>();
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "SELECT a.*, c.nombre AS nombre_categoria FROM articulo a "
                + "INNER JOIN categoria c ON a.id_categoria = c.id"
            );
            var rs = sql.executeQuery();

            while (rs.next()) {
                Articulo a = new Articulo();
                a.setIdArticulo(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                a.setCodigo(rs.getString("codigo"));
                a.setDescripcion(rs.getString("descripcion"));
                a.setIdCategoria(rs.getInt("id_categoria"));
                a.setCantidad(rs.getInt("cantidad"));
                a.setEstado(rs.getString("estado"));
                a.setNivelMinimo(rs.getInt("nivel_minimo"));
                a.setFechaPublicacion(rs.getTimestamp("fecha_publicacion"));
                a.setNombreCategoria(rs.getString("nombre_categoria"));
                lista.add(a);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar articulos: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            Articulo vacio = new Articulo();
            vacio.setNombre("Sin registros");
            lista.add(vacio);
        }
        return lista.iterator();
    }

    public void insertar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "INSERT INTO articulo (nombre, codigo, descripcion, id_categoria, cantidad, estado, nivel_minimo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            sql.setString(1, this.nombre);
            sql.setString(2, this.codigo);
            sql.setString(3, this.descripcion);
            sql.setInt(4, this.idCategoria);
            sql.setInt(5, this.cantidad);
            sql.setString(6, this.estado);
            sql.setInt(7, this.nivelMinimo);
            sql.executeUpdate();
            System.out.println("Articulo insertado correctamente");
        } catch (Exception ex) {
            System.err.println("Error al insertar articulo: " + ex.getMessage());
        }
    }

    public void modificar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "UPDATE articulo SET nombre=?, codigo=?, descripcion=?, id_categoria=?, cantidad=?, estado=?, nivel_minimo=? WHERE id=?"
            );

            sql.setString(1, this.nombre);
            sql.setString(2, this.codigo);
            sql.setString(3, this.descripcion);
            sql.setInt(4, this.idCategoria);
            sql.setInt(5, this.cantidad);
            sql.setString(6, this.estado);
            sql.setInt(7, this.nivelMinimo);
            sql.setInt(8, this.idArticulo);
            sql.executeUpdate();
            System.out.println("Articulo modificado correctamente");
        } catch (Exception ex) {
            System.err.println("Error al modificar articulo: " + ex.getMessage());
        }
    }

    public void eliminar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement("DELETE FROM articulo WHERE id=?");

            sql.setInt(1, this.idArticulo);
            sql.executeUpdate();
            System.out.println("Articulo eliminado");
        } catch (Exception ex) {
            System.err.println("Error al eliminar articulo: " + ex.getMessage());
        }
    }
}

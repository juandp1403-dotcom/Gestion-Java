package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Categoria {

    private int idCategoria;
    private String nombre;
    private String descripcion;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(o instanceof Categoria)) {
            return false;
        }
        return this.idCategoria == ((Categoria) o).idCategoria;
    }

    public Iterator<Categoria> listar() {
        ArrayList<Categoria> lista = new ArrayList<>();
        try {
            var sql = ConexionBD.conexion.prepareStatement("SELECT * FROM categoria");
            var rs = sql.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                lista.add(c);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar categorias: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            Categoria vacio = new Categoria();
            vacio.setNombre("Sin registros");
            lista.add(vacio);
        }
        return lista.iterator();
    }

    public void insertar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement("INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)");

            sql.setString(1, this.nombre);
            sql.setString(2, this.descripcion);
            sql.executeUpdate();
            System.out.println("Categoria insertada correctamente");
        } catch (Exception ex) {
            System.err.println("Error al insertar categoria: " + ex.getMessage());
        }
    }

    public void modificar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement("UPDATE categoria SET nombre=?, descripcion=? WHERE id=?");

            sql.setString(1, this.nombre);
            sql.setString(2, this.descripcion);
            sql.setInt(3, this.idCategoria);
            sql.executeUpdate();
            System.out.println("Categoria modificada correctamente");
        } catch (Exception ex) {
            System.err.println("Error al modificar categoria: " + ex.getMessage());
        }
    }

    public void eliminar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement("DELETE FROM categoria WHERE id=?");

            sql.setInt(1, this.idCategoria);
            sql.executeUpdate();
            System.out.println("Categoria eliminada");
        } catch (Exception ex) {
            System.err.println("Error al eliminar categoria: " + ex.getMessage());
        }
    }
}

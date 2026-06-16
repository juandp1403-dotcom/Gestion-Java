package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Ambiente {

    private int idAmbiente;
    private String nombre;
    private String tipo;
    private String ubicacion;
    private String descripcion;

    public int getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
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
        if (!(o instanceof Ambiente)) {
            return false;
        }
        return this.idAmbiente == ((Ambiente) o).idAmbiente;
    }

    public Iterator<Ambiente> listar() {
        ArrayList<Ambiente> lista = new ArrayList<>();
        try {
            var sql = ConexionBD.conexion.prepareStatement("SELECT * FROM ambiente");
            var rs = sql.executeQuery();

            while (rs.next()) {
                Ambiente a = new Ambiente();
                a.setIdAmbiente(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                a.setTipo(rs.getString("tipo"));
                a.setUbicacion(rs.getString("ubicacion"));
                a.setDescripcion(rs.getString("descripcion"));
                lista.add(a);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar ambientes: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            Ambiente vacio = new Ambiente();
            vacio.setNombre("Sin registros");
            lista.add(vacio);
        }
        return lista.iterator();
    }

    public void insertar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement("INSERT INTO ambiente (nombre, tipo, ubicacion, descripcion) VALUES (?, ?, ?, ?)");

            sql.setString(1, this.nombre);
            sql.setString(2, this.tipo);
            sql.setString(3, this.ubicacion);
            sql.setString(4, this.descripcion);
            sql.executeUpdate();
            System.out.println("Ambiente insertado correctamente");
        } catch (Exception ex) {
            System.err.println("Error al insertar ambiente: " + ex.getMessage());
        }
    }

    public void modificar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement("UPDATE ambiente SET nombre=?, tipo=?, ubicacion=?, descripcion=? WHERE id=?");

            sql.setString(1, this.nombre);
            sql.setString(2, this.tipo);
            sql.setString(3, this.ubicacion);
            sql.setString(4, this.descripcion);
            sql.setInt(5, this.idAmbiente);
            sql.executeUpdate();
            System.out.println("Ambiente modificado correctamente");
        } catch (Exception ex) {
            System.err.println("Error al modificar ambiente: " + ex.getMessage());
        }
    }

    public void eliminar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement("DELETE FROM ambiente WHERE id=?");

            sql.setInt(1, this.idAmbiente);
            sql.executeUpdate();
            System.out.println("Ambiente eliminado");
        } catch (Exception ex) {
            System.err.println("Error al eliminar ambiente: " + ex.getMessage());
        }
    }
}

package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Reporte {

    private int id;
    private String tipo;
    private String filtros;
    private int idUsuario;
    private int idAmbiente;
    private Timestamp fechaCreacion;
    private String nombreAmbiente;
    private String nombreUsuario;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getFiltros() { return filtros; }
    public void setFiltros(String filtros) { this.filtros = filtros; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdAmbiente() { return idAmbiente; }
    public void setIdAmbiente(int idAmbiente) { this.idAmbiente = idAmbiente; }

    public Timestamp getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Timestamp fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getNombreAmbiente() { return nombreAmbiente; }
    public void setNombreAmbiente(String nombreAmbiente) { this.nombreAmbiente = nombreAmbiente; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public Iterator<Reporte> listar() {
        ArrayList<Reporte> lista = new ArrayList<>();
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "SELECT r.*, a.nombre AS nombre_ambiente, u.nombre AS nombre_usuario " +
                "FROM reporte r " +
                "INNER JOIN ambiente a ON r.id_ambiente = a.id " +
                "LEFT JOIN usuario u ON r.id_usuario = u.id " +
                "ORDER BY r.fecha_creacion DESC"
            );
            var rs = sql.executeQuery();
            while (rs.next()) {
                Reporte r = new Reporte();
                r.setId(rs.getInt("id"));
                r.setTipo(rs.getString("tipo"));
                r.setFiltros(rs.getString("filtros"));
                r.setIdUsuario(rs.getInt("id_usuario"));
                r.setIdAmbiente(rs.getInt("id_ambiente"));
                r.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                r.setNombreAmbiente(rs.getString("nombre_ambiente"));
                r.setNombreUsuario(rs.getString("nombre_usuario"));
                lista.add(r);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar reportes: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            Reporte vacio = new Reporte();
            vacio.setTipo("Sin registros");
            lista.add(vacio);
        }
        return lista.iterator();
    }
}

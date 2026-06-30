package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Solicitud {

    private int id;
    private int idUsuario;
    private int idAmbiente;
    private int cantidad;
    private String estado;
    private String justificacion;
    private Timestamp fechaSolicitud;
    private String nombreAmbiente;
    private String nombreUsuario;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getIdAmbiente() { return idAmbiente; }
    public void setIdAmbiente(int idAmbiente) { this.idAmbiente = idAmbiente; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getJustificacion() { return justificacion; }
    public void setJustificacion(String justificacion) { this.justificacion = justificacion; }

    public Timestamp getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(Timestamp fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public String getNombreAmbiente() { return nombreAmbiente; }
    public void setNombreAmbiente(String nombreAmbiente) { this.nombreAmbiente = nombreAmbiente; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public Iterator<Solicitud> listar() {
        ArrayList<Solicitud> lista = new ArrayList<>();
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "SELECT s.*, a.nombre AS nombre_ambiente, u.nombre AS nombre_usuario " +
                "FROM solicitud s " +
                "INNER JOIN ambiente a ON s.id_ambiente = a.id " +
                "LEFT JOIN usuario u ON s.id_usuario = u.id " +
                "ORDER BY s.fecha_solicitud DESC"
            );
            var rs = sql.executeQuery();
            while (rs.next()) {
                Solicitud s = new Solicitud();
                s.setId(rs.getInt("id"));
                s.setIdUsuario(rs.getInt("id_usuario"));
                s.setIdAmbiente(rs.getInt("id_ambiente"));
                s.setCantidad(rs.getInt("cantidad"));
                s.setEstado(rs.getString("estado"));
                s.setJustificacion(rs.getString("justificacion"));
                s.setFechaSolicitud(rs.getTimestamp("fecha_solicitud"));
                s.setNombreAmbiente(rs.getString("nombre_ambiente"));
                s.setNombreUsuario(rs.getString("nombre_usuario"));
                lista.add(s);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar solicitudes: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            Solicitud vacio = new Solicitud();
            vacio.setJustificacion("Sin registros");
            lista.add(vacio);
        }
        return lista.iterator();
    }
}

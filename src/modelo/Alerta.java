package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class Alerta {

    private int id;
    private String titulo;
    private String mensaje;
    private String tipo;
    private int idUsuarioDestino;
    private int idReferencia;
    private Timestamp fechaCreacion;
    private boolean leida;
    private String nombreUsuarioDestino;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getIdUsuarioDestino() { return idUsuarioDestino; }
    public void setIdUsuarioDestino(int idUsuarioDestino) { this.idUsuarioDestino = idUsuarioDestino; }

    public int getIdReferencia() { return idReferencia; }
    public void setIdReferencia(int idReferencia) { this.idReferencia = idReferencia; }

    public Timestamp getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Timestamp fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public boolean isLeida() { return leida; }
    public void setLeida(boolean leida) { this.leida = leida; }

    public String getNombreUsuarioDestino() { return nombreUsuarioDestino; }
    public void setNombreUsuarioDestino(String nombreUsuarioDestino) { this.nombreUsuarioDestino = nombreUsuarioDestino; }

    public Iterator<Alerta> listar() {
        ArrayList<Alerta> lista = new ArrayList<>();
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "SELECT a.*, COALESCE(u.nombre, 'Sistema') AS nombre_usuario " +
                "FROM alerta a " +
                "LEFT JOIN usuario u ON a.id_usuario_destino = u.id " +
                "ORDER BY a.fecha_creacion DESC"
            );
            var rs = sql.executeQuery();
            while (rs.next()) {
                Alerta a = new Alerta();
                a.setId(rs.getInt("id"));
                a.setTitulo(rs.getString("titulo"));
                a.setMensaje(rs.getString("mensaje"));
                a.setTipo(rs.getString("tipo"));
                a.setIdUsuarioDestino(rs.getInt("id_usuario_destino"));
                a.setIdReferencia(rs.getInt("id_referencia"));
                a.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
                a.setLeida(rs.getBoolean("leida"));
                a.setNombreUsuarioDestino(rs.getString("nombre_usuario"));
                lista.add(a);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar alertas: " + ex.getMessage());
        }
        if (lista.isEmpty()) {
            Alerta vacio = new Alerta();
            vacio.setTitulo("Sin registros");
            lista.add(vacio);
        }
        return lista.iterator();
    }
}

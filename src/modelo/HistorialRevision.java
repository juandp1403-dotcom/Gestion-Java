package modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

public class HistorialRevision {

    private int id;
    private int idAmbiente;
    private Timestamp fechaRevision;
    private String tipoAccion;
    private String descripcion;
    private Integer idReferencia;
    private int idUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAmbiente() {
        return idAmbiente;
    }

    public void setIdAmbiente(int idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    public Timestamp getFechaRevision() {
        return fechaRevision;
    }

    public void setFechaRevision(Timestamp fechaRevision) {
        this.fechaRevision = fechaRevision;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(Integer idReferencia) {
        this.idReferencia = idReferencia;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void insertar() {
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "INSERT INTO historial_revision "
                + "(id_ambiente, fecha_revision, tipo_accion, descripcion, id_referencia, id_usuario) "
                + "VALUES (?, ?, ?, ?, ?, ?)"
            );
            sql.setInt(1, this.idAmbiente);
            sql.setTimestamp(2, this.fechaRevision != null ? this.fechaRevision : new Timestamp(System.currentTimeMillis()));
            sql.setString(3, this.tipoAccion);
            sql.setString(4, this.descripcion);
            if (this.idReferencia != null) {
                sql.setInt(5, this.idReferencia);
            } else {
                sql.setNull(5, java.sql.Types.INTEGER);
            }
            sql.setInt(6, this.idUsuario);
            sql.executeUpdate();
        } catch (Exception ex) {
            System.err.println("Error al insertar historial_revision: " + ex.getMessage());
        }
    }

    public Iterator<HistorialRevision> listarPorAmbiente(int idAmbiente) {
        ArrayList<HistorialRevision> lista = new ArrayList<>();
        try {
            var sql = ConexionBD.conexion.prepareStatement(
                "SELECT * FROM historial_revision WHERE id_ambiente = ? ORDER BY fecha_revision DESC"
            );
            sql.setInt(1, idAmbiente);
            var rs = sql.executeQuery();

            while (rs.next()) {
                HistorialRevision hr = new HistorialRevision();
                hr.setId(rs.getInt("id"));
                hr.setIdAmbiente(rs.getInt("id_ambiente"));
                hr.setFechaRevision(rs.getTimestamp("fecha_revision"));
                hr.setTipoAccion(rs.getString("tipo_accion"));
                hr.setDescripcion(rs.getString("descripcion"));
                hr.setIdReferencia((Integer) rs.getObject("id_referencia"));
                hr.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(hr);
            }
        } catch (Exception ex) {
            System.err.println("Error al listar historial_revision: " + ex.getMessage());
        }
        return lista.iterator();
    }
}

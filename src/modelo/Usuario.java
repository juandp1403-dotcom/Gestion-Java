/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class Usuario {

    private int idUsuario;
    private String nombre;
    private String email;
    private String password;
    private Timestamp creadoEn;
    private boolean aprovado;
    private boolean activo;
    private int idRol;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Timestamp creadoEn) {
        this.creadoEn = creadoEn;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
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
        if (!(o instanceof Usuario)) {
            return false;
        }
        return this.idUsuario == ((Usuario) o).idUsuario;
    }

    // Listar
    public Iterator<Usuario> listar() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            var sql = ConexionBD.conexion.prepareStatement("SELECT * FROM usuario");
            var rs = sql.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setCreadoEn(rs.getTimestamp("creado_en"));
                u.setAprovado(rs.getBoolean("aprobado"));
                u.setActivo(rs.getBoolean("activo"));
                lista.add(u);
            }
        } catch (Exception ex){
            System.err.println("Error al listar: " + ex.getMessage());
        } if (lista.isEmpty()){
            Usuario vacio = new Usuario();
            vacio.setNombre("No hay usuarios");
            lista.add(vacio);
        }
        return lista.iterator();
    }
    
    //Insertar
    public void insertar(){
        try{
            var sql = ConexionBD.conexion.prepareStatement("INSERT INTO usuario (nombre, email, password, id_rol, aprobado, activo) VALUES (?, ?, ?, ?, ?, ?)");
            
            sql.setString(1, this.nombre);
            sql.setString(2, this.email);
            sql.setString(3, this.password);
            sql.setInt(4, this.idRol);
            sql.setBoolean(5, this.aprovado);
            sql.setBoolean(6, this.activo);
            sql.executeUpdate();
            System.out.println("Usuario insertado correctamente");
        } catch (Exception ex) {
            System.err.println("Error al insertar: " + ex.getMessage());
        }
    }
    
    //Modificar
    public void modificar(){
        try{
            var sql = ConexionBD.conexion.prepareStatement("UPDATE usuario SET nombre=?, email=?, password=?, aprobado=?, activo=? WHERE id=?");
            
            sql.setString(1, this.nombre);
            sql.setString(2, this.email);
            sql.setString(3, this.password);
            sql.setBoolean(4, this.aprovado);
            sql.setBoolean(5, this.activo);
            sql.setInt(6, this.idUsuario);
            sql.executeUpdate();
            System.out.println("Usuario modificado correctamente");
        }catch (Exception ex) {
            System.err.println("Error al modificar: " + ex.getMessage());
        }
    }
    
    //Eliminar
    public void eliminar(){
        try{
            var sql = ConexionBD.conexion.prepareStatement("DELETE FROM usuario WHERE id=?");
            
            sql.setInt(1, this.idUsuario);
            sql.executeUpdate();
            System.out.println("Usuario eliminado");
        }catch (Exception ex) {
            System.err.println("Error al modificar: " + ex.getMessage());
        }
    }
    
    //Buscar
    public Iterator<Usuario> buscar (String busqueda){
        ArrayList<Usuario> lista = new ArrayList<>();
        try{
            var sql = ConexionBD.conexion.prepareStatement("SELECT * FROM usuario WHERE nombre LIKE ? OR email LIKE ?");
            
            String b = "%" + busqueda + "%";
            sql.setString(1, b);
            sql.setString(2, b);
            var rs = sql.executeQuery();
            while (rs.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setCreadoEn(rs.getTimestamp("creado_en"));
                u.setAprovado(rs.getBoolean("aprobado"));
                u.setActivo(rs.getBoolean("activo"));
                lista.add(u);
            }
        }catch (Exception ex) {
            System.err.println("Error al buscar: " + ex.getMessage());
        }
        return lista.iterator();
    }
}

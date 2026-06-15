/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package modelo;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author ASUS
 */
public class ConexionBD {
    public static Connection conexion;
    
    private ConexionBD() {
        try{
            
            Properties prop = new Properties();
            InputStream Input = getClass()
                    .getClassLoader()
                    .getResourceAsStream("config.properties");
            prop.load(Input);
            
            String driver = prop.getProperty("db.driver");
            String url = prop.getProperty("db.url");
            String usuario = prop.getProperty("db.user");
            String clave = prop.getProperty("db.clave");
            
            Class.forName(driver);
            conexion = DriverManager.getConnection(url,usuario,clave);
            System.out.println("Conexion exitosa");
        }catch (Exception ex){
            System.err.println("Error al conectar: " + ex.getMessage());
        }
    }
    
    public static void desconectar(){
        try{
            conexion.close();
        } catch (Exception ex){
            System.err.println("Error al desconectar: " + ex.getMessage());
        }
    }
    
    public static ConexionBD getInstance() {
        return ConexionBDHolder.INSTANCE;
    }
    
    private static class ConexionBDHolder {

        private static final ConexionBD INSTANCE = new ConexionBD();
    }
}

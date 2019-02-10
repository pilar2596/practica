/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pilar
 */
public class GestorConexion {
    
    Connection conn1;
     String cadenaResult = "";
    
    public GestorConexion(){
    
     conn1 = null;
    
    try{
    
    String url1 = "jdbc:mysql://localhost:3306/discografica";
    String user = "root";
    String password = "";
    
    conn1 = (Connection)DriverManager.getConnection(url1,user,password);
    if(conn1 !=null){
        System.out.println("Conectado a discografia...");
        
    }
}catch (SQLException ex){
    System.out.println("ERROR: direccion o usuario/clave no valida");
    ex.printStackTrace();
}
 
    
}
    public void cerrar_conexion(){
        
        try{
        conn1.close();
        System.out.println("Conexion cerrada");
        }catch (SQLException ex){
            System.out.println("Error al cerrar la conexion");
            ex.printStackTrace();
        }
        
    }
    
    
     public void Anade(String Caratula) {

        try {

            conn1.setAutoCommit(false);

            Statement sta = conn1.createStatement();
            sta.executeUpdate("ALTER TABLE album ADD " + Caratula + " VARCHAR(30)");
            sta.close();
            conn1.commit();
            
            System.out.println("ha ido bien añadir");
        } catch (SQLException ex) {

            System.out.println("ha ido mal añadir");

            try {
                if (conn1 != null) {
                    conn1.rollback();
                }
            } catch (SQLException se2) {
                se2.printStackTrace();

            }
            ex.printStackTrace();
        }

    }


    

    public void insertarCancion(String titulo, String duracion, String letras, String album) {
        try {
            conn1.setAutoCommit(false);

            Statement sta = conn1.createStatement();

            sta.executeUpdate("INSERT INTO cancion VALUES('" + titulo + "', '" + duracion + "', '" + letras + "', '" + album + "')");

            System.out.println("insertado correcto");

            sta.close();

            conn1.commit();
        } catch (Exception e) {
            System.out.println("Se ha producido un error");

            try {
                if (conn1 != null) {
                    conn1.rollback();
                }
            } catch (Exception se2) {
                se2.printStackTrace();
                cadenaResult = se2.toString();
            }

            e.printStackTrace();
            cadenaResult = e.toString();
        }
    }

    public void insertarAlbum(String Id, String titulo, String Year) {
        try {
            conn1.setAutoCommit(false);
            Statement sta = conn1.createStatement();
            
            sta.executeUpdate("INSERT INTO album VALUES('" + Id + "', '" + titulo + "', '" + Year + "')");
            System.out.println("insertado correcto");

            sta.close();
            conn1.commit();

        } catch (Exception e) {
            System.out.println("Error");

            try {
                if (conn1 != null) {
                    conn1.rollback();

                }

            } catch (Exception se2) {
                se2.printStackTrace();
                cadenaResult = se2.toString();

            }
            e.printStackTrace();
            cadenaResult = e.toString();
        }
    }

    public String consulta_Statement() {
        String error = "error";

        try {
            conn1.setAutoCommit(false);
            Statement sta = conn1.createStatement();

            String query = "SELECT * FROM album ";
            ResultSet rs = sta.executeQuery(query);
            ResultSetMetaData metaDatos = rs.getMetaData();

            int numColumnas = metaDatos.getColumnCount();

            return cadenaResult;

        } catch (Exception e) {
            System.out.println("error");

            try {
                if (conn1 != null) {
                    conn1.rollback();
                }
            } catch (Exception se2) {
                se2.printStackTrace();
            }
            e.printStackTrace();
            return error;

        }

    }
    
    

    
    
    
}

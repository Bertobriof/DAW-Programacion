/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.appdebuxo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alber
 */
public class ConnectionManager {
    //Constantes de conexión
    public static final String JDBC_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:C:\\tprog\\bd\\h2\\bd\\debuxos";
    public static final String DB_USER = "";
    public static final String DB_PASS = "";
    
    private static ConnectionManager conexionManager;
    
    //Atributos
    
    private final Connection conexion;
    /**
     * Constructor privado que se crea con un método estático.
     * @param conexion 
     */
    private ConnectionManager(Connection conexion) {
        this.conexion = conexion;
    }
    
    //Métodos:
    public static final ConnectionManager getConectionManager() throws SQLException, ClassNotFoundException {
        try {
            if(conexionManager == null || conexionManager.isClosed()) {
                //Evita que puedan crearse dos a la vez.
                synchronized(Connection.class) {
                if(conexionManager == null || conexionManager.isClosed()) {
                    Class.forName(JDBC_DRIVER); //No necesario
                    conexionManager = new ConnectionManager (DriverManager.getConnection(DB_URL, DB_USER, DB_PASS));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro conextion: "+ ex.getMessage());
    } 
        return conexionManager;
}
    
    
    public Connection getConnection() {
        return conexion;
    }
    
    public boolean isClosed() {
        try {
            return conexion.isClosed();
        } catch(SQLException ex) {
            System.out.println("Error de SQL: "+ex.getMessage());
        }
        return true;
    } 
}

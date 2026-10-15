/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sesi2dib
 */





public class MySQLConnection {
    
    // Define strings de conexão com o banco.
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/nicolas_dukemarket";
    
    private static final String USER = "nicolas";
    private static final String PASS = "2diSenai";





public static Connection getConnection() {
    try {
    Class.forName(DRIVER);
    return DriverManager.getConnection(URL, USER, PASS);
    } catch (SQLException ex) {
        throw new RuntimeException("Erro na Conexao com o BD. Verifique!", ex);
    } catch (ClassNotFoundException ex) {
        throw new RuntimeException("Falha na carga do Driver", ex);
    }
}


public static void closeConnection(Connection conn) {
   try {
       if (conn != null) {
           conn.close();
       }
   } catch (SQLException ex) {
       Logger.getLogger(
        MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
       
   }
}

public static void closeConnection(Connection conn, PreparedStatement stmt) {
    closeConnection(conn);
    
    try {
        if (stmt != null) {
            stmt.close();
        }
    } catch (SQLException ex) {
        Logger.getLogger(
            MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
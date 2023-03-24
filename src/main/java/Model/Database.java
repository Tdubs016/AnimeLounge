/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Typan
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {
    
    private Connection theConnection;
    private final String URL = "jdbc:mysql://localhost:3306/animechat";
    private final String USERNAME = "root";
    private final String PASSWORD = "Starfire2211*";
    private static Database theDatabase = new Database();
    
    private Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //class name for MySQL Driver
            this.theConnection = DriverManager.getConnection(URL, USERNAME, PASSWORD); //retrieve database connection
        } catch (Exception ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Database getDB() {
        return theDatabase; //retrieve eager instantiated single instance of Database
    }
    public Connection getConnection() {
        return this.theConnection; //retrieve the re-usable connection object
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author palas
 */
public class DatabaseProvider {
    /**
     * instance
     */
    private static DatabaseProvider instance;
    
    /**
     * connection
     */
    private Connection connection;
    
    /**
     * statement
     */
    private Statement statement;
    
    /**
     * Opens a connection to the database and disables autocommit
     * @throws SQLException Forwarded exception
     */
    private void init() throws SQLException {
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/eshopping", "app", "app");
        connection.setAutoCommit(false);
        statement = connection.createStatement();
    }
    
    /**
     * Default constructor
     * @throws ClassNotFoundException Forwarded exception
     * @throws SQLException Forwarded exception
     */
    private DatabaseProvider() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        init();
    }
    
    /**
     * Sends an SQL query to the database
     * @param sql SQL query
     * @return Response from the database
     * @throws ClassNotFoundException Forwarded exception
     * @throws SQLException Forwarded exception
     */
    public static ResultSet query(String sql) throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new DatabaseProvider();
        }
        if (instance.connection.isClosed()) {
            instance.init();
        }
        return instance.statement.executeQuery(sql);
    }
    
    /**
     * Sends an SQL update request to the database
     * @param sql SQL request
     * @return Number of affected rows
     * @throws SQLException Forwarded exception
     * @throws ClassNotFoundException Forwarded exception
     */
    public static int update(String sql) throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DatabaseProvider();
        }
        if (instance.connection.isClosed()) {
            instance.init();
        }
        int returnValue = instance.statement.executeUpdate(sql);
        instance.connection.commit();
        return returnValue;
    }
    
}

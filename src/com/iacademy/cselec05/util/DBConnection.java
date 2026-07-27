package com.iacademy.cselec05.util;

// Import the stuff -- Juan Amado Cleto hohoho I am going to place my name in each comment because I am the project
// manager.

// Some reminders to my teammates -- its always good to comment because we have to know where the logic is and if we
// don't know, god help us all
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Alright so this is the DB connection where we have two problems
// 1. There are two database connections and that is a big no no for me because we have two connections
// So we have to centralize it
// This will be our central database connection
// And no, I am not going to have intelij ultimate because I am cheap -- I am Ilokano
public class DBConnection {

    // So far we are going to use a local server for the database handshake and binary reading stuff
    private static final String URL = "jdbc:mysql://localhost:3306/javaee1_db"; // Changed to javaee1_db
    private static final String USER = "root";
    private static final String PASS = "89antaraNus"; // Change password

    // Alright props that we made it static so that is going in one memory address or something
    static {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading MySQL JDBC driver");
            e.printStackTrace();
        }
    }

    // Good we have get connection for the USER DAO ----- so far this is for the USER DAO. I am yet to trace for the
    // Artworks database
    // And good that it is static -- so that we dont need to call it in other pages
    // So instances wouldn't a problem
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Good, we have this connection close.
    public static void close(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                System.err.println("Error closing resource: " + e.getMessage());
            }
        }
    }
}

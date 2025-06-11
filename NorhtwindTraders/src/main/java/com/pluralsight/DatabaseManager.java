package com.pluralsight;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/northwind";
    private static final String username = "root";   // <--- REPLACE WITH YOUR MySQL USERNAME
    private static final String password = "yearup"; // <--- REPLACE WITH YOUR MySQL PASSWORD


//      Establish and return connection to the Northwind database.
//      return active SQL Connection object, or null if connection fails.
//
    public static Connection getConnection() {
        Connection connection = null;
        try {
            System.out.println("Attempting to connect to the Northwind database...");
            connection = DriverManager.getConnection(URL, username, password);
            if (connection != null) {
                System.out.println("Successfully connected to the Northwind database!");
            }
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }


//     Close given database connection
//     @param connection The SQL Connection to close.

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection to Northwind database closed.");
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

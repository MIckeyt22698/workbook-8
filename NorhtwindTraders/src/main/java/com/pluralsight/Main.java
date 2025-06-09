package com.pluralsight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; // Import for Statement
import java.sql.ResultSet; // Import for ResultSet

public class Main {

    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/northwind";
        String user = "root";   //  MySQL USERNAME
        String password = "yearup"; // MySQL PASSWORD

        Connection connection = null;
        Statement statement = null; // Declare Statement object
        ResultSet resultSet = null; // Declare ResultSet object

        try {
            System.out.println("Attempting to connect to the database...");

            // Establish the connection
            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Successfully connected to the Northwind database!");

                // Execute query to select all products ---
                System.out.println("\n--- Listing all products ---");

                // Create a Statement executing SQL queries
                statement = connection.createStatement();

                // Execute SELECT query
                String sqlQuery = "SELECT ProductName FROM Products";
                resultSet = statement.executeQuery(sqlQuery);

                // Process ResultSet and display product names
                while (resultSet.next()) {
                    String productName = resultSet.getString("ProductName");
                    System.out.println("Product Name: " + productName);
                }

            } else {
                System.out.println("Failed to establish a connection.");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            //  Close resources in reverse order of creation
            if (resultSet != null) {
                try {
                    resultSet.close();
                    System.out.println("ResultSet closed.");
                } catch (SQLException e) {
                    System.err.println("Error closing ResultSet: " + e.getMessage());
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                    System.out.println("Statement closed.");
                } catch (SQLException e) {
                    System.err.println("Error closing Statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Connection to Northwind database closed.");
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}
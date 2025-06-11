package com.pluralsight;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductService {


//     query to select all products and displays ID, Name, Unit Price, and Units in Stock.
//     @param connection The active database connection.

    public void listAllProducts(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            System.out.println("\n--- Listing all products with ID, Name, Unit Price, and Units in Stock ---");

            statement = connection.createStatement();
            String sqlQuery = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";
            resultSet = statement.executeQuery(sqlQuery);

            System.out.printf("%-5s %-40s %-15s %-15s%n", "ID", "Product Name", "Unit Price", "Units in Stock");
            System.out.printf("%-5s %-40s %-15s %-15s%n", "----", "----------------------------------------", "-------------", "---------------");

            while (resultSet.next()) {
                int productId = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                double unitPrice = resultSet.getDouble("UnitPrice");
                int unitsInStock = resultSet.getInt("UnitsInStock");

                System.out.printf("%-5d %-40s %-15.2f %-15d%n", productId, productName, unitPrice, unitsInStock);
            }

        } catch (SQLException e) {
            System.err.println("Error listing products: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close ResultSet and Statement
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources in ProductService: " + e.getMessage());
            }
        }
    }
}

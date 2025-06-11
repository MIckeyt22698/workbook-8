package com.pluralsight;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerService {


//     Executes query select all customers and display contact name, company name, city, country, and phone.
//     ordered by country.
//     @param connection The active database connection.

    public void listAllCustomers(Connection connection) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            System.out.println("\n--- Listing all customers with Contact Name, Company, City, Country, and Phone ---");

            statement = connection.createStatement();
            String sqlQuery = "SELECT ContactName, CompanyName, City, Country, Phone FROM Customers ORDER BY Country";
            resultSet = statement.executeQuery(sqlQuery);

            System.out.printf("%-25s %-35s %-20s %-15s %-20s%n",
                    "Contact Name", "Company Name", "City", "Country", "Phone");
            System.out.printf("%-25s %-35s %-20s %-15s %-20s%n",
                    "-------------------------", "-----------------------------------", "--------------------", "---------------", "--------------------");

            while (resultSet.next()) {
                String contactName = resultSet.getString("ContactName");
                String companyName = resultSet.getString("CompanyName");
                String city = resultSet.getString("City");
                String country = resultSet.getString("Country");
                String phone = resultSet.getString("Phone");

                System.out.printf("%-25s %-35s %-20s %-15s %-20s%n",
                        contactName, companyName, city, country, phone);
            }

        } catch (SQLException e) {
            System.err.println("Error listing customers: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close ResultSet and Statement
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources in CustomerService: " + e.getMessage());
            }
        }
    }
}
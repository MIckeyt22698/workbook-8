package com.pluralsight;//package com.pluralsight;

import java.sql.Connection;
import java.util.Scanner;

public class NorthwindApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        boolean running = true;

        // Create instances of service classes
        ProductService productService = new ProductService();
        CustomerService customerService = new CustomerService();

        try {
            // Get database connection
            connection = DatabaseManager.getConnection();

            if (connection != null) {
                while (running) {
                    displayMenu();
                    System.out.print("Enter your choice: ");
                    int choice = -1;

                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next(); // Consume the invalid input
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            productService.listAllProducts(connection);
                            break;
                        case 2:
                            customerService.listAllCustomers(connection);
                            break;
                        case 0:
                            running = false;
                            System.out.println("Exiting Northwind Traders application. Goodbye!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                    System.out.println(); // Add blank line for readability
                }

            } else {
                System.out.println("Application could not connect to the database. Exiting.");
            }

        } finally {
            //  main connection and scanner are closed
            DatabaseManager.closeConnection(connection);
            if (scanner != null) {
                scanner.close();
                System.out.println("Scanner closed.");
            }
        }
    }


// Display main menu options

    private static void displayMenu() {
        System.out.println("--- Northwind Traders Menu ---");
        System.out.println("1. List all Products");
        System.out.println("2. List all Customers");
        System.out.println("0. Exit");
        System.out.println("----------------------------");
    }
}

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.ResultSet;
//import java.util.Scanner;


//public class NorthwindTraders {
//
//    private static final String url = "jdbc:mysql://localhost:3306/northwind";
//    private static final String user = "root";   // <--- REPLACE WITH YOUR MySQL USERNAME
//    private static final String password = "yearup"; // <--- REPLACE WITH YOUR MySQL PASSWORD
//
//    public static void main(String[] args) {
//        Scanner keyStroke = new Scanner(System.in);
//        Connection connection = null;
//        boolean running = true;
//
//        try {
//            System.out.println("Attempting to connect to the Northwind database...");
//            connection = DriverManager.getConnection(url, user, password);
//
//            if (connection != null) {
//                System.out.println("Successfully connected to the Northwind database!");
//
//                while (running) {
//                    displayMenu();
//                    System.out.print("Enter your choice: ");
//                    int choice = -1;
//
//                    if (keyStroke.hasNextInt()) {
//                        choice = keyStroke.nextInt();
//                    } else {
//                        System.out.println("Invalid input. Please enter a number.");
//                        keyStroke.next(); // Consume the invalid input
//                        continue;
//                    }
//
//                    switch (choice) {
//                        case 1:
//                            listAllProducts(connection);
//                            break;
//                        case 2: // New option for customers
//                            listAllCustomers(connection);
//                            break;
//                        case 0:
//                            running = false;
//                            System.out.println("Exiting Northwind Traders application. Goodbye!");
//                            break;
//                        default:
//                            System.out.println("Invalid choice. Please try again.");
//                    }
//                    System.out.println(); // blank line for readability
//                }
//
//            } else {
//                System.out.println("Failed to establish a connection to the database.");
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Database connection error: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                    System.out.println("Connection to Northwind database closed.");
//                } catch (SQLException e) {
//                    System.err.println("Error closing connection: " + e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//            if (keyStroke != null) {
//                keyStroke.close();
//                System.out.println("Scanner closed.");
//            }
//        }
//    }
//
//
//// Display the main menu options
//
//    private static void displayMenu() {
//        System.out.println("--- Northwind Traders Menu ---");
//        System.out.println("1. List all Products");
//        System.out.println("2. List all Customers"); // New menu item
//        System.out.println("0. Exit");
//        System.out.println("----------------------------");
//    }
//
//    /**
//     * Executes a query to select all products and displays their ID, Name, Unit Price, and Units in Stock.
//     *
//     * @param connection The active database connection.
//     */
//    private static void listAllProducts(Connection connection) {
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            System.out.println("\n--- Listing all products with ID, Name, Unit Price, and Units in Stock ---");
//
//            statement = connection.createStatement();
//            String sqlQuery = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";
//            resultSet = statement.executeQuery(sqlQuery);
//
//            System.out.printf("%-5s %-40s %-15s %-15s%n", "ID", "Product Name", "Unit Price", "Units in Stock");
//            System.out.printf("%-5s %-40s %-15s %-15s%n", "----", "----------------------------------------", "-------------", "---------------");
//
//            while (resultSet.next()) {
//                int productId = resultSet.getInt("ProductID");
//                String productName = resultSet.getString("ProductName");
//                double unitPrice = resultSet.getDouble("UnitPrice");
//                int unitsInStock = resultSet.getInt("UnitsInStock");
//
//                System.out.printf("%-5d %-40s %-15.2f %-15d%n", productId, productName, unitPrice, unitsInStock);
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Error listing products: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//                    System.err.println("Error closing ResultSet: " + e.getMessage());
//                }
//            }
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//                    System.err.println("Error closing Statement: " + e.getMessage());
//                }
//            }
//        }
//    }
//
//
////  Executes a query to select all customers and displays their contact name, company name, city, country, and phone.
////  Results are ordered by country.
//    private static void listAllCustomers(Connection connection) {
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        try {
//            System.out.println("\n--- Listing all customers with Contact Name, Company, City, Country, and Phone ---");
//
//            statement = connection.createStatement();
//            // SQL query to select customer details and order by Country
//            String sqlQuery = "SELECT ContactName, CompanyName, City, Country, Phone FROM Customers ORDER BY Country";
//            resultSet = statement.executeQuery(sqlQuery);
//
//            // Print header
//            System.out.printf("%-25s %-35s %-20s %-15s %-20s%n",
//                    "Contact Name", "Company Name", "City", "Country", "Phone");
//            System.out.printf("%-25s %-35s %-20s %-15s %-20s%n",
//                    "-------------------------", "-----------------------------------", "--------------------", "---------------", "--------------------");
//
//
//            // Process ResultSet
//            while (resultSet.next()) {
//                String contactName = resultSet.getString("ContactName");
//                String companyName = resultSet.getString("CompanyName");
//                String city = resultSet.getString("City");
//                String country = resultSet.getString("Country");
//                String phone = resultSet.getString("Phone");
//
//                System.out.printf("%-25s %-35s %-20s %-15s %-20s%n",
//                        contactName, companyName, city, country, phone);
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Error listing customers: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//                    System.err.println("Error closing ResultSet: " + e.getMessage());
//                }
//            }
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//                    System.err.println("Error closing Statement: " + e.getMessage());
//                }
//            }
//        }
//    }
//}
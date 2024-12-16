package com.manage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static Connection connection;

    static {
        try {
            // Database credentials
            String url = "jdbc:mysql://localhost:3306/userData?useSSL=false";
            String user = "root";
            String password = "";

            // Register MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL database successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("MySQL connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}



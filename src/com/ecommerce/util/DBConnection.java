package com.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USER = "root"; // or
    private static final String PASSWORD = "Root123";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database!");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
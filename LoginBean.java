/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nikit
 */
public class LoginBean {

    public boolean validate(String username, String password) throws SQLException {
        String url = "jdbc:sqlite:C:/DB_Collection/Users.db"; // Keep the URL correct
        String driverClassName = "org.sqlite.JDBC"; // This is the correct driver class
        Connection conn = null;

        try {
            // Ensure driver is loaded
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            // If a matching user is found, return true
            return rs.next();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw e;
                }
            }
        }
    }
}


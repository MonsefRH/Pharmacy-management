package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUserModel {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Pharmacy";
    private static final String DATABASE_USER = "root";  // Replace with your MySQL username
    private static final String DATABASE_PASSWORD = "Nadoua2004";  // Replace with your MySQL password

    // This method will save the user information into the database
    public boolean saveUserToDatabase(String role, String fullName, String dob, String phone, String email, String username, String password, String address) {
        boolean isSaved = false;

        // SQL query to insert data into the user table
        String query = "INSERT INTO user (Username, Password, FullName, DOB, Phone, Email, Address, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, fullName);
            statement.setString(4, dob);
            statement.setString(5, phone);
            statement.setString(6, email);
            statement.setString(7, address);
            statement.setString(8, role);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                isSaved = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally, print the error message
            System.out.println("Error saving user: " + e.getMessage());
        }

        return isSaved;
    }
}

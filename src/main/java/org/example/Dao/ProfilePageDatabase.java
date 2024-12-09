package org.example;

import java.sql.*;

public class ProfilePageDatabase {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Pharmacy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Yasminchahraoui2003";

    private Connection connection;

    // Constructor to initialize the database connection
    public ProfilePageDatabase() {
        try {
            // Establish the connection
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
    }

    // Fetch the user's profile data from the database using their email
    public User getUserProfile(String email) {
        String query = "SELECT * FROM user WHERE Email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the email parameter
            stmt.setString(1, email);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // If a record is found, map it to a User object
            if (rs.next()) {
                String fullName = rs.getString("FullName");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                // Assuming email is always found for the logged-in user
                return new User(fullName, phone, email, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if user is not found
    }

    // Update the user's profile data in the database
    public boolean updateUserProfile(String name, String mobile, String email, String address) {
        // Construct the SQL update query
        String updateQuery = "UPDATE user SET FullName = ?, Phone = ?, Address = ? WHERE Email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            // Set the parameters in the prepared statement
            stmt.setString(1, name);
            stmt.setString(2, mobile);
            stmt.setString(3, address);
            stmt.setString(4, email);  // Assuming Email is the primary key for identification

            // Execute the update query
            int rowsAffected = stmt.executeUpdate();

            // If the update is successful, rowsAffected will be > 0
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // Return false if an error occurs
        }
    }

    // Inner User class to hold profile information (for simplicity)
    public static class User {
        private String name;
        private String mobile;
        private String email;
        private String address;

        public User(String name, String mobile, String email, String address) {
            this.name = name;
            this.mobile = mobile;
            this.email = email;
            this.address = address;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public String getMobile() {
            return mobile;
        }

        public String getEmail() {
            return email;
        }

        public String getAddress() {
            return address;
        }
    }
}

package org.example.Dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ViewUserDatabase {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Pharmacy";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Yasminchahraoui2003";

    
    public static class User {
        private int id;
        private String username, password, fullName, dob, phone, email, address, role;

        public User(int id, String username, String password, String fullName, String dob,
                    String phone, String email, String address, String role) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.fullName = fullName;
            this.dob = dob;
            this.phone = phone;
            this.email = email;
            this.address = address;
            this.role = role;
        }

        // Getters
        public int getId() { return id; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getFullName() { return fullName; }
        public String getDob() { return dob; }
        public String getPhone() { return phone; }
        public String getEmail() { return email; }
        public String getAddress() { return address; }
        public String getRole() { return role; }
    }

    // Fetch users from the database
    public ObservableList<User> getUsersFromDatabase() {
        ObservableList<User> users = FXCollections.observableArrayList();
        String query = "SELECT id, username, password, FullName, dob, phone, email, address, role FROM user";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                // Populate user objects and add to the ObservableList
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("FullName"),
                        resultSet.getString("dob"),
                        resultSet.getString("phone"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Method to delete a user from the database
    public void deleteUserFromDatabase(int userId) {
        String query = "DELETE FROM user WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

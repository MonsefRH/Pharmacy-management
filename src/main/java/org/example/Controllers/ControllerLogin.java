package org.example.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.Dao.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControllerLogin {

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome!");
    }
    @FXML
    protected boolean verifyLogin(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                return checkPassword(password, storedHash); // Compares input password with stored hash
            }
        } catch (Exception e) {
            System.err.println("Login verification failed: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @FXML
    // Dummy password check function (for illustration purposes)
    private boolean checkPassword(String inputPassword, String storedHash) {
        // Replace with proper password hashing (e.g., BCrypt)
        return inputPassword.equals(storedHash);
    }
}

package org.example.Controllers;


import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.AdminDashBoard;
import org.example.Dao.DatabaseConnection;
import org.example.PharmacyDashBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerLogin {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField PasswordField;


    @FXML
    private void Login() {
        String username = usernameField.getText();
        String password = PasswordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champs vides !! ");
            alert.setHeaderText(null);
            alert.setContentText("svp remplir tous les champs");
            alert.showAndWait();
            return;
        }
        List<String> userinfos =verifyLogin(username);

        if (userinfos == null || userinfos.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Loging incorrect !");
            alert.setHeaderText(null);
            alert.setContentText("Les informations sont incorrectes , Ressayer !");
            alert.showAndWait();
            return;
        }
        String Enterdpassword=userinfos.get(1);
        String Role = userinfos.get(2);
        Stage primaryStage = (Stage) usernameField.getScene().getWindow(); // Get current stage

        if (checkPassword(password,Enterdpassword)) {
            if (Role.contains("Admin")) {
                // Load the Admin Dashboard
                AdminDashBoard adminDashBoard = new AdminDashBoard();
                adminDashBoard.start(primaryStage); // Use the same stage to launch Admin Dashboard
            } else if (Role.contains("Pharmacist")) {
                // Load the Pharmacy Dashboard
                PharmacyDashBoard pharmacyView = new PharmacyDashBoard();
                pharmacyView.start(primaryStage);
            }

        }
    }

    @FXML
    protected List<String> verifyLogin(String username) {
        String query = "SELECT * FROM user WHERE Username = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                List<String> userinfos = new ArrayList<>();
                userinfos.add(rs.getString("FullName"));
                userinfos.add(rs.getString("Password"));
                userinfos.add(rs.getString("Role"));
                return userinfos;
            }
        } catch (SQLException e) {
            System.err.println("Login verification failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @FXML
    // Dummy password check function (for illustration purposes)
    private boolean checkPassword(String inputPassword, String storedHash) {
        // Replace with proper password hashing (e.g., BCrypt)
        return inputPassword.equals(storedHash);
    }
}

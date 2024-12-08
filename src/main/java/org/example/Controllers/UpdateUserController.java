package org.example.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.AdminDashBoard;
import org.example.Dao.UserDao;
import org.example.Models.User;
import org.example.PharmacyDashBoard;

import java.sql.Date;

public class UpdateUserController {


    private Stage stage;
    private Scene scene;

    @FXML
    private TextField usernameField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private DatePicker dateOfBirthPicker;
    @FXML
    private Button searchButton;
    @FXML
    private Button updateInfosButton;
    @FXML
    private Button ReturnButton;

    private final UserDao userDao = new UserDao();


    @FXML
    void initialize() {
        roleComboBox.getItems().addAll("Admin", "Pharmacist");
    }

    @FXML
    void SearchUserByUsername() {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Username field is empty.");
            return;
        }

        User user = userDao.getUserByUsername(username);
        if (user == null) {
            showAlert(Alert.AlertType.ERROR, "Not Found", "No user found with username: " + username);
            return;
        }

        // Populate fields with user data
        fullNameField.setText(user.getFullName());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhone());
        addressField.setText(user.getAddress());
        roleComboBox.setValue(user.getRole());
        dateOfBirthPicker.setValue(user.getDOB().toLocalDate());
    }

    @FXML
    void UpdateUser() {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Username field is empty.");
            return;
        }

        User updatedUser = new User(
                0, // ID is not needed for updates
                username,
                fullNameField.getText().trim(),
                Date.valueOf(dateOfBirthPicker.getValue()),
                emailField.getText().trim(),
                phoneField.getText().trim(),
                addressField.getText().trim(),
                roleComboBox.getValue()
        );

        boolean isUpdated = userDao.updateUser(updatedUser);
        if (isUpdated) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Profile updated successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update profile. Try again.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void goToDashboard() {
        // Assuming MainApp holds a reference to the primary stage and scenes
        Stage stage = (Stage) ReturnButton.getScene().getWindow();

        // Set the scene to the dashboard
        Scene dashboardScene = AdminDashBoard.getDashboardScene(); // Ensure this method returns the Scene for PharmacyDashBoard
        stage.setScene(dashboardScene);
    }



}

package org.example.Controllers;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import org.example.Dao.ViewUserDatabase;

public class ViewUserController {

    private final ViewUserDatabase database;

    public ViewUserController(ViewUserDatabase database) {
        this.database = database;
    }

    public ViewUserDatabase getDatabase() {
        return database;
    }

    // Handle the deletion of a user
    public void deleteUser(int userId, ObservableList<ViewUserDatabase.User> users) {
        try {
            // Delete the user from the database
            database.deleteUserFromDatabase(userId);

            // Remove the user from the ObservableList (UI)
            users.removeIf(user -> user.getId() == userId);

            // Show success alert
            showSuccessAlert();
        } catch (Exception e) {
            
            showErrorAlert(e.getMessage());
        }
    }

    // Show the confirmation dialog before deletion
    public void showConfirmationDialog(ViewUserDatabase.User user, ObservableList<ViewUserDatabase.User> users, TableView<ViewUserDatabase.User> tableView) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete User");
        alert.setHeaderText("Are you sure you want to delete this user?");
        alert.setContentText("User: " + user.getFullName());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                deleteUser(user.getId(), users);
            }
        });
    }

    // Show success alert after deletion
    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("User Deleted");
        alert.setContentText("The user has been successfully deleted.");
        alert.showAndWait();
    }

    // Show error alert if something goes wrong
    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Failed to Delete User");
        alert.setContentText("Error: " + errorMessage);
        alert.showAndWait();
    }
}

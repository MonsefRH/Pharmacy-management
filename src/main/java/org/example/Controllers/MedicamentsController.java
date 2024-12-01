package org.example.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.AdminDashBoard;
import org.example.Dao.MedicamentsDao;
import org.example.Models.Medicament;
import org.example.View.MedicamentsView;


import java.sql.SQLException;
import java.util.List;

public class MedicamentsController {
    private MedicamentsView view;
    private MedicamentsDao dao;

    public MedicamentsController(MedicamentsView view, MedicamentsDao dao) {
        this.view = view;
        this.dao = dao;

        // Setup actions
        setupTableView();
        setupRedButton();
    }

    private void setupTableView() {
        try {
            List<Medicament> medicines = dao.getAllMedicines();
            view.getTableView().getItems().setAll(medicines);

            view.getTableView().setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    Medicament selectedItem = view.getTableView().getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        showDeleteConfirmation(selectedItem);
                    }
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupRedButton() {
        view.getRedButton().setOnAction(event -> {
            try {
                // Create an instance of AdminDashboard
                AdminDashBoard adminDashboard = new AdminDashBoard();

                // Get the current stage
                Stage currentStage = (Stage) view.getRedButton().getScene().getWindow();

                // Call the start method of AdminDashboard to switch scenes
                adminDashboard.start(currentStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void showDeleteConfirmation(Medicament medicament) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Would you like to delete this medicament?");
        alert.initOwner(view.getTableView().getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                try {
                    dao.deleteMedicine(medicament.getId());
                    view.getTableView().getItems().remove(medicament);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

package org.example.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.AdminDashBoard;
import org.example.Dao.MedicineDao;
import org.example.Models.Medicament;
import org.example.PharmacyDashBoard;
import org.example.View.MedicamentsView;


import java.sql.SQLException;
import java.util.List;

public class MedicamentsController {
    private MedicamentsView view;
    private MedicineDao dao;

    public MedicamentsController(MedicamentsView view, MedicineDao dao) {
        this.view = view;
        this.dao = dao;

        // Setup actions
        setupTableView();
        setupRedButton();
        setupSearch();
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
                PharmacyDashBoard pharmacyDashBoard = new PharmacyDashBoard();

                Stage currentStage = (Stage) view.getRedButton().getScene().getWindow();

                pharmacyDashBoard.start(currentStage);
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

    private void setupSearch() {
        view.getSearchField().textProperty().addListener((observable, oldValue, newValue) -> {
            String query = newValue.trim().toLowerCase();

            if (query.isEmpty()) {
                try {
                    List<Medicament> allMedicines = dao.getAllMedicines();
                    view.getTableView().getItems().setAll(allMedicines);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    List<Medicament> filteredMedicines = dao.searchMedicines(query);
                    view.getTableView().getItems().setAll(filteredMedicines);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

package org.example.Controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.example.*;
import org.example.View.PharmacyDashboardView;

public class PharmacyDashboardController {

    private final PharmacyDashboardView view;

    public PharmacyDashboardController(PharmacyDashboardView view) {
        this.view = view;
        initialize();
    }

    private void initialize() {
        view.getAddMedicineButton().setOnAction(event -> {
            System.out.println("Add Medicine clicked!");
            AddMedicinePage addMedicinePage = new AddMedicinePage();
            try {
                Stage stage = (Stage) view.getAddMedicineButton().getScene().getWindow();
                addMedicinePage.start(stage); // Launch Add Medicine Page on the same stage
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        view.getSellMedicineButton().setOnAction(event -> {
//            System.out.println("Sell Medicine clicked!");
//            SellMedicinePage sellMedicinePage = new SellMedicinePage();
//            try {
//                Stage stage = (Stage) view.getSellMedicineButton().getScene().getWindow();
//                sellMedicinePage.start(stage);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        view.getViewMedicineButton().setOnAction(event -> {
            System.out.println("View Medicine clicked!");
            ViewMedicinePage viewMedicinePage = new ViewMedicinePage();
            try {
                Stage stage = (Stage) view.getViewMedicineButton().getScene().getWindow();
                viewMedicinePage.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        view.getViewBillButton().setOnAction(event -> {
            System.out.println("View Bill clicked!");
            ViewBillPage viewBillPage = new ViewBillPage();
            try {
                Stage stage = (Stage) view.getViewBillButton().getScene().getWindow();
                viewBillPage.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        view.getUpdateMedicineButton().setOnAction(event -> {
//            System.out.println("Update Medicine clicked!");
//            UpdateMedicinePage updateMedicinePage = new UpdateMedicinePage();
//            try {
//                Stage stage = (Stage) view.getUpdateMedicineButton().getScene().getWindow();
//                updateMedicinePage.start(stage);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        view.getProfileButton().setOnAction(event -> {
            System.out.println("Profile clicked!");
            ProfilePage profilePage = new ProfilePage();
            try {
                Stage stage = (Stage) view.getProfileButton().getScene().getWindow();
                profilePage.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        view.getLogoutButton().setOnAction(event -> {
            System.out.println("Logout clicked!");
            // Implement logout logic, e.g., navigate to login screen
            LoginPage profilePage = new LoginPage();
            try {
                Stage stage = (Stage) view.getLogoutButton().getScene().getWindow();
                profilePage.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        view.getExitButton().setOnAction(event -> {
            Alert exitConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
            exitConfirmation.setTitle("Exit Confirmation");
            exitConfirmation.setHeaderText("Are you sure you want to exit?");
            exitConfirmation.setContentText("All unsaved data will be lost.");
            exitConfirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    System.exit(0);
                }
            });
        });
    }
}

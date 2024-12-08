package org.example.Controllers;



import javafx.stage.Stage;
import org.example.AdminDashBoard;
import org.example.Dao.MedicineDao;
import org.example.Models.Medicine;
import org.example.PharmacyDashBoard;
import org.example.View.AjoutDeMedicamentView;

public class AjoutDeMedicamentController {

    private final AjoutDeMedicamentView view;

    public AjoutDeMedicamentController(AjoutDeMedicamentView view) {
        this.view = view;
        setupHandlers();
    }

    private void setupHandlers() {
        view.getSaveButton().setOnAction(actionEvent -> {
            String id = view.getIDField().getText().trim();
            String name = view.getNameField().getText().trim();
            String company = view.getCompanyField().getText().trim();
            String quantityText = view.getQuantityField().getText().trim();
            String priceText = view.getPriceField().getText().trim();

            if (id.isEmpty() || name.isEmpty() || company.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
                view.getMessageLabel().setText("Please fill in all fields.");
                return;
            }

            try {
                int quantity = Integer.parseInt(quantityText);
                double price = Double.parseDouble(priceText);

                MedicineDao db = new MedicineDao();
                Medicine medicine = new Medicine(id, name, company, quantity, price);
                db.insertMedicine(medicine);

                view.getIDField().clear();
                view.getNameField().clear();
                view.getCompanyField().clear();
                view.getQuantityField().clear();
                view.getPriceField().clear();

                view.getMessageLabel().setText("");

            } catch (NumberFormatException e) {
                view.getMessageLabel().setText("Invalid input. Please check quantity and price.");
            }
        });

        view.getExitButton().setOnAction(actionEvent -> {
            try {
                // Create an instance of AdminDashboard
                PharmacyDashBoard pharmacyDashBoard = new PharmacyDashBoard();

                // Get the current stage
                Stage currentStage = (Stage) view.getExitButton().getScene().getWindow();

                // Call the start method of AdminDashboard to switch scenes
                pharmacyDashBoard.start(currentStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}

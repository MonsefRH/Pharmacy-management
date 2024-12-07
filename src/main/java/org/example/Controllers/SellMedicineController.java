package com.example.pharmacy_sidi_abbad.controller;

import com.example.pharmacy_sidi_abbad.MedicineItem;
import com.example.pharmacy_sidi_abbad.model.SellModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class SellMedicineController {

    private SellModel sellModel;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> medicineList;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField companyField;

    @FXML
    private TextField unitsField;

    @FXML
    private TextField totalField;

    @FXML
    private TableView<MedicineItem> cartTable;

    @FXML
    private TableColumn<MedicineItem, Integer> colMedicineID;

    @FXML
    private TableColumn<MedicineItem, String> colName;

    @FXML
    private TableColumn<MedicineItem, Integer> colPricePerUnit;

    @FXML
    private TableColumn<MedicineItem, String> colPharmName;

    @FXML
    private TableColumn<MedicineItem, Integer> colTotal;
    @FXML
    private Button addToCartButton;

    @FXML
    private Label rsLabel;

    @FXML
    private Button purchaseButton;

    private ObservableList<MedicineItem> cartItems;
    private int totalRS = 0;

    public SellMedicineController() {
        this.sellModel = new SellModel();
        this.cartItems = FXCollections.observableArrayList();
    }

    public void initialize() {
        colMedicineID.setCellValueFactory(cellData -> cellData.getValue().medicineIDProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colPricePerUnit.setCellValueFactory(cellData -> cellData.getValue().pricePerUnitProperty().asObject());
        colPharmName.setCellValueFactory(cellData -> cellData.getValue().PharmaNameProperty());
        colTotal.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty().asObject());

        cartTable.setItems(cartItems);

        unitsField.textProperty().addListener((observable, oldValue, newValue) -> calculateTotalPrice());

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                medicineList.getItems().clear();
            } else {
                searchMedicines(newValue.trim());
            }
        });

        medicineList.setOnMouseClicked(event -> {
            String selectedMedicineName = medicineList.getSelectionModel().getSelectedItem();
            if (selectedMedicineName != null) {
                loadMedicineDetails(selectedMedicineName);
            }
        });

        addToCartButton.setOnAction(event -> addToCart());
        purchaseButton.setOnAction(event -> purchaseAndPrint());
    }

    private void searchMedicines(String keyword) {
        Platform.runLater(() -> {
            List<String> medicines = sellModel.searchMedicines(keyword);

            ObservableList<String> medicineNames = FXCollections.observableArrayList(medicines);

            medicineList.setItems(medicineNames);
        });
    }

    private void loadMedicineDetails(String medicineName) {
        Platform.runLater(() -> {
            MedicineItem medicine = sellModel.getMedicineDetails(medicineName);

            if (medicine != null) {
                idField.setText(String.valueOf(medicine.getMedicineID()));
                nameField.setText(medicine.getName());
                priceField.setText(String.valueOf(medicine.getPricePerUnit()));

            }
        });
    }

    private void calculateTotalPrice() {
        try {
            int pricePerUnit = Integer.parseInt(priceField.getText().trim());
            int noOfUnits = Integer.parseInt(unitsField.getText().trim());
            int totalPrice = pricePerUnit * noOfUnits;

            totalField.setText(String.valueOf(totalPrice));
        } catch (NumberFormatException e) {
            totalField.setText("");
        }
    }

    private void addToCart() {
        try {
            int medicineID = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            int pricePerUnit = Integer.parseInt(priceField.getText().trim());
            String company = companyField.getText().trim();
            int noOfUnits = Integer.parseInt(unitsField.getText().trim());
            int total = Integer.parseInt(totalField.getText().trim());

            // Vérifier si la quantité est disponible en stock
            int availableStock = sellModel.getMedicineStock(medicineID);
            if (noOfUnits > availableStock) {
                showAlert("Stock Insufficient", "Only " + availableStock + " units available in stock.");
                return;
            }

            // Ajouter l'article au panier
            MedicineItem item = new MedicineItem(medicineID, name, company, pricePerUnit, noOfUnits, total);
            cartItems.add(item);

            totalRS += total;
            rsLabel.setText(String.valueOf(totalRS));

            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please ensure all fields are filled with valid data.");
        }
    }

    private void purchaseAndPrint() {
        for (MedicineItem item : cartItems) {
            sellModel.updateMedicineStock(item.getMedicineID(), item.getNoOfUnits());
        }

        showAlert("Purchase Successful", "Stock updated and purchase recorded.");
        cartItems.clear();
        cartTable.refresh();
        totalRS = 0;
        rsLabel.setText("0");
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        priceField.clear();
        companyField.clear();
        unitsField.clear();
        totalField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

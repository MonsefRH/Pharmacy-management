package org.example.View;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lombok.Data;
import org.example.Models.Medicament;

@Data
public class MedicamentsView {
    private Stage stage;
    private TableView<Medicament> tableView;
    private Button redButton;
    private TextField searchField;
    private Button searchButton;

    public MedicamentsView(Stage stage) {
        this.stage = stage;
        initialize();
    }

    private void initialize() {

        tableView = new TableView<>();
        redButton = new Button("X");
        redButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px 10px;");

        searchField = new TextField();
        searchField.setPromptText("Search by name or company...");
        searchButton = new Button("Search");

        TableColumn<Medicament, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));

        TableColumn<Medicament, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));

        TableColumn<Medicament, String> companyColumn = new TableColumn<>("Company");
        companyColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCompany()));

        TableColumn<Medicament, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getQuantity()));

        TableColumn<Medicament, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrice()));

        tableView.getColumns().addAll(idColumn, nameColumn, companyColumn, quantityColumn, priceColumn);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        idColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        nameColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        companyColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        quantityColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
        priceColumn.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));

        HBox searchBar = new HBox(10, searchField, searchButton);
        searchBar.setAlignment(Pos.CENTER_LEFT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox topBar = new HBox(searchBar, spacer, redButton);
        topBar.setStyle("-fx-padding: 5px;");

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setCenter(tableView);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Medicines");
    }

}

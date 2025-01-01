package org.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;

public class AddUserView {

    private Stage primaryStage;

    public AddUserView(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        // Configure the main window (Stage)
        primaryStage.setTitle("Pharmacy Management - Add User");

        // Root container: GridPane to allow resizing
        GridPane root = new GridPane();
        root.setHgap(20);
        root.setVgap(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20px;");

        // Title Section
        HBox titleBox = new HBox();
        titleBox.setStyle("-fx-background-color: #4682B4;"); // Steel blue
        titleBox.setSpacing(10);
        titleBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Add New User");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");
        titleBox.getChildren().add(titleLabel);

        // Add the title box to the root at row 0, column span 2
        root.add(titleBox, 0, 0, 2, 1);

        // Form Section: Row-based in GridPane
        addFormRow(root, "User Role:", new ComboBox<String>() {{
            getItems().addAll("Pharmacist", "Admin");
        }}, 1);

        addFormRow(root, "Name:", new TextField(), 2);
        addFormRow(root, "DOB (Date Of Birth):", new DatePicker(), 3);
        addFormRow(root, "Mobile Number:", new TextField(), 4);
        addFormRow(root, "Email:", new TextField(), 5);
        addFormRow(root, "Username:", new TextField(), 6);
        addFormRow(root, "Password:", new PasswordField(), 7);
        addFormRow(root, "Address:", new TextField(), 8);

        // Button Section (Save and Retour)
        HBox buttonBox = new HBox(20);
        buttonBox.setStyle("-fx-padding: 20px;");
        buttonBox.setAlignment(Pos.CENTER);

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-background-color: #228B22; -fx-text-fill: black;");

        // "Retour" button
        Button backButton = new Button("Retour");
        backButton.setStyle("-fx-background-color: #DC143C; -fx-text-fill: black;");
        backButton.setOnAction(e -> {
            // Action for "Retour" button
            primaryStage.close();
        });

        buttonBox.getChildren().addAll(saveButton, backButton);

        // Add buttonBox to root at a new row
        root.add(buttonBox, 0, 9, 2, 1);

        // Apply GridPane Column and Row Constraints for responsive layout
        for (int i = 0; i < 2; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(50); // Distribute equally
            root.getColumnConstraints().add(col);
        }

        for (int i = 0; i < 9; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS); // Allow rows to expand and fill vertical space
            root.getRowConstraints().add(row);
        }

        // Bind width of the form controls to the window size
        for (int i = 1; i <= 8; i++) {
            Control control = (Control) root.getChildren().get(i);
            control.prefWidthProperty().bind(root.widthProperty()); // Set width to 100% of the window width
            GridPane.setHgrow(control, Priority.ALWAYS); // Allow controls to grow horizontally
        }

        // Bind the height of the titleBox and buttonBox to take 10% of the window height
        titleBox.prefHeightProperty().bind(root.heightProperty().multiply(0.1));
        buttonBox.prefHeightProperty().bind(root.heightProperty().multiply(0.1));

        // Create the scene and set the root
        Scene scene = new Scene(root, 700, 500);

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(500);

        // Show the window
        primaryStage.show();
    }

    private void addFormRow(GridPane gridPane, String label, Control control, int rowIndex) {
        Label formLabel = new Label(label);
        formLabel.setStyle("-fx-font-size: 14px;");
        gridPane.add(formLabel, 0, rowIndex);

        gridPane.add(control, 1, rowIndex);

        // Make the control stretchable horizontally
        GridPane.setHgrow(control, Priority.ALWAYS);
    }
}


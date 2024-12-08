package org.example.View;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public class PharmacyDashboardView extends Parent {

    private Button addMedicineButton;
    private Button sellMedicineButton;
    private Button viewMedicineButton;
    private Button viewBillButton;
    private Button updateMedicineButton;
    private Button profileButton;
    private Button logoutButton;
    private Button exitButton;


    public PharmacyDashboardView(Stage primaryStage) {
        // Title
        Text title = new Text("Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setStyle("-fx-fill: white;");

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(title);

        Pane line = new Pane();
        line.setMinHeight(1);
        line.setStyle("-fx-background-color: white;");

        // Create buttons with icons
        addMedicineButton = createButton("Add Medicine", "/images/addMedicine.png");
        sellMedicineButton = createButton("Sell Medicine", "/images/sellMedicine.png");
        viewMedicineButton = createButton("View Medicine", "/images/viewUser.png");
        viewBillButton = createButton("View Bill", "/images/viewBill.png");
        updateMedicineButton = createButton("Update Medicine", "/images/updateUser.png");
        profileButton = createButton("Profile", "/images/profile.png");
        logoutButton = createButton("Logout", "/images/logout.png");
        exitButton = createButton("Exit", "/images/exit.png");


        // GridPane for main buttons
        GridPane gridPane = new GridPane();
        gridPane.setHgap(80);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(50));
        gridPane.setAlignment(Pos.CENTER);

        // Add buttons to the grid
        gridPane.add(addMedicineButton, 0, 0);
        gridPane.add(sellMedicineButton, 1, 0);
        gridPane.add(viewMedicineButton, 0, 1);
        gridPane.add(viewBillButton, 1, 1);
        gridPane.add(updateMedicineButton, 0, 2);
        gridPane.add(profileButton, 1, 2);

        // Spacer for logout/exit buttons
        Pane spacer = new Pane();
        spacer.setMinHeight(100);
        gridPane.add(spacer, 0, 3);

        gridPane.add(logoutButton, 0, 4);
        gridPane.add(exitButton, 1, 4);

        // StackPane for background
        StackPane gridContainer = new StackPane();
        gridContainer.setStyle("-fx-background-color: dimgrey;");
        gridContainer.getChildren().add(gridPane);

        VBox topBox = new VBox(5);
        topBox.getChildren().addAll(titleBox, line);
        topBox.setAlignment(Pos.TOP_CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(gridContainer);
        root.setStyle("-fx-background-color: dimgrey;");

        // Scene and Stage
        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setTitle("Pharmacy Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text, String iconPath) {
        try {
            // Load icon dynamically using getClass().getResource
            URL resource = getClass().getResource(iconPath);
            if (resource == null) {
                throw new NullPointerException("Resource not found: " + iconPath);
            }

            ImageView icon = new ImageView(new Image(resource.toString()));
            icon.setFitWidth(40);
            icon.setFitHeight(40);

            Button button = new Button(text, icon);
            button.setStyle(getButtonStyle());
            button.setPrefWidth(1000);
            button.setPrefHeight(150);
            button.setGraphicTextGap(20);
            button.setMinWidth(250);   // Min width to prevent shrinking
            button.setMinHeight(100);  // Min height to prevent shrinking

            button.setPadding(new Insets(10));
            button.setGraphicTextGap(15);

            return button;
        } catch (Exception e) {
            System.err.println("Error loading icon: " + e.getMessage());
            // Fallback to text-only button
            Button button = new Button(text);
            button.setStyle(getButtonStyle());
            return button;
        }
    }

    private String getButtonStyle() {
        return "-fx-background-color: white; "
                + "-fx-text-fill: black; "
                + "-fx-font-size: 25px; "
                + "-fx-font-weight: bold; "
                + "-fx-border-color: #dcdcdc; "
                + "-fx-border-width: 2px; "
                + "-fx-background-radius: 25; "
                + "-fx-border-radius: 25;";
    }

    // Getters for buttons
    public Button getAddMedicineButton() {
        return addMedicineButton;
    }

    public Button getSellMedicineButton() {
        return sellMedicineButton;
    }

    public Button getViewMedicineButton() {
        return viewMedicineButton;
    }

    public Button getViewBillButton() {
        return viewBillButton;
    }

    public Button getUpdateMedicineButton() {
        return updateMedicineButton;
    }

    public Button getProfileButton() {
        return profileButton;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
}

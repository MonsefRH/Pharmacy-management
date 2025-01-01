package org.example.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.PharmacyDashBoard;

public class ProfilePageView {

    private Stage stage;
    private TextField nameField;
    private TextField mobileField;
    private TextField emailField;
    private TextField addressField;
    private Button updateButton;
    private Button closeButton;  // Red cross button
    private Label messageLabel;

    public ProfilePageView(Stage stage) {
        this.stage = stage;
        initializeUI();
    }

    private void initializeUI() {
        // Create the input fields
        nameField = new TextField();
        mobileField = new TextField();
        emailField = new TextField();
        addressField = new TextField();

        // Create a button to update profile changes
        updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        updateButton.setMinWidth(100);  // Make the button wider
        updateButton.setMinHeight(30);  // Make the button taller
        updateButton.setStyle(updateButton.getStyle() + " -fx-font-size: 16px;");

        // Create a red cross button (close button)
        closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-padding: 5px 10px;");

        // Close button functionality
        closeButton.setOnAction(event -> {
            try {
                PharmacyDashBoard pharmacyDashBoard = new PharmacyDashBoard();

                Stage currentStage = (Stage) closeButton.getScene().getWindow();

                pharmacyDashBoard.start(currentStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Create a label to display messages (success or error)
        messageLabel = new Label();

        // Create the profile image (profile.png from resources)
        Image profileImage = new Image(getClass().getResourceAsStream("/images/profile.png"));
        ImageView imageView = new ImageView(profileImage);
        imageView.setFitWidth(200);  // Increased the width of the image
        imageView.setFitHeight(200); // Increased the height of the image

        // Layout for the profile photo and input fields
        HBox topLayout = new HBox(200, imageView, createInputForm());  // Reduced spacing to push the image a bit to the right
        topLayout.setAlignment(Pos.CENTER);

        // Layout for the close button (top-right corner)
        HBox closeButtonLayout = new HBox(closeButton);
        closeButtonLayout.setAlignment(Pos.TOP_RIGHT);

        // Layout for the message label and the button
        VBox mainLayout = new VBox(30, closeButtonLayout, topLayout, messageLabel, updateButton);
        mainLayout.setAlignment(Pos.BASELINE_CENTER);
        mainLayout.setStyle("-fx-padding: 20;"); // Add some padding

        // Scene and stage setup
        Scene scene = new Scene(mainLayout, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Profile Page");
        stage.show();
    }


    private VBox createInputForm() {
        // Create the input form layout
        VBox inputForm = new VBox(23);
        inputForm.setAlignment(Pos.CENTER_LEFT);  // Align to the left

        // Add input fields and labels
        inputForm.getChildren().addAll(
                new Label("Name:"), nameField,
                new Label("Mobile:"), mobileField,
                new Label("Email:"), emailField,
                new Label("Address:"), addressField
        );

        // Set widths for the input fields to align them properly
        nameField.setPrefWidth(250);  // Increased width of input fields
        mobileField.setPrefWidth(250);
        emailField.setPrefWidth(250);
        addressField.setPrefWidth(250);

        return inputForm;
    }

    // Getters for the input fields and the update button
    public TextField getNameField() {
        return nameField;
    }

    public TextField getMobileField() {
        return mobileField;
    }

    public TextField getEmailField() {
        return emailField;
    }

    public TextField getAddressField() {
        return addressField;
    }

    public Button getUpdateButton() {
        return updateButton;
    }

    public Button getCloseButton() {
        return closeButton;  // Getter for the close button
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(String message) {
        messageLabel.setText(message);
    }
}

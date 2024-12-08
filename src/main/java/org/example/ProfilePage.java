package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;

public class ProfilePage extends Application {

    // Variables pour stocker les données du profil
    private String currentName = "Admin1";
    private String currentMobile = "0000111122";
    private String currentEmail = "johndoe@example.com";
    private String currentAddress = "Maroc";

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Pharmacy";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Yasminchahraoui2003";

    @Override
    public void start(Stage primaryStage) {
        // Titre de la page
        Label titleLabel = new Label("Profile");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        // Bouton de fermeture en haut à droite
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
        closeButton.setOnAction(e -> primaryStage.close());

        // Conteneur pour le bouton de fermeture
        HBox closeButtonContainer = new HBox(closeButton);
        closeButtonContainer.setAlignment(Pos.TOP_RIGHT);
        VBox.setMargin(closeButtonContainer, new Insets(0, 20, 0, 0)); // Marge à droite

        // Image de profil
        ImageView profileImage = new ImageView(new Image(getClass().getResource("/profile.png").toExternalForm()));
        profileImage.fitWidthProperty().bind(primaryStage.widthProperty().divide(6)); // Taille ajustable
        profileImage.fitHeightProperty().bind(primaryStage.heightProperty().divide(6));

        Label profileNameLabel = new Label("admin");
        profileNameLabel.setStyle("-fx-font-size: 14px; -fx-text-align: center;");

        VBox profileContainer = new VBox(profileImage, profileNameLabel);
        profileContainer.setAlignment(Pos.CENTER);
        profileContainer.setSpacing(10);

        // Ajouter une marge pour décaler l'image vers la gauche
        HBox.setMargin(profileContainer, new Insets(0, 110, 0, 20));

        // Champs de texte
        TextField nameField = new TextField(currentName);
        TextField mobileField = new TextField(currentMobile);
        TextField emailField = new TextField(currentEmail);
        TextField addressField = new TextField(currentAddress);

        // Liens pour rendre les champs adaptatifs
        nameField.prefWidthProperty().bind(primaryStage.widthProperty().divide(3));
        nameField.setStyle("-fx-pref-height: 40;");
        mobileField.prefWidthProperty().bind(primaryStage.widthProperty().divide(3));
        mobileField.setStyle("-fx-pref-height: 40;");
        emailField.prefWidthProperty().bind(primaryStage.widthProperty().divide(3));
        emailField.setStyle("-fx-pref-height: 40;");
        addressField.prefWidthProperty().bind(primaryStage.widthProperty().divide(3));
        addressField.setStyle("-fx-pref-height: 40;");

        nameField.setPromptText("Name");
        mobileField.setPromptText("Mobile Number");
        emailField.setPromptText("Email");
        addressField.setPromptText("Address");

        // Étiquettes des champs
        Label nameLabel = new Label("Name");
        Label mobileLabel = new Label("Mobile Number");
        Label emailLabel = new Label("Email");
        Label addressLabel = new Label("Address");

        VBox formContainer = new VBox(
                nameLabel, nameField,
                mobileLabel, mobileField,
                emailLabel, emailField,
                addressLabel, addressField
        );
        formContainer.setSpacing(10);

        // Bouton de mise à jour
        Button updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        updateButton.setOnAction(e -> {
            // Mettre à jour les données du profil
            currentName = nameField.getText();
            currentMobile = mobileField.getText();
            currentEmail = emailField.getText();
            currentAddress = addressField.getText();

            // Appeler la méthode pour mettre à jour dans la base de données
            updateProfileInDatabase(currentName, currentMobile, currentEmail, currentAddress);

            // Afficher une alerte de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Profile Updated");
            alert.setHeaderText(null);
            alert.setContentText("Profile updated successfully!");
            alert.showAndWait();
        });

        HBox updateButtonContainer = new HBox(updateButton);
        updateButtonContainer.setAlignment(Pos.CENTER);

        VBox rightContainer = new VBox(formContainer, updateButtonContainer);
        rightContainer.setSpacing(20);
        rightContainer.setAlignment(Pos.CENTER);

        // Conteneur principal
        HBox mainContainer = new HBox(profileContainer, rightContainer);
        mainContainer.setSpacing(50);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPrefWidth(Region.USE_COMPUTED_SIZE);
        mainContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Ajouter des marges autour du conteneur principal
        mainContainer.setPadding(new Insets(20));

        VBox root = new VBox(closeButtonContainer, titleLabel, mainContainer);
        root.setSpacing(20);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-padding: 20;");
        VBox.setVgrow(mainContainer, Priority.ALWAYS);

        // Création de la scène
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Profile Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthode pour mettre à jour le profil dans la base de données
    private void updateProfileInDatabase(String name, String mobile, String email, String address) {
        String updateQuery = "UPDATE user SET Username = ?, Phone = ?, Email = ?, Address = ? WHERE Email = ?";

        // Debugging: print out the email being used for the update
        System.out.println("Updating profile for email: " + email);

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            // Set parameters in the prepared statement
            stmt.setString(1, name);
            stmt.setString(2, mobile);
            stmt.setString(3, email);
            stmt.setString(4, address);
            stmt.setString(5, email); // Make sure the WHERE clause uses the correct email

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // If the update was successful
                System.out.println("Profile updated successfully.");
            } else {
                // If no rows were affected (no matching email in the database)
                System.out.println("No profile found with the provided email.");
            }

        } catch (SQLException e) {
            // If there's a SQL exception, print the stack trace for debugging
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}


package com.example.pharmacy_sidi_abbad;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SellMedicine extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        Parent root = FXMLLoader.load(getClass().getResource("/layout/sell_medicine.fxml"));

        // Charger la feuille de style CSS
        Scene scene = new Scene(root, 800, 500);
        scene.getStylesheets().add(getClass().getResource("/Sell.css").toExternalForm());

        // Configurer la scène et la fenêtre
        primaryStage.setTitle("Sell Medicine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

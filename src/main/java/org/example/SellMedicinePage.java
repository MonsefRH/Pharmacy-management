package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SellMedicinePage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("sell_medicine.fxml"));
        AnchorPane root = loader.load();

        // Charger la feuille de style CSS
        Scene scene = new Scene(root, 800, 500);

        // Configurer la scène et la fenêtre
        primaryStage.setTitle("Sell Medicine");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

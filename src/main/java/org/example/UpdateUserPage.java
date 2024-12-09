package org.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UpdateUserPage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Load the FXML file and ensure the controller is loaded properly
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("UpdateUserView.fxml"));
            AnchorPane root = loader.load();  // Load the FXML and get the root node
            // Set up the scene and stage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Pharamacy Management System");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package org.example.pproject.Models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlLocation = getClass().getResource("/org/example/pproject/views/Login.fxml");
        if (fxmlLocation == null) {
            throw new IllegalStateException("FXML file not found.");
        }

        Parent root = FXMLLoader.load(fxmlLocation);
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

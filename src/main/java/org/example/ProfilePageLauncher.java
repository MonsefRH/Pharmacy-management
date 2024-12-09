package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class ProfilePageLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        // User's email can be dynamically passed based on the logged-in user
        String userEmail = "houda@example.com"; // Example email

        // Initialize the ProfilePageView
        ProfilePageView view = new ProfilePageView(primaryStage);

        // Initialize the ProfilePageController with the view and userEmail
        ProfilePageController controller = new ProfilePageController(view, userEmail);
    }

    public static void main(String[] args) {
        launch(args);  // This launches the JavaFX application
    }
}

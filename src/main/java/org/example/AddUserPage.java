package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.View.AddUserView;


public class AddUserPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set up the view (UI layout)
        AddUserView userView = new AddUserView(primaryStage);

        // Show the view
        userView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Controllers.PharmacyDashboardController;
import org.example.View.PharmacyDashboardView;

public class PharmacyDashBoard extends Application {

    @Override
    public void start(Stage primaryStage) {
        PharmacyDashboardView view=new PharmacyDashboardView(primaryStage);
        new PharmacyDashboardController(view);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

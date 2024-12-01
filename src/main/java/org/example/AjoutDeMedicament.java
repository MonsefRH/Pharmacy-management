package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.Controllers.AjoutDeMedicamentController;
import org.example.View.AjoutDeMedicamentView;

public class AjoutDeMedicament extends Application {

    @Override
    public void start(Stage primaryStage) {
        AjoutDeMedicamentView view = new AjoutDeMedicamentView();
        new AjoutDeMedicamentController(view);
        view.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

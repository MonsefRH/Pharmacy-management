package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class ViewUserLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        ViewUserDatabase database = new ViewUserDatabase();
        ViewUserController controller = new ViewUserController(database);
        ViewUserView view = new ViewUserView(controller);

        // Démarrer la vue
        view.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

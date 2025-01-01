package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.Controllers.ViewUserController;
import org.example.Dao.ViewUserDatabase;
import org.example.View.ViewUserView;

public class ViewUserLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        ViewUserDatabase database = new ViewUserDatabase();
        ViewUserController controller = new ViewUserController(database);
        ViewUserView view = new ViewUserView(controller);

      
        view.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

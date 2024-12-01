package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.Controllers.MedicamentsController;
import org.example.Dao.MedicamentsDao;
import org.example.View.MedicamentsView;

public class MedicamentsApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        MedicamentsDao dao = new MedicamentsDao();
        MedicamentsView view = new MedicamentsView(primaryStage);

        // Pass the DAO and View to the Controller
        MedicamentsController controller = new MedicamentsController(view, dao);

        // Show the view's stage
        view.getStage().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

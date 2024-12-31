package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.Controllers.MedicamentsController;
import org.example.Dao.MedicineDao;
import org.example.View.MedicamentsView;

public class ViewMedicinePage extends Application {

    @Override
    public void start(Stage primaryStage) {
        MedicineDao dao = new MedicineDao();
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

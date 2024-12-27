package org.example;

import java.io.IOException;
import org.example.Controllers.UpdateMedicineController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UpdateMedicinePage extends Application{
    @Override
    public void start (Stage primarystage)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("UpdateMedicineView.fxml"));
        Parent root = loader.load();
        UpdateMedicineController con=loader.getController();
        con.setmainwindow(primarystage);
        Scene sc=new Scene(root,1400,1400);
        primarystage.setTitle("PHARMACY Management System");
        primarystage.setScene(sc);
        primarystage.show();
    }
    public static void main( String[] args ){
    launch(args);
    }
}

package org.example.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class AjoutDeMedicamentView {

    private TextField ID = new TextField();
    private TextField Name = new TextField();
    private TextField Company = new TextField();
    private TextField Quantity = new TextField();
    private TextField Price = new TextField();
    private Button saveButton = new Button("\uD83D\uDCBE Save");
    private Button logoutButton = new Button("Logout");
    private Button exitButton = new Button("Return");
    private Label messageLabel = new Label();

    public void start(Stage primaryStage) {
        Text title = new Text("Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setStyle("-fx-fill: white");

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(title);
        titleBox.setPadding(new Insets(20));

        Pane line = new Pane();
        line.setMinHeight(1);
        line.setStyle("-fx-background-color: white;");

        Label l0 = new Label("Add Medicine");
        l0.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        l0.setStyle("-fx-fill: black");

        HBox l0Box = new HBox();
        l0Box.setAlignment(Pos.CENTER);
        l0Box.getChildren().add(l0);

        Pane line2 = new Pane();
        line2.setMinHeight(1);
        line2.setStyle("-fx-background-color: dimgrey;");


        Label l1 = new Label("Medicine ID ");
        l1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label l2 = new Label("Name");
        l2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label l3 = new Label("Company Name");
        l3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label l4 = new Label("Quantity");
        l4.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        Label l5 = new Label("Price Per Unit");
        l5.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        GridPane grid = new GridPane();
        grid.setHgap(120);
        grid.setVgap(20);
        grid.setPadding(new Insets(40));
        grid.setAlignment(Pos.CENTER);

        messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        messageLabel.setTextFill(Color.RED);

        grid.add(l0Box, 0, 0, 2, 1);
        grid.add(line2, 0, 1, 2, 1);
        grid.add(l1, 0, 2);
        grid.add(ID, 0, 3);
        grid.add(l2, 0, 4);
        grid.add(Name, 0, 5);
        grid.add(l3, 0, 6);
        grid.add(Company, 0, 7);
        grid.add(l4, 1, 2);
        grid.add(Quantity, 1, 3);
        grid.add(l5, 1, 4);
        grid.add(Price, 1, 5);
        grid.add(saveButton, 1, 6);
        grid.add(messageLabel, 1, 7);

        grid.setStyle("-fx-background-color: white;  -fx-border-radius: 10px; -fx-background-radius: 10px;");
        grid.setMinWidth(700);
        grid.setMaxWidth(700);

        StackPane gridContainer = new StackPane();
        gridContainer.setStyle("-fx-background-color: dimgrey;");
        gridContainer.getChildren().add(grid);
        gridContainer.setPadding(new Insets(100));

        VBox topBox = new VBox(5);
        topBox.getChildren().addAll(titleBox, line);

        HBox logoutBox = new HBox(logoutButton);
        logoutBox.setAlignment(Pos.CENTER);
        logoutBox.setPadding(new Insets(10));
        logoutBox.setPrefWidth(300);
        logoutBox.setStyle("-fx-background-color: white;  -fx-border-radius: 10px; -fx-background-radius: 10px;");

        HBox exitBox = new HBox(exitButton);
        exitBox.setAlignment(Pos.CENTER);
        exitBox.setPadding(new Insets(10));
        exitBox.setPrefWidth(300);
        exitBox.setStyle("-fx-background-color: white;  -fx-border-radius: 10px; -fx-background-radius: 10px; " );

        HBox buttonBox = new HBox(10, logoutBox, exitBox);
        buttonBox.setPadding(new Insets(20));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(0, 0, 1000, 0));

        logoutButton.setStyle("-fx-background-color: transparent;");
        exitButton.setStyle("-fx-background-color: transparent;");
        logoutButton.setPrefWidth(300);
        exitButton.setPrefWidth(300);


        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(gridContainer);
        root.setBottom(buttonBox);
        root.setStyle("-fx-background-color: dimgrey;");

        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setTitle("JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public TextField getIDField() {
        return ID;
    }

    public TextField getNameField() {
        return Name;
    }

    public TextField getCompanyField() {
        return Company;
    }

    public TextField getQuantityField() {
        return Quantity;
    }

    public TextField getPriceField() {
        return Price;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }
}

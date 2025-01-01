package org.example.View;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.AdminDashBoard;
import org.example.Controllers.ViewUserController;
import org.example.Dao.ViewUserDatabase;

public class ViewUserView {

    private final ViewUserController controller;

    public ViewUserView(ViewUserController controller) {
        this.controller = controller;
    }

    public void start(Stage primaryStage) {
        // Create the table
        TableView<ViewUserDatabase.User> tableView = new TableView<>();

        // Create columns
        TableColumn<ViewUserDatabase.User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ViewUserDatabase.User, String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        TableColumn<ViewUserDatabase.User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<ViewUserDatabase.User, String> dobColumn = new TableColumn<>("Date of Birth");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));

        TableColumn<ViewUserDatabase.User, String> phoneColumn = new TableColumn<>("Mobile Number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<ViewUserDatabase.User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<ViewUserDatabase.User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<ViewUserDatabase.User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<ViewUserDatabase.User, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Add columns to the table
        tableView.getColumns().addAll(idColumn, nameColumn, roleColumn, dobColumn, phoneColumn, emailColumn, usernameColumn, passwordColumn, addressColumn);

        // Load data from the database
        ObservableList<ViewUserDatabase.User> users = controller.getDatabase().getUsersFromDatabase();
        tableView.setItems(users);

        // Adjust column widths
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Detect a click on a table row
        tableView.setRowFactory(tv -> {
            TableRow<ViewUserDatabase.User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    ViewUserDatabase.User clickedUser = row.getItem();
                    controller.showConfirmationDialog(clickedUser, users, tableView); // Delegating to controller
                }
            });
            return row;
        });

        // Create the title
        Label titleLabel = new Label("View User");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        // Center the title
        VBox titleBox = new VBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        // Close button (top right corner)
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
        closeButton.setOnAction(event -> {
            try {
                AdminDashBoard adminDashBoard = new AdminDashBoard();

                Stage currentStage = (Stage) closeButton.getScene().getWindow();

                adminDashBoard.start(currentStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Align the close button
        HBox closeBox = new HBox(closeButton);
        closeBox.setAlignment(Pos.TOP_RIGHT);

        // Header container
        VBox header = new VBox(closeBox, titleBox);
        header.setSpacing(10);

        // Footer text
        Label footerLabel = new Label("Click on a row to delete the user");
        footerLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: grey;");
        footerLabel.setAlignment(Pos.CENTER);

        // Footer container
        VBox footerBox = new VBox(footerLabel);
        footerBox.setAlignment(Pos.CENTER);
        footerBox.setSpacing(10);

        // Main container for the table
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(tableView);
        root.setBottom(footerBox);

        // Create the scene
        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setTitle("View User");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

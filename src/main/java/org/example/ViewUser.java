package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Optional;

public class ViewUser extends Application {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Pharmacy"; 
    private static final String DB_USERNAME = "root"; 
    private static final String DB_PASSWORD = "Yasminchahraoui2003"; 

    @Override
    public void start(Stage primaryStage) {
        // Create the table
        TableView<User> tableView = new TableView<>();

        // Create columns
        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> nameColumn = new TableColumn<>("Full Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        TableColumn<User, String> dobColumn = new TableColumn<>("Date of Birth");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));

        TableColumn<User, String> phoneColumn = new TableColumn<>("Mobile Number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<User, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Add columns to the table
        tableView.getColumns().addAll(idColumn, nameColumn, roleColumn, dobColumn, phoneColumn, emailColumn, usernameColumn, passwordColumn, addressColumn);

        // Load data from the database
        ObservableList<User> users = getUsersFromDatabase();
        tableView.setItems(users);

        // Adjust column widths
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Detect a click on a table row
        tableView.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 1) {
                    User clickedUser = row.getItem();
                    showConfirmationDialog(clickedUser, users, tableView);
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
        closeButton.setOnAction(e -> primaryStage.close());

        // Align the close button
        HBox closeBox = new HBox(closeButton);
        closeBox.setAlignment(Pos.TOP_RIGHT);

        // Header container
        VBox header = new VBox(closeBox, titleBox);
        header.setSpacing(10);

        // Footer text
        Label footerLabel = new Label("Click on a row to delete the user");
        footerLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: darkgrey;");
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
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("View User");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to show a confirmation dialog
    private void showConfirmationDialog(User user, ObservableList<User> users, TableView<User> tableView) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete User");
        alert.setContentText("Are you sure you want to delete the user: " + user.getFullname() + "?");

        // Handle the user's response
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            users.remove(user); // Remove the user from the data
            tableView.refresh(); // Refresh the table
        }
    }

    // Load data from the database
    private ObservableList<User> getUsersFromDatabase() {
        ObservableList<User> users = FXCollections.observableArrayList();

        // Connect to the database
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT id, fullname, role, dob, phone, email, username, password, address FROM user";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Map each row to a User object
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fullname = resultSet.getString("fullname");
                String role = resultSet.getString("role");
                String dob = resultSet.getString("dob");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");

                users.add(new User(id, fullname, role, dob, phone, email, username, password, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Failed to fetch data from the database.");
        }

        return users;
    }

    // Helper method to show an alert in case of an error
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // User class
    public static class User {
        private int id;
        private String fullname;
        private String role;
        private String dob;
        private String phone;
        private String email;
        private String username;
        private String password;
        private String address;

        public User(int id, String fullname, String role, String dob, String phone, String email, String username, String password, String address) {
            this.id = id;
            this.fullname = fullname;
            this.role = role;
            this.dob = dob;
            this.phone = phone;
            this.email = email;
            this.username = username;
            this.password = password;
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public String getFullname() {
            return fullname;
        }

        public String getRole() {
            return role;
        }

        public String getDob() {
            return dob;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getAddress() {
            return address;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

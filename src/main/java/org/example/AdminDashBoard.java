package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;

public class AdminDashBoard extends Application {
    static private Scene dashboardScene;
    @Override
    public void start(Stage primaryStage) {
        // Title
        Text title = new Text("Dashboard");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        title.setStyle("-fx-fill: white;");

        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(title);

        Pane line = new Pane();
        line.setMinHeight(1);
        line.setStyle("-fx-background-color: white;");

        // Create buttons with icons
        Button addUserButton = createButton("Add User", "/addUser.png");
        Button profileButton = createButton("Profile", "/profile.png");
        Button viewUserButton = createButton("View User", "/viewUser.png");
        Button updateUserButton = createButton("Update User", "/updateUser.png");
        Button logoutButton = createButton("Logout", "/logout.png");
        Button exitButton = createButton("Exit", "/exit.png");

        // GridPane for main buttons
        GridPane gridPane = new GridPane();
        gridPane.setHgap(80); // Add more horizontal space between columns
        gridPane.setVgap(20); // Vertical space between rows
        gridPane.setPadding(new Insets(50));
        gridPane.setAlignment(Pos.CENTER);

        // Add buttons to the grid
        gridPane.add(addUserButton, 0, 0);
        gridPane.add(profileButton, 1, 0);
        gridPane.add(viewUserButton, 0, 1);
        gridPane.add(updateUserButton, 1, 1);
        gridPane.add(logoutButton, 0, 2);
        gridPane.add(exitButton, 1, 2);


        // Add gridBox to a StackPane to control its background
        StackPane gridContainer = new StackPane();
        gridContainer.setStyle("-fx-background-color: dimgrey;");
        gridContainer.getChildren().add(gridPane);

        VBox topBox = new VBox(5);
        topBox.getChildren().addAll(titleBox, line);
        topBox.setAlignment(Pos.TOP_CENTER);

        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(gridContainer);
        root.setStyle("-fx-background-color: dimgrey;");

        // Scene and Stage
        Scene scene = new Scene(root, 1000, 1000);
        dashboardScene=scene;
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create buttons with text and icons
    private Button createButton(String text, String iconPath) {
        try {
            // Load icon dynamically using getClass().getResource
            URL resource = getClass().getResource("/images/" + iconPath);
            if (resource == null) {
                throw new NullPointerException("Resource not found: " + iconPath);
            }

            ImageView icon = new ImageView(new Image(resource.toString()));
            icon.setFitWidth(40);
            icon.setFitHeight(40);

            Button button = new Button(text, icon);
            button.setStyle("-fx-background-color: white; "
                    + "-fx-text-fill: black; "
                    + "-fx-font-size: 25px; "  // Increase font size
                    + "-fx-font-weight: bold; "
                    + "-fx-border-color: #dcdcdc; "
                    + "-fx-border-width: 2px; "
                    + "-fx-background-radius: 25; "  // Rounded edges
                    + "-fx-border-radius: 25;");  // Rounded border
            button.setPrefWidth(1000);
            button.setPrefHeight(150);
            button.setGraphicTextGap(20);
            button.setMinWidth(250);   // Min width to prevent shrinking
            button.setMinHeight(100);  // Min height to prevent shrinking

            // Add padding to the button to ensure content is not cramped
            button.setPadding(new Insets(10));

            // Set a gap between icon and text
            button.setGraphicTextGap(15);

            if ("Update User".equals(text)) {
                button.setOnAction(event -> {
                    UpdateUserPage UpdateUserPage = new UpdateUserPage();
                    try {
                        Stage stage = (Stage) button.getScene().getWindow(); // Get current stage
                        UpdateUserPage.start(stage); // Launch the View Bill page on the same stage
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            if ("Exit".equals(text)) {
                button.setOnAction(event -> {
                    // Create a confirmation dialog
                    Alert exitConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    exitConfirmation.setTitle("Exit Confirmation");
                    exitConfirmation.setHeaderText("Are you sure you want to exit?");
                    exitConfirmation.setContentText("All unsaved data will be lost.");

                    // Show the dialog and wait for a response
                    exitConfirmation.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            // If user clicked "OK", exit the application
                            System.exit(0);
                        } else {
                            // If user clicked "Cancel", do nothing
                            event.consume(); // Prevents the window from closing
                        }
                    });
                });
            }

            return button;
        } catch (Exception e) {
            System.err.println("Error loading icon: " + e.getMessage());
            return new Button(text); // Fallback to text-only button
        }
    }
    static public Scene getDashboardScene() {
        return dashboardScene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
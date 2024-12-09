package org.example;

public class ProfilePageController {

    private ProfilePageView view;
    private String userEmail;
    private ProfilePageDatabase database;

    public ProfilePageController(ProfilePageView view, String userEmail) {
        this.view = view;
        this.userEmail = userEmail;
        this.database = new ProfilePageDatabase();

        // Initialize the profile
        initializeProfile();

        // Handle save button click
        view.getUpdateButton().setOnAction(e -> saveProfile());
    }

    private void initializeProfile() {
        // Fetch user profile data from the database
        ProfilePageDatabase.User user = database.getUserProfile(userEmail);

        // If the user is found, display the data
        if (user != null) {
            view.getNameField().setText(user.getName());
            view.getMobileField().setText(user.getMobile());
            view.getEmailField().setText(user.getEmail());
            view.getAddressField().setText(user.getAddress());
        } else {
            view.setMessageLabel("User not found.");
        }
    }

    private void saveProfile() {
        // Get the updated data from the input fields
        String name = view.getNameField().getText();
        String mobile = view.getMobileField().getText();
        String email = view.getEmailField().getText();
        String address = view.getAddressField().getText();

        // Update the profile in the database
        database.updateUserProfile(name, mobile, email, address);

        // Display success message
        view.setMessageLabel("Profile updated successfully.");
    }
}
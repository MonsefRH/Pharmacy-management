package org.example.Controllers;


import org.example.Dao.AddUserModel;

import javax.swing.*;

public class AddUserController {

    private AddUserModel addUserModel;

    public AddUserController() {
        addUserModel = new AddUserModel();
    }

    // This method will handle the saving of user info
    public void saveUser(String role, String fullName, String dob, String phone, String email, String username, String password, String address) {
        boolean isSaved = addUserModel.saveUserToDatabase(role, fullName, dob, phone, email, username, password, address);

        if (isSaved) {
            JOptionPane.showMessageDialog(null, "User saved successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Error saving user. Please try again.");
        }
    }
}

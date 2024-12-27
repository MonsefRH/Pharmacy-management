package org.example.View;

import org.example.Controllers.AddUserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddUserView extends JFrame {
    private JComboBox<String> roleComboBox;
    private JTextField nameField, mobileField, emailField, usernameField, addressField;
    private JSpinner dobSpinner;
    private JPasswordField passwordField;
    private JButton saveButton, resetButton;

    public AddUserView() {
        setTitle("Pharmacy Management - Add User");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout(10, 10));

        // Title Section
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(70, 130, 180));

        JLabel titleLabel = new JLabel("Add New User");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Verdana", Font.BOLD, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(220, 20, 60));
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        closeButton.addActionListener(e -> dispose());
        titlePanel.add(closeButton, BorderLayout.EAST);
        add(titlePanel, BorderLayout.NORTH);

        // Form Section
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        formPanel.setBackground(Color.WHITE);

        formPanel.add(createLabel("User Role:"));
        roleComboBox = new JComboBox<>(new String[]{"Pharmacist", "Admin"});
        formPanel.add(roleComboBox);

        formPanel.add(createLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(createLabel("DOB:"));
        dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd");
        dobSpinner.setEditor(dateEditor);
        formPanel.add(dobSpinner);

        formPanel.add(createLabel("Mobile Number:"));
        mobileField = new JTextField();
        // Restrict mobileField to accept only numbers
        mobileField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && !Character.isISOControl(c)) {
                    e.consume(); // Ignore invalid characters
                }
            }
        });
        formPanel.add(mobileField);

        formPanel.add(createLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(createLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(createLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(createLabel("Address:"));
        addressField = new JTextField();
        formPanel.add(addressField);

        add(formPanel, BorderLayout.CENTER);

        // Button Section
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        saveButton = new JButton("Save");
        resetButton = new JButton("Reset");
        saveButton.setBackground(new Color(34, 139, 34));
        saveButton.setForeground(Color.BLUE);
        resetButton.setBackground(new Color(220, 20, 60));
        resetButton.setForeground(Color.BLUE);

        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button Action Listeners
        saveButton.addActionListener(e -> {
            // Get user input from fields
            String role = getRole();
            String name = getName();

            // Get the value from dobSpinner, which is a Date object
            Date dob = (Date) dobSpinner.getValue();

            // Format the Date object to the required format (yyyy-MM-dd)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDob = dateFormat.format(dob); // Convert to string in yyyy-MM-dd format

            String phone = getMobile();
            String email = getEmail();
            String username = getUsername();
            String password = getPassword();
            String address = getAddress();

            // Pass the formatted user data to the controller to save it in the database
            new AddUserController().saveUser(role, name, formattedDob, phone, email, username, password, address);
        });

        resetButton.addActionListener(e -> resetFields());
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Verdana", Font.PLAIN, 14));
        return label;
    }

    // Getters for user input
    public String getRole() {
        return (String) roleComboBox.getSelectedItem();
    }

    public String getName() {
        return nameField.getText();
    }

    public String getDob() {
        return dobSpinner.getValue().toString();
    }

    public String getMobile() {
        return mobileField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getAddress() {
        return addressField.getText();
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    // Method to reset form fields
    private void resetFields() {
        roleComboBox.setSelectedIndex(0);
        nameField.setText("");
        dobSpinner.setValue(new java.util.Date());
        mobileField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        addressField.setText("");
    }
}

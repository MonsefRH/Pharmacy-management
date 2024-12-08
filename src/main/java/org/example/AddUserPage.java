package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class AddUserPage extends JFrame {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Pharmacy"; // Change to your DB URL
    private static final String DB_USERNAME = "root"; // Change to your DB username
    private static final String DB_PASSWORD = "Nadoua2004"; // Change to your DB password

    public AddUserPage() {
        // Configure the main window
        setTitle("Pharmacy Management - Add User");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        getContentPane().setBackground(new Color(245, 245, 245)); // Light gray background

        // Use BorderLayout to manage the sections
        setLayout(new BorderLayout(10, 10));

        // Title Section
        JPanel titlePanel = new JPanel(new BorderLayout()); // Use BorderLayout for positioning
        titlePanel.setBackground(new Color(70, 130, 180)); // Steel blue

        JLabel titleLabel = new JLabel("Add New User");
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Add a close button (cross icon) in the top-right corner
        JButton closeButton = new JButton("X");
        closeButton.setFont(new Font("Verdana", Font.BOLD, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(220, 20, 60)); // Crimson
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add action to close the window
        closeButton.addActionListener((ActionEvent e) -> {
            dispose(); // Close the current frame
        });

        titlePanel.add(closeButton, BorderLayout.EAST); // Add close button to the right
        add(titlePanel, BorderLayout.NORTH);

        // Form Section
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10)); // 8 rows, 2 columns
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        formPanel.setBackground(Color.WHITE);

        // Add fields to the form
        formPanel.add(createLabel("User Role:"));
        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Pharmacist", "Admin"});
        formPanel.add(roleComboBox);

        formPanel.add(createLabel("Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(createLabel("DOB (Date Of Birth):"));
        JSpinner dobSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dobSpinner, "yyyy-MM-dd");
        dobSpinner.setEditor(dateEditor);
        formPanel.add(dobSpinner);

        formPanel.add(createLabel("Mobile Number:"));
        JTextField mobileField = new JTextField();
        ((AbstractDocument) mobileField.getDocument()).setDocumentFilter(new NumericDocumentFilter()); // Apply filter
        formPanel.add(mobileField);

        formPanel.add(createLabel("Email:"));
        JTextField emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(createLabel("Username:"));
        JTextField usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(createLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(createLabel("Address:"));
        JTextField addressField = new JTextField();
        formPanel.add(addressField);

        add(formPanel, BorderLayout.CENTER);

        // Button Section
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245)); // Match the main background
        JButton saveButton = new JButton("Save");
        JButton resetButton = new JButton("Reset");
        saveButton.setBackground(new Color(34, 139, 34)); // Green
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Verdana", Font.BOLD, 14));
        resetButton.setBackground(new Color(220, 20, 60)); // Crimson
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(new Font("Verdana", Font.BOLD, 14));

        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add ActionListeners for the buttons
        saveButton.addActionListener((ActionEvent e) -> {
            String role = (String) roleComboBox.getSelectedItem();
            String name = nameField.getText();
            Date dob = (Date) dobSpinner.getValue();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dobFormatted = dateFormat.format(dob);
            String mobile = mobileField.getText();
            String email = emailField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String address = addressField.getText();

            // Validate mobile number
            if (!mobile.matches("[0-9]{10}")) {
                JOptionPane.showMessageDialog(this, "Le numéro de téléphone doit être un numéro à 10 chiffres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Insert into database
            insertUserIntoDatabase(role, name, dobFormatted, mobile, email, username, password, address);
        });

        resetButton.addActionListener((ActionEvent e) -> {
            roleComboBox.setSelectedIndex(0);
            nameField.setText("");
            dobSpinner.setValue(new Date()); // Reset to current date
            mobileField.setText("");
            emailField.setText("");
            usernameField.setText("");
            passwordField.setText("");
            addressField.setText("");
        });
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Verdana", Font.PLAIN, 14));
        return label;
    }

    private void insertUserIntoDatabase(String role, String name, String dob, String mobile, String email, String username, String password, String address) {
        // Check if the name is too long (more than 20 characters)
        if (name.length() > 20) {
            JOptionPane.showMessageDialog(this, "Le nom ne peut pas dépasser 20 caractères.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return; // Stop insertion if name is too long
        }

        // Check if email or username already exists in the database
        if (isEmailOrUsernameExists(email, username)) {
            JOptionPane.showMessageDialog(this, "L'email ou le nom d'utilisateur existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return; // Stop insertion if email or username already exists
        }

        String query = "INSERT INTO user (id, Username, Password, FullName, DOB, Phone, Email, Address, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Generate a new ID if needed
            int newId = generateNewId(); // You can create a method to get the next available ID or auto-increment

            // Set parameters
            preparedStatement.setInt(1, newId);  // ID
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, name); // FullName
            preparedStatement.setString(5, dob);  // DOB
            preparedStatement.setString(6, mobile); // Phone
            preparedStatement.setString(7, email);  // Email
            preparedStatement.setString(8, address); // Address
            preparedStatement.setString(9, role); // Role

            // Execute the query
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Utilisateur ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de l'utilisateur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isEmailOrUsernameExists(String email, String username) {
        String checkQuery = "SELECT COUNT(*) FROM user WHERE Email = ? OR Username = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {

            // Set parameters to check for existing email or username
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Return true if there's an existing email or username
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Return false if no email or username is found
    }

    private int generateNewId() {
        // Implement logic to generate new ID (auto-increment logic or fetch the next available ID)
        return 0;
    }

    // DocumentFilter to accept only numbers for the mobile number field
    class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string.matches("[0-9]*")) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text.matches("[0-9]*")) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddUserPage().setVisible(true));
    }
}

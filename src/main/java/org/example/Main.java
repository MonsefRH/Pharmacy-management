package org.example;

import org.example.View.AddUserView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddUserView().setVisible(true));
    }
}
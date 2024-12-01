package org.example.Dao;

import org.example.Models.Medicament;

import java.sql.*;
import java.util.*;

public class MedicamentsDao {


    // Method to fetch medicines from the database
    public List<Medicament> getAllMedicines() throws SQLException {
        List<Medicament> medicines = new ArrayList<>();
        String query = "SELECT * FROM medicines"; // Example query

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Medicament med = new Medicament(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("company"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                );
                medicines.add(med);
            }
        }
        return medicines;
    }

    // Method to delete a medicine from the database
    public void deleteMedicine(String id) throws SQLException {
        String query = "DELETE FROM medicines WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }
}

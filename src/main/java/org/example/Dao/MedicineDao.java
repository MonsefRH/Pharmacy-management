package org.example.Dao;


import org.example.Models.Medicine;

import java.sql.*;

public class MedicineDao {

    public void insertMedicine(Medicine medicine) {
        String query = "INSERT INTO medicines (id, name, company, quantity, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medicine.getId());
            stmt.setString(2, medicine.getName());
            stmt.setString(3, medicine.getCompany());
            stmt.setInt(4, medicine.getQuantity());
            stmt.setDouble(5, medicine.getPrice());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

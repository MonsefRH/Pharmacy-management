package org.example.Dao;

import org.example.Models.Facture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FactureDao {

    // Method to insert a new facture
    public void insertFacture(Facture facture) {
        String query = "INSERT INTO factures (mediid, medicine_name, price_per_unit, quantity, total_price, date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, facture.getMediid());
            stmt.setString(2, facture.getMedicineName());
            stmt.setDouble(3, facture.getPricePerUnit());
            stmt.setInt(4, facture.getQuantity());
            stmt.setDouble(5, facture.getTotalPrice());
            stmt.setTimestamp(6, Timestamp.valueOf(facture.getDate()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all factures
    public List<Facture> getAllFactures() {
        List<Facture> factures = new ArrayList<>();
        String query = "SELECT * FROM factures";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Facture facture = new Facture(
                        rs.getLong("id"),
                        rs.getLong("mediid"),
                        rs.getString("medicine_name"),
                        rs.getDouble("price_per_unit"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getTimestamp("date").toLocalDateTime()
                );
                factures.add(facture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return factures;
    }

    // Method to retrieve a facture by ID
    public Facture getFactureById(long id) {
        String query = "SELECT * FROM factures WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Facture(
                        rs.getLong("id"),
                        rs.getLong("mediid"),
                        rs.getString("medicine_name"),
                        rs.getDouble("price_per_unit"),
                        rs.getInt("quantity"),
                        rs.getDouble("total_price"),
                        rs.getTimestamp("date").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


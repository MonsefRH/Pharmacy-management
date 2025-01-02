package org.example.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellModel {
    private Connection connection;

    public SellModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy_management", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> searchMedicines(String keyword) {
        List<String> medicines = new ArrayList<>();
        String query = "SELECT name FROM medicines WHERE name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                medicines.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }

    public MedicineItem getMedicineDetails(String medicineName) {
        String query = "SELECT * FROM medicines WHERE name = ?";
        MedicineItem medicine = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medicineName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                medicine = new MedicineItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        null,
                        rs.getInt("price"),
                        rs.getInt("quantity"),
                        0
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicine;
    }

    public int getMedicineStock(int medicineID) {
        String query = "SELECT quantity FROM medicines WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, medicineID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateMedicineStock(int medicineID, int quantitySold) {
        String query = "UPDATE medicines SET quantity = quantity - ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantitySold);
            stmt.setInt(2, medicineID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recordSale(MedicineItem item) {
        String query = "INSERT INTO factures (medicine_id, medicine_name, price_per_unit, quantity, total_price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, item.getMedicineID());
            stmt.setString(2, item.getName());
            stmt.setInt(3, item.getPricePerUnit());
            stmt.setInt(4, item.getNoOfUnits());
            stmt.setInt(5, item.getTotalPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.example.pharmacy_sidi_abbad.model;

import com.example.pharmacy_sidi_abbad.MedicineItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellModel {
    private Connection connection;

    public SellModel() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pharmacy_sidi_abbad", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer les noms des médicaments pour la recherche
    public List<String> searchMedicines(String keyword) {
        List<String> medicines = new ArrayList<>();
        String query = "SELECT name FROM medicines WHERE name LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, keyword + "%"); // Recherche les noms qui commencent par le mot-clé
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                medicines.add(rs.getString("name")); // Ajoute uniquement les noms à la liste
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicines; // Retourne la liste des noms des médicaments
    }

    // Méthode pour récupérer les détails d'un médicament
    public MedicineItem getMedicineDetails(String medicineName) {
        String query = "SELECT * FROM medicines WHERE name = ?";
        MedicineItem medicine = null;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, medicineName); // Recherche exacte par nom
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Récupérer les informations du médicament
                medicine = new MedicineItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        null, // Si 'company' n'existe pas dans votre table, mettre null
                        rs.getInt("price_per_unit"),
                        rs.getInt("quantity"),
                        0 // Pas de calcul de prix total ici
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicine; // Retourne l'objet MedicineItem contenant les informations
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


}

package org.example.Dao;


import org.example.Models.Medicament;
import org.example.Models.Medicine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public ResultSet search1 (String b) throws SQLException{
        Connection connection=DatabaseConnection.getConnection();
       Statement stmt=connection.createStatement();
       ResultSet res=stmt.executeQuery("select * from medicine where id='"+b+"'");
       return res ;
   }

   public void update1(String c,String f) throws SQLException{
       Connection connection=DatabaseConnection.getConnection();
       Statement stmt=connection.createStatement();
       String query="UPDATE `medicine` SET `price`='"+c+"' WHERE id='"+f+"'";
       stmt.executeUpdate(query);
   }

   public void update2 (String a,String b)throws SQLException{
       Connection connection=DatabaseConnection.getConnection();
       Statement stmt=connection.createStatement();
       String query="UPDATE `medicine` SET `quantity`='"+a+"'where id='"+b+"'";
       stmt.executeUpdate(query);
   }


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

    public List<Medicament> searchMedicines(String query) throws SQLException {
        String sql = "SELECT * FROM medicines WHERE LOWER(name) LIKE ? OR LOWER(company) LIKE ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();

            List<Medicament> medicines = new ArrayList<>();
            while (rs.next()) {
                medicines.add(new Medicament(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("company"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                ));
            }
            return medicines;
        }
    }

}

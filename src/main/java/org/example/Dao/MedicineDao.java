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

}

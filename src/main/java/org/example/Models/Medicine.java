package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Medicine {
    private String id;
    private String name;
    private String company;
    private int quantity;
    private double price;

    // Constructor
//    public Medicine(String id, String name, String company, int quantity, double price) {
//        this.id = id;
//        this.name = name;
//        this.company = company;
//        this.quantity = quantity;
//        this.price = price;
//    }

    // Getters and setters
//    public String getId() { return id; }
//    public void setId(String id) { this.id = id; }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//
//    public String getCompany() { return company; }
//    public void setCompany(String company) { this.company = company; }
//
//    public int getQuantity() { return quantity; }
//    public void setQuantity(int quantity) { this.quantity = quantity; }
//
//    public double getPrice() { return price; }
//    public void setPrice(double price) { this.price = price; }
}

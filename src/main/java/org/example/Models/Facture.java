package org.example.Models;


import java.time.LocalDateTime;

public class Facture {

    private long id;
    private long mediid;
    private String medicineName;
    private double pricePerUnit;
    private int quantity;
    private double totalPrice;
    private LocalDateTime date;

    // Constructeur par défaut
    public Facture() {
    }

    // Constructeur complet
    public Facture(long id, long mediid, String medicineName, double pricePerUnit, int quantity, double totalPrice, LocalDateTime date) {
        this.id = id;
        this.mediid = mediid;
        this.medicineName = medicineName;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    // Constructeur sans ID (utilisé pour ajouter une nouvelle facture)
    public Facture(long mediid, String medicineName, double pricePerUnit, int quantity, double totalPrice, LocalDateTime date) {
        this.mediid = mediid;
        this.medicineName = medicineName;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    // Getters et Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMediid() {
        return mediid;
    }

    public void setMediid(long mediid) {
        this.mediid = mediid;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    // Méthode toString() (facultative pour débogage ou affichage)
    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", mediid=" + mediid +
                ", medicineName='" + medicineName + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", date=" + date +
                '}';
    }
}

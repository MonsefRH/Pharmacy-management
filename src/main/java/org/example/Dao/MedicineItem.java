package org.example.Dao;

import javafx.beans.property.*;

public class MedicineItem {
    private IntegerProperty medicineID;
    private StringProperty name;
    private StringProperty PharmaName;
    private IntegerProperty pricePerUnit;
    private IntegerProperty noOfUnits;
    private IntegerProperty totalPrice;

    // Constructeur
    public MedicineItem(int medicineID, String name, String PharmaName, int pricePerUnit, int noOfUnits, int totalPrice) {
        this.medicineID = new SimpleIntegerProperty(medicineID);
        this.name = new SimpleStringProperty(name);
        this.PharmaName = new SimpleStringProperty(PharmaName);
        this.pricePerUnit = new SimpleIntegerProperty(pricePerUnit);
        this.noOfUnits = new SimpleIntegerProperty(noOfUnits);
        this.totalPrice = new SimpleIntegerProperty(totalPrice);
    }

    // Getters pour les valeurs (primitives)
    public int getMedicineID() {
        return medicineID.get();
    }

    public String getName() {
        return name.get();
    }

    public String getPharmaName() {
        return PharmaName.get();
    }

    public int getPricePerUnit() {
        return pricePerUnit.get();
    }

    public int getNoOfUnits() {
        return noOfUnits.get();
    }

    public int getTotalPrice() {
        return totalPrice.get();
    }

    // Setters pour les valeurs
    public void setMedicineID(int medicineID) {
        this.medicineID.set(medicineID);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setPharmaName(String PharmaName) {
        this.PharmaName.set(PharmaName);
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit.set(pricePerUnit);
    }

    public void setNoOfUnits(int noOfUnits) {
        this.noOfUnits.set(noOfUnits);
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice.set(totalPrice);
    }

    // Méthodes Property pour JavaFX
    public IntegerProperty medicineIDProperty() {
        return medicineID;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty PharmaNameProperty() {
        return PharmaName;
    }

    public IntegerProperty pricePerUnitProperty() {
        return pricePerUnit;
    }

    public IntegerProperty noOfUnitsProperty() {
        return noOfUnits;
    }

    public IntegerProperty totalPriceProperty() {
        return totalPrice;
    }
}

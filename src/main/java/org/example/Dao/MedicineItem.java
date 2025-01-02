package org.example.Dao;

import javafx.beans.property.*;

public class MedicineItem {
    private IntegerProperty medicineID;
    private StringProperty name;
    private StringProperty pharmaName;
    private IntegerProperty pricePerUnit;
    private IntegerProperty noOfUnits;
    private IntegerProperty totalPrice;

    public MedicineItem(int medicineID, String name, String pharmaName, int pricePerUnit, int noOfUnits, int totalPrice) {
        this.medicineID = new SimpleIntegerProperty(medicineID);
        this.name = new SimpleStringProperty(name);
        this.pharmaName = new SimpleStringProperty(pharmaName);
        this.pricePerUnit = new SimpleIntegerProperty(pricePerUnit);
        this.noOfUnits = new SimpleIntegerProperty(noOfUnits);
        this.totalPrice = new SimpleIntegerProperty(totalPrice);
    }

    public int getMedicineID() {
        return medicineID.get();
    }

    public IntegerProperty medicineIDProperty() {
        return medicineID;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getPharmaName() {
        return pharmaName.get();
    }

    public StringProperty PharmaNameProperty() {
        return pharmaName;
    }

    public int getPricePerUnit() {
        return pricePerUnit.get();
    }

    public IntegerProperty pricePerUnitProperty() {
        return pricePerUnit;
    }

    public int getNoOfUnits() {
        return noOfUnits.get();
    }

    public IntegerProperty noOfUnitsProperty() {
        return noOfUnits;
    }

    public int getTotalPrice() {
        return totalPrice.get();
    }

    public IntegerProperty totalPriceProperty() {
        return totalPrice;
    }
}

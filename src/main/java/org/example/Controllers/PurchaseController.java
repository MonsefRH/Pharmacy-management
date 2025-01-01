package org.example.Controllers;



import javafx.collections.ObservableList;
import org.example.Dao.FactureDao;
import org.example.Models.Facture;
import org.example.Models.Medicine;

import java.time.LocalDateTime;

public class PurchaseController {

    public void saveFactures(ObservableList<Medicine> cartItems, double totalAmount) {
        FactureDao factureDao = new FactureDao();

        for (Medicine medicine : cartItems) {
            Facture facture = new Facture(
                    medicine.getId(),
                    medicine.getName(),
                    medicine.getPricePerUnit(),
                    medicine.getQuantity(),
                    medicine.getPricePerUnit() * medicine.getQuantity(),
                    LocalDateTime.now() // Current date and time
            );

            factureDao.insertFacture(facture); // Save each facture in the database
        }

        System.out.println("Factures saved successfully!");
    }
}


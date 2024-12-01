package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Medicament {
    private String id;
    private String name;
    private String company;
    private int quantity;
    private double price;
}

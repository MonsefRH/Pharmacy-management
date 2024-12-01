package org.example.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private int id;
    private String Username;
//    private String Password;
    private String FullName;
    private Date DOB;
    private String Email;
    private String Phone;
    private String Address;
    private String Role;

}

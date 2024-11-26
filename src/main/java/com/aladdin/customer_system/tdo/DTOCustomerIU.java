package com.aladdin.customer_system.tdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOCustomerIU {
    private String firstName;
    private String lastName;
    private String email;
    private double rating;
    private String password;
    private int age;

}

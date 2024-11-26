package com.aladdin.customer_system.tdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOCustomer {
    private String firstName;
    private String lastName;
    private double rating;
}

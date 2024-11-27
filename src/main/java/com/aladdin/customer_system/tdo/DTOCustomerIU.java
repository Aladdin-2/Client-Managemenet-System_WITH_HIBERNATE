package com.aladdin.customer_system.tdo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DTOCustomerIU {
    @Size(min = 3, max = 14, message = "Firstname can be minimum 3 maximum 13 letters!")
    @NotEmpty(message = "First name dose not is empty!")
    private String firstName;

    @Size(min = 4, max = 14, message = "Lastname can be minimum 3 maximum 13 letters!")
    private String lastName;

    @Email(message = "Incorrect email!")
    private String email;

    private double rating;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{4,20}$", message = "Password must contain at least one digit," +
            " one lowercase, one uppercase letter ")
    private String password;

    @Min(value = 18, message = "Must be at least 18 years old. ")
    private int age;

}

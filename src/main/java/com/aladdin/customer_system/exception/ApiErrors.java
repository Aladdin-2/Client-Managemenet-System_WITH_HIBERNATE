package com.aladdin.customer_system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrors<T> {
    private String id;
    private LocalDateTime dateTime;
    private T error;
}

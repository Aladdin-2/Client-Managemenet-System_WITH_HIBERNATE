package com.aladdin.customer_system.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;


@ControllerAdvice
public class GlobalExceptionHandler {

    private List<String> valueList(List<String> list, String newValue) {
        list.add(newValue);
        return list;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrors> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, List<String>> errorMap = new HashMap<>();

        for (ObjectError objectError : exception.getAllErrors()) {
            String errorField = ((FieldError) objectError).getField();
            if (errorMap.containsKey(errorField)) {
                errorMap.put(errorField, valueList(errorMap.get(errorField), objectError.getDefaultMessage()));
            } else {
                errorMap.put(errorField, valueList(new ArrayList<>(), objectError.getDefaultMessage()));
            }
        }
        return ResponseEntity.badRequest().body(apiErrors(errorMap));

    }


    public <T> ApiErrors<T> apiErrors(T error) {
        ApiErrors<T> apiErrors = new ApiErrors<>();
        apiErrors.setId(UUID.randomUUID().toString());
        apiErrors.setDateTime(LocalDateTime.now());
        apiErrors.setError(error);
        return apiErrors;
    }
}

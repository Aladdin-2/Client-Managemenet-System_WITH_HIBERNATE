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

    private List<String> errorsMessage(List<String> list, String newValue) {
        list.add(newValue);
        return list;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrors> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        Map<String, List<String>> errorsMap = new HashMap<>();

        for (ObjectError objectError : exception.getBindingResult().getAllErrors()) {
            if (objectError instanceof FieldError) {
                String fieldError = ((FieldError) objectError).getField();
                if (errorsMap.containsKey(fieldError)) {
                    errorsMap.put(fieldError, errorsMessage(errorsMap.get(fieldError), objectError.getDefaultMessage()));
                } else {
                    errorsMap.put(fieldError, errorsMessage(new ArrayList<>(), objectError.getDefaultMessage()));
                }
            }
        }

        return ResponseEntity.badRequest().body(errors(errorsMap));
    }

    private <T> ApiErrors<T> errors(T error) {
        ApiErrors<T> apiErrors = new ApiErrors<>();
        apiErrors.setId(UUID.randomUUID().toString());
        apiErrors.setDateTime(LocalDateTime.now());
        apiErrors.setError(error);

        return apiErrors;
    }
}

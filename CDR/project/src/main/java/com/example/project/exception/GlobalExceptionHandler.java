package com.example.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<String> handleUserRegistrationException(UserRegistrationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + ex.getMessage());
    }
}

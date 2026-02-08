package com.pm.patient_service.exception;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    HashMap<String, String> errors = new HashMap<>();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, String>> handleValidationException(MethodArgumentNotValidException ex) {    
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<HashMap<String, String>> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
        errors.put("message", "Email Already Exist");
        return ResponseEntity.badRequest().body(errors);
    }

}

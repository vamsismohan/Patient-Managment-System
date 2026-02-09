package com.pm.patient_service.exception;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HashMap<String, String>> handleValidationException(MethodArgumentNotValidException ex) {  
        HashMap<String, String> errors = new HashMap<>();  
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<HashMap<String, String>> handleEmailAlreadyExistException(EmailAlreadyExistException ex) {
        log.warn("Patient Email Address Already Exist {}", ex.getMessage());
        HashMap<String, String> errors = new HashMap<>();
        errors.put("message", "Email Already Exist");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<HashMap<String, String>> handleTransactionException(TransactionSystemException ex) {
        HashMap<String, String> errors = new HashMap<>();
        
        if (ex.getRootCause() instanceof ConstraintViolationException) {
            ConstraintViolationException cve = (ConstraintViolationException) ex.getRootCause();
            cve.getConstraintViolations().forEach(violation -> 
                errors.put(violation.getPropertyPath().toString(), violation.getMessage())
            );
        } else {
            errors.put("error", ex.getMessage());
        }
        
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<HashMap<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        HashMap<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> 
            errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

}

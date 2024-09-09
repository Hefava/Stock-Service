package com.bootcamp.stock.infrastructure.exceptionhandler;

import com.bootcamp.stock.domain.exception.categoryCantBeRepeatedException;
import com.bootcamp.stock.domain.exception.invalidCategoryCountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ArticuloControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(categoryCantBeRepeatedException.class)
    public ResponseEntity<Map<String, String>> handleCategoryCantBeRepeatedException(
            categoryCantBeRepeatedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(invalidCategoryCountException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCategoryCountException(
            invalidCategoryCountException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ArticuloExceptionResponse.INVALID_CATEGORY_COUNT.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}

package com.bootcamp.stock.categoria.infrastructure.exceptionhandler;

import com.bootcamp.stock.categoria.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp.stock.categoria.domain.exception.InvalidCategoryDescriptionLengthException;
import com.bootcamp.stock.categoria.domain.exception.InvalidCategoryNameLengthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
            CategoryAlreadyExistsException categoryAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(InvalidCategoryNameLengthException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCategoryNameLengthException(
            InvalidCategoryNameLengthException invalidCategoryNameLengthException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_CATEGORY_NAME_LENGTH.getMessage()));
    }

    @ExceptionHandler(InvalidCategoryDescriptionLengthException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCategoryDescriptionLengthException(
            InvalidCategoryDescriptionLengthException invalidCategoryDescriptionLengthException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_CATEGORY_DESCRIPTION_LENGTH.getMessage()));
    }
}

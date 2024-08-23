package com.bootcampPragma.Stock.Marca.infrastructure.exceptionhandler;

import com.bootcampPragma.Stock.Marca.domain.exception.InvalidMarcaDescriptionLengthException;
import com.bootcampPragma.Stock.Marca.domain.exception.InvalidMarcaNameLengthException;
import com.bootcampPragma.Stock.Marca.domain.exception.MarcaAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class MarcaControllerAdvisor {
    private static final String MESSAGE = "Message";

    @ExceptionHandler(MarcaAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMarcaAlreadyExistsException(
            MarcaAlreadyExistsException marcaAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, MarcaExceptionResponse.MARCA_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(InvalidMarcaNameLengthException.class)
    public ResponseEntity<Map<String, String>> handleInvalidMarcaNameLengthException(
            InvalidMarcaNameLengthException invalidMarcaNameLengthException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, MarcaExceptionResponse.INVALID_NAME_LENGTH.getMessage()));
    }

    @ExceptionHandler(InvalidMarcaDescriptionLengthException.class)
    public ResponseEntity<Map<String, String>> handleInvalidMarcaDescriptionLengthException(
            InvalidMarcaDescriptionLengthException invalidMarcaDescriptionLengthException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, MarcaExceptionResponse.INVALID_DESCRIPTION_LENGTH.getMessage()));
    }
}
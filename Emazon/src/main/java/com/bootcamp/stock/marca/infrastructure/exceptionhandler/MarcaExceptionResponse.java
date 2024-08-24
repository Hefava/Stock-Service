package com.bootcamp.stock.marca.infrastructure.exceptionhandler;

public enum MarcaExceptionResponse {
    MARCA_ALREADY_EXISTS("Brand already exists"),
    INVALID_NAME_LENGTH("The brand name must not exceed 50 characters"),
    INVALID_DESCRIPTION_LENGTH("The brand description must not exceed 120 characters");


    private final String message;

    MarcaExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

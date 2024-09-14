package com.bootcamp.stock.infrastructure.exceptionhandler;

public enum ArticuloExceptionResponse {
    CATEGORY_ALREADY_EXISTS("Una categoría no puede repetirse."),
    INVALID_CATEGORY_COUNT("El artículo debe tener entre 1 y 3 categorías."),
    ARTICULO_NOT_FOUND("El artículo no existe.");

    private final String message;

    ArticuloExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
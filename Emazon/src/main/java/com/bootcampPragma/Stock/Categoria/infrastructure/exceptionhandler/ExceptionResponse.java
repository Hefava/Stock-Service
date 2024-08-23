package com.bootcampPragma.Stock.Categoria.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    CATEGORY_ALREADY_EXISTS("Ya existe esa categoría."),
    INVALID_CATEGORY_NAME_LENGTH("El nombre de la categoría no debe exceder los 50 caracteres."),
    INVALID_CATEGORY_DESCRIPTION_LENGTH("La descripción de la categoría no debe exceder los 90 caracteres.");

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
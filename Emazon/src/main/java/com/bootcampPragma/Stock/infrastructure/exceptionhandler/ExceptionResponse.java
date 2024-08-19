package com.bootcampPragma.Stock.infrastructure.exceptionhandler;

public enum ExceptionResponse {
    CATEGORY_ALREADY_EXISTS("Ya existe esa categoria");

    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
package com.bootcampPragma.Stock.Categoria.domain.utils;

public enum CategoriaConstants {
    MAX_NOMBRE_LENGTH(50),
    MAX_DESCRIPCION_LENGTH(90);

    private final int value;

    CategoriaConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
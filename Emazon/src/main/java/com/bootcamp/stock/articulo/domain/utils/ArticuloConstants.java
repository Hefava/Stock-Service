package com.bootcamp.stock.articulo.domain.utils;

public enum ArticuloConstants {
    MAX_NOMBRE_LENGTH(50),
    MAX_DESCRIPCION_LENGTH(150);

    private final int value;

    ArticuloConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
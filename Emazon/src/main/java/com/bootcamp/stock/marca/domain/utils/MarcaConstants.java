package com.bootcamp.stock.marca.domain.utils;

public enum MarcaConstants {
    MAX_NOMBRE_LENGTH(50),
    MAX_DESCRIPCION_LENGTH(120);

    private final int value;

    MarcaConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

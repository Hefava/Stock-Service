package com.bootcamp.stock.domain.utils.constants;

public class ArticuloConstants {
    public static final int MAX_NOMBRE_LENGTH = 50;
    public static final int MAX_DESCRIPCION_LENGTH = 150;
    public static final int MAX_CATEGORIAS = 3;

    private ArticuloConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
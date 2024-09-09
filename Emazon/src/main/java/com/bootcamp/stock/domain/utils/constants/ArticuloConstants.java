package com.bootcamp.stock.domain.utils.constants;

public class ArticuloConstants {
    public static final int MAX_NOMBRE_LENGTH = 50;
    public static final int MAX_DESCRIPCION_LENGTH = 150;
    public static final int MAX_CATEGORIAS = 3;
    public static final int MIN_SUMINISTRO = 1;
    public static final String ERROR_CANTIDAD = "La cantidad debe ser mayor que 0.";

    private ArticuloConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
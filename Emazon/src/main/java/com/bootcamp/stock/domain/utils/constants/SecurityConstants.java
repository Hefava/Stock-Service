package com.bootcamp.stock.domain.utils.constants;

public class SecurityConstants {
    public static final String ROL_ADMIN = "ADMIN";
    public static final String ROL_AUX_BODEGA = "AUX_BODEGA";
    public static final String ROL_CLIENTE = "CLIENTE";

    public static final String ACCESO_DENEGADO = "Acceso denegado: No tienes permiso para acceder a este recurso.";

    private SecurityConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

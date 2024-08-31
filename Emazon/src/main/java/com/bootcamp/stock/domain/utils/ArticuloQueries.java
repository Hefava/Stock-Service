package com.bootcamp.stock.domain.utils;

public class ArticuloQueries {

    private ArticuloQueries() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String FIND_ALL_ORDER_BY_CATEGORIA_NOMBRE =
            "SELECT a FROM ArticuloEntity a WHERE a.articuloID IN (" +
                    "SELECT a2.articuloID FROM ArticuloEntity a2 JOIN a2.categorias c " +
                    "GROUP BY a2.articuloID ORDER BY MIN(c.nombre) ASC)";
}

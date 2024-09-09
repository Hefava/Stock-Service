package com.bootcamp.stock.domain.utils.constants;

public class SwaggerConstants {

    // General Responses
    public static final String RESPONSE_SUCCESS = "Operación realizada exitosamente";
    public static final String RESPONSE_BAD_REQUEST = "Solicitud incorrecta";
    public static final String RESPONSE_INTERNAL_SERVER_ERROR = "Error interno del servidor";

    // Articulo Controller
    public static final String ARTICULO_SAVE_SUMMARY = "Crear un artículo";
    public static final String ARTICULO_SAVE_DESCRIPTION = "Crea un nuevo artículo en el sistema.";
    public static final String ARTICULO_AGREGAR_SUMMARY = "Actualizar cantidad de un artículo";
    public static final String ARTICULO_AGREGAR_DESCRIPTION = "Agrega más cantidad a un artículo existente.";
    public static final String ARTICULO_GET_SUMMARY = "Obtener artículos ordenados por criterio";
    public static final String ARTICULO_GET_DESCRIPTION = "Obtiene una lista de artículos ordenados por nombre, nombre de marca o categoría y paginados.";

    public static final String ARTICULO_GET_SUCCESS = "Artículos obtenidos exitosamente";
    public static final String ORDER_DEFAULT = "nombre";
    public static final String ARTICULO_ORDER_DESCRIPTION = "Campo de ordenación: nombre, marcaNombre, categoriaNombre";
    public static final String ORDER_DEFAULT_ASC = "asc";
    public static final String ARTICULO_ORDER_DIRECTION_DESCRIPTION = "Orden: asc o desc";
    public static final String ARTICULO_PAGINATION_DESCRIPTION = "Paginación";
    public static final String DATOS_ARTICULO = "Datos del artículo a crear";
    public  static final String DATOS_SUMINISTRO = "ID del artículo y cantidad a agregar";

    // Category Controller

    public static final String CATEGORY_SAVE_SUMMARY = "Guardar una categoría";
    public static final String CATEGORY_SAVE_DESCRIPTION = "Crea una nueva categoría en el sistema.";
    public static final String CATEGORY_GET_SUMMARY = "Obtener categorías ordenadas por nombre";
    public static final String CATEGORY_GET_DESCRIPTION = "Obtiene una lista de categorías ordenadas por nombre y paginadas.";
    public static final String CATEGORY_GET_SUCCESS = "Categorías obtenidas exitosamente";
    public static final String CATEGORY_SORT_ORDER_DESCRIPTION = "Orden de clasificación: asc o desc";
    public static final String CATEGORY_PAGINATION_DESCRIPTION = "Paginación de la categoría";

    // Marca Controller
    public static final String MARCA_SAVE_SUMMARY = "Guardar una marca";
    public static final String MARCA_SAVE_DESCRIPTION = "Crea una nueva marca en el sistema.";
    public static final String MARCA_GET_SUMMARY = "Obtener marcas ordenadas por nombre";
    public static final String MARCA_GET_DESCRIPTION = "Obtiene una lista de marcas ordenadas por nombre y paginadas.";
    public static final String DATOS_MARCA = "Datos de la marca a crear";

    private SwaggerConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

package com.bootcamp.stock.ports.aplication.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloResponse {
    private Long articuloID;
    private String nombre;
    private String descripcion;
    private Long cantidad;
    private Double precio;
    private String marcaNombre;
    private List<CategoriaDto> categorias;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoriaDto {
        private Long categoriaID;
        private String nombre;
    }
}

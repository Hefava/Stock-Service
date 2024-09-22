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
public class ArticuloCarritoInfoResponse {
    private String nombre;
    private Long cantidad;
    private Long precio;
    private String marcaNombre;
    private List<CategoriaInfoDto> categorias;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoriaInfoDto {
        private String nombre;
    }
}
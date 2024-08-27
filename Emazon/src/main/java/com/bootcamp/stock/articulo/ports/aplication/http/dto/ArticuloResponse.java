package com.bootcamp.stock.articulo.ports.aplication.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloResponse {
    private Long articuloID;
    private String nombre;
    private String descripcion;
    private Long cantidad;
    private Double precio;
    private String marcaNombre;
    private Set<String> categoriaNombres;
}

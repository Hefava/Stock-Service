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
    private Long articuloID;
    private String nombre;
    private Long cantidad;
    private Double precio;
    private String marcaNombre;
    private List<String> categorias;
}
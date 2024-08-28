package com.bootcamp.stock.ports.aplication.http.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarcaRequest {
    private String nombre;
    private String descripcion;
}
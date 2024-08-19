package com.bootcampPragma.Stock.ports.aplication.http.dto;

import lombok.AllArgsConstructor;
import  lombok.Getter;
import lombok.NoArgsConstructor;
import  lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private String nombre;
    private String descripcion;
}
package com.bootcamp.stock.ports.aplication.http.dto;

import lombok.*;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Long categoryID;
    private String nombre;
    private String descripcion;
}
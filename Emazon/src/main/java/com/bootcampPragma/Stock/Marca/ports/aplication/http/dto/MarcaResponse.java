package com.bootcampPragma.Stock.Marca.ports.aplication.http.dto;

import lombok.*;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MarcaResponse {
    private Long marcaID;
    private String nombre;
    private String descripcion;
}

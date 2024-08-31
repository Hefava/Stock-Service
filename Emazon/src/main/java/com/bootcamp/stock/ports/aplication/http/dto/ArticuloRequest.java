package com.bootcamp.stock.ports.aplication.http.dto;

import com.bootcamp.stock.domain.utils.constants.ArticuloConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloRequest {

    @NotNull
    @Size(max = ArticuloConstants.MAX_NOMBRE_LENGTH)
    private String nombre;

    @NotNull
    @Size(max = ArticuloConstants.MAX_DESCRIPCION_LENGTH)
    private String descripcion;

    @NotNull
    private Long cantidad;

    @NotNull
    private Double precio;

    @NotNull
    private Long marcaID;

    @NotNull
    @Size(max = ArticuloConstants.MAX_CATEGORIAS)
    private Set<Long> categoriaIDs;
}
package com.bootcamp.stock.ports.aplication.http.dto;

import static com.bootcamp.stock.domain.utils.constants.ArticuloConstants.ERROR_CANTIDAD;
import static com.bootcamp.stock.domain.utils.constants.ArticuloConstants.MIN_SUMINISTRO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgregarSuministroRequest {

        @NotNull
        private Long articuloID;

        @NotNull
        @Min(value = MIN_SUMINISTRO, message = ERROR_CANTIDAD)
        private Long cantidad;
}
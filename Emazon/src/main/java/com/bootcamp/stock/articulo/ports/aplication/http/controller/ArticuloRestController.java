package com.bootcamp.stock.articulo.ports.aplication.http.controller;

import com.bootcamp.stock.articulo.domain.api.IArticuloServicePort;
import com.bootcamp.stock.articulo.domain.model.Articulo;
import com.bootcamp.stock.articulo.ports.aplication.http.dto.ArticuloRequest;
import com.bootcamp.stock.articulo.ports.aplication.http.mapper.ArticuloRequestMapper;
import com.bootcamp.stock.articulo.ports.aplication.http.mapper.ArticuloResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articulo")
@RequiredArgsConstructor
public class ArticuloRestController {
    private final IArticuloServicePort articuloService;
    private final ArticuloRequestMapper articuloRequestMapper;
    private final ArticuloResponseMapper articuloResponseMapper;

    @Operation(summary = "Guardar un artículo", description = "Crea un nuevo artículo en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artículo creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Void> saveArticulo(
            @RequestBody @Parameter(description = "Datos del artículo a crear", required = true) ArticuloRequest articuloRequest) {
        Articulo articulo = articuloRequestMapper.toArticulo(articuloRequest);
        articuloService.saveArticulo(articulo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
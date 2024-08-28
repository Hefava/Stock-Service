package com.bootcamp.stock.ports.aplication.http.controller;

import com.bootcamp.stock.domain.api.IArticuloServicePort;
import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.utils.PageRequestArticulo;
import com.bootcamp.stock.domain.utils.SortArticulo;
import com.bootcamp.stock.ports.aplication.http.dto.ArticuloRequest;
import com.bootcamp.stock.ports.aplication.http.dto.ArticuloResponse;
import com.bootcamp.stock.ports.aplication.http.mapper.ArticuloRequestMapper;
import com.bootcamp.stock.ports.aplication.http.mapper.ArticuloResponseMapper;
import com.bootcamp.stock.domain.utils.PagedResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @Operation(summary = "Obtener artículos ordenados por criterio", description = "Obtiene una lista de artículos ordenados por nombre, nombre de marca o categoría y paginados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artículos obtenidos exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<PagedResult<ArticuloResponse>> getArticulos(
            @RequestParam(defaultValue = "nombre") @Parameter(description = "Campo de ordenación: nombre, marcaNombre, categoriaNombre") String sortBy,
            @RequestParam(defaultValue = "asc") @Parameter(description = "Orden: asc o desc") String order,
            @PageableDefault(size = 10) @Parameter(description = "Paginación") Pageable pageable) {

        SortArticulo.Direction direction = SortArticulo.Direction.ASC;
        if ("desc".equalsIgnoreCase(order)) {
            direction = SortArticulo.Direction.DESC;
        }

        SortArticulo sort = new SortArticulo(sortBy, direction);

        PageRequestArticulo pageRequest = new PageRequestArticulo(pageable.getPageNumber(), pageable.getPageSize());

        PagedResult<Articulo> result;
        if ("categoriaNombre".equalsIgnoreCase(sortBy)) {
            result = articuloService.findAllOrderByCategoriaNombre(sort, pageRequest);
        } else {
            result = articuloService.getArticulos(sort, pageRequest);
        }

        PagedResult<ArticuloResponse> response = new PagedResult<>(
                result.getContent().stream()
                        .map(articuloResponseMapper::toResponse)
                        .toList(),
                result.getPage(),
                result.getPageSize(),
                result.getTotalPages(),
                result.getTotalCount()
        );

        return ResponseEntity.ok(response);
    }
}
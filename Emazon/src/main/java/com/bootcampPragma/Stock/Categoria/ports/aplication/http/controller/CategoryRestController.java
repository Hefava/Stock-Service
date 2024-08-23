package com.bootcampPragma.Stock.Categoria.ports.aplication.http.controller;

import com.bootcampPragma.Stock.Categoria.domain.api.ICategoryServicePort;
import com.bootcampPragma.Stock.Categoria.domain.model.Category;
import com.bootcampPragma.Stock.Categoria.domain.utils.PageRequestDomain;
import com.bootcampPragma.Stock.Categoria.domain.utils.SortDomain;
import com.bootcampPragma.Stock.Categoria.ports.aplication.http.dto.CategoryRequest;
import com.bootcampPragma.Stock.Categoria.ports.aplication.http.dto.CategoryResponse;
import com.bootcampPragma.Stock.Categoria.ports.aplication.http.mapper.CategoryResponseMapper;
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

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoryRestController {
    private final ICategoryServicePort categoryService;
    private final CategoryResponseMapper categoryResponseMapper;

    @Operation(summary = "Guardar una categoría", description = "Crea una nueva categoría en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Void> saveCategory(
            @RequestBody @Parameter(description = "Datos de la categoría a crear", required = true) CategoryRequest categoryRequest) {
        Category category = new Category(null, categoryRequest.getNombre(), categoryRequest.getDescripcion());
        categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener categorías por nombre", description = "Obtiene una lista de categorías ordenadas por nombre y paginadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorías obtenidas exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategoriesByNombre(
            @RequestParam(defaultValue = "asc") @Parameter(description = "Orden de clasificación: asc o desc") String order,
            @PageableDefault(size = 5) @Parameter(hidden = true) Pageable pageable) {

        SortDomain.Direction direction = order.equalsIgnoreCase("desc") ? SortDomain.Direction.DESC : SortDomain.Direction.ASC;
        SortDomain sortDomain = new SortDomain("nombre", direction);
        PageRequestDomain pageRequestDomain = new PageRequestDomain(pageable.getPageNumber(), pageable.getPageSize());

        List<Category> categories = categoryService.getCategoriesByNombre(sortDomain, pageRequestDomain);
        List<CategoryResponse> responses = categoryResponseMapper.toResponseList(categories);
        return ResponseEntity.ok(responses);
    }
}
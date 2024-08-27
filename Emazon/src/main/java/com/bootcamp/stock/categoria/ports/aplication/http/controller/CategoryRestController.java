package com.bootcamp.stock.categoria.ports.aplication.http.controller;

import com.bootcamp.stock.categoria.domain.api.ICategoryServicePort;
import com.bootcamp.stock.categoria.domain.model.Category;
import com.bootcamp.stock.categoria.domain.utils.PageRequestCategory;
import com.bootcamp.stock.categoria.domain.utils.PagedResult;
import com.bootcamp.stock.categoria.domain.utils.SortCategory;
import com.bootcamp.stock.categoria.ports.aplication.http.dto.CategoryRequest;
import com.bootcamp.stock.categoria.ports.aplication.http.dto.CategoryResponse;
import com.bootcamp.stock.categoria.ports.aplication.http.mapper.CategoryResponseMapper;
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

    @Operation(summary = "Obtener categorías ordenadas por nombre", description = "Obtiene una lista de categorías ordenadas por nombre y paginadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categorías obtenidas exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<PagedResult<CategoryResponse>> getCategoriesByNombre(
            @RequestParam(defaultValue = "asc") @Parameter(description = "Sort order: asc or desc") String order,
            @PageableDefault(size = 5) @Parameter(hidden = true) Pageable pageable) {

        SortCategory.Direction direction = order.equalsIgnoreCase("desc") ? SortCategory.Direction.DESC : SortCategory.Direction.ASC;
        SortCategory sortDomain = new SortCategory("nombre", direction);
        PageRequestCategory pageRequestDomain = new PageRequestCategory(pageable.getPageNumber(), pageable.getPageSize());

        PagedResult<Category> result = categoryService.getCategoriesByNombre(sortDomain, pageRequestDomain);
        PagedResult<CategoryResponse> response = new PagedResult<>(
                categoryResponseMapper.toResponseList(result.getContent()),
                result.getPage(),
                result.getPageSize(),
                result.getTotalPages(),
                result.getTotalCount()
        );

        return ResponseEntity.ok(response);
    }
}
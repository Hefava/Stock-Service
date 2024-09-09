package com.bootcamp.stock.ports.aplication.http.controller;

import com.bootcamp.stock.domain.api.ICategoryServicePort;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;
import com.bootcamp.stock.ports.aplication.http.dto.CategoryRequest;
import com.bootcamp.stock.ports.aplication.http.dto.CategoryResponse;
import com.bootcamp.stock.ports.aplication.http.mapper.CategoryResponseMapper;
import com.bootcamp.stock.domain.utils.constants.SwaggerConstants;
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

    @Operation(summary = SwaggerConstants.CATEGORY_SAVE_SUMMARY, description = SwaggerConstants.CATEGORY_SAVE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = SwaggerConstants.RESPONSE_SUCCESS),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.RESPONSE_BAD_REQUEST, content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/save-category")
    public ResponseEntity<Void> saveCategory(
            @RequestBody @Parameter(description = "Datos de la categoría a crear", required = true) CategoryRequest categoryRequest) {
        Category category = new Category(null, categoryRequest.getNombre(), categoryRequest.getDescripcion());
        categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = SwaggerConstants.CATEGORY_GET_SUMMARY, description = SwaggerConstants.CATEGORY_GET_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerConstants.CATEGORY_GET_SUCCESS),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.RESPONSE_BAD_REQUEST, content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/get-categories")
    public ResponseEntity<PagedResult<CategoryResponse>> getCategoriesByNombre(
            @RequestParam(defaultValue = SwaggerConstants.ORDER_DEFAULT_ASC) @Parameter(description = SwaggerConstants.CATEGORY_SORT_ORDER_DESCRIPTION) String order,
            @PageableDefault(size = 5) @Parameter(description = SwaggerConstants.CATEGORY_PAGINATION_DESCRIPTION, hidden = true) Pageable pageable) {

        SortUtil.Direction direction = order.equalsIgnoreCase(SwaggerConstants.ORDER_DEFAULT_ASC) ? SortUtil.Direction.ASC : SortUtil.Direction.DESC;
        SortUtil sortDomain = new SortUtil(SwaggerConstants.ORDER_DEFAULT, direction);
        PageRequestUtil pageRequestDomain = new PageRequestUtil(pageable.getPageNumber(), pageable.getPageSize());

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
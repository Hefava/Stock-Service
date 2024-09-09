package com.bootcamp.stock.ports.aplication.http.controller;

import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.api.IMarcaServicePort;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.ports.aplication.http.dto.MarcaRequest;
import com.bootcamp.stock.ports.aplication.http.dto.MarcaResponse;
import com.bootcamp.stock.ports.aplication.http.mapper.MarcaRequestMapper;
import com.bootcamp.stock.ports.aplication.http.mapper.MarcaResponseMapper;
import com.bootcamp.stock.domain.utils.constants.SwaggerConstants;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;
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

import static com.bootcamp.stock.domain.utils.constants.SwaggerConstants.DATOS_MARCA;

@RestController
@RequestMapping("/marca")
@RequiredArgsConstructor
public class MarcaRestController {
    private final IMarcaServicePort marcaService;
    private final MarcaRequestMapper marcaRequestMapper;
    private final MarcaResponseMapper marcaResponseMapper;

    @Operation(summary = SwaggerConstants.MARCA_SAVE_SUMMARY, description = SwaggerConstants.MARCA_SAVE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = SwaggerConstants.RESPONSE_SUCCESS),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.RESPONSE_BAD_REQUEST, content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/save-marca")
    public ResponseEntity<Void> saveMarca(
            @RequestBody @Parameter(description = DATOS_MARCA, required = true) MarcaRequest marcaRequest) {
        Marca marca = marcaRequestMapper.toMarca(marcaRequest);
        marcaService.saveMarca(marca);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = SwaggerConstants.MARCA_GET_SUMMARY, description = SwaggerConstants.MARCA_GET_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerConstants.RESPONSE_SUCCESS),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.RESPONSE_BAD_REQUEST, content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/get-marcas")
    public ResponseEntity<PagedResult<MarcaResponse>> getMarcasByNombre(
            @RequestParam(defaultValue = SwaggerConstants.ORDER_DEFAULT_ASC) @Parameter(description = SwaggerConstants.CATEGORY_SORT_ORDER_DESCRIPTION) String order,
            @PageableDefault(size = 5) @Parameter(hidden = true) Pageable pageable) {

        SortUtil.Direction direction = order.equalsIgnoreCase(SwaggerConstants.ORDER_DEFAULT_ASC) ? SortUtil.Direction.ASC : SortUtil.Direction.DESC;
        SortUtil sortDomain = new SortUtil(SwaggerConstants.ORDER_DEFAULT, direction);
        PageRequestUtil pageRequestDomain = new PageRequestUtil(pageable.getPageNumber(), pageable.getPageSize());

        PagedResult<Marca> result = marcaService.getMarcasByNombre(sortDomain, pageRequestDomain);
        PagedResult<MarcaResponse> response = new PagedResult<>(
                marcaResponseMapper.toResponseList(result.getContent()),
                result.getPage(),
                result.getPageSize(),
                result.getTotalPages(),
                result.getTotalCount()
        );

        return ResponseEntity.ok(response);
    }
}
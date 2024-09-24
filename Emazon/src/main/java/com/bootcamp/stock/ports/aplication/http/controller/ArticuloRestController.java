package com.bootcamp.stock.ports.aplication.http.controller;

import com.bootcamp.stock.domain.api.IArticuloServicePort;
import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;
import com.bootcamp.stock.ports.aplication.http.dto.*;
import com.bootcamp.stock.ports.aplication.http.mapper.ArticuloCarritoInfoResponseMapper;
import com.bootcamp.stock.ports.aplication.http.mapper.ArticuloInfoResponseMapper;
import com.bootcamp.stock.ports.aplication.http.mapper.ArticuloRequestMapper;
import com.bootcamp.stock.ports.aplication.http.mapper.ArticuloResponseMapper;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.utils.constants.SwaggerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bootcamp.stock.domain.utils.constants.SwaggerConstants.DATOS_ARTICULO;
import static com.bootcamp.stock.domain.utils.constants.SwaggerConstants.DATOS_SUMINISTRO;

@RestController
@RequestMapping("/articulo")
@RequiredArgsConstructor
public class ArticuloRestController {
    private final IArticuloServicePort articuloService;
    private final ArticuloRequestMapper articuloRequestMapper;
    private final ArticuloResponseMapper articuloResponseMapper;
    private final ArticuloInfoResponseMapper articuloInfoResponseMapper;
    private final ArticuloCarritoInfoResponseMapper articuloCarritoInfoResponseMapper;

    @Operation(summary = SwaggerConstants.ARTICULO_SAVE_SUMMARY, description = SwaggerConstants.ARTICULO_SAVE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = SwaggerConstants.RESPONSE_SUCCESS),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.RESPONSE_BAD_REQUEST, content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/save-articulo")
    public ResponseEntity<Void> saveArticulo(
            @RequestBody @Parameter(description = DATOS_ARTICULO, required = true) ArticuloRequest articuloRequest) {
        Articulo articulo = articuloRequestMapper.toArticulo(articuloRequest);
        articuloService.saveArticulo(articulo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = SwaggerConstants.ARTICULO_AGREGAR_SUMMARY, description = SwaggerConstants.ARTICULO_AGREGAR_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerConstants.RESPONSE_SUCCESS),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.RESPONSE_BAD_REQUEST, content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/agregar-cantidad-articulo")
    public ResponseEntity<Void> agregarCantidadArticulo(
            @RequestBody @Valid @Parameter(description = DATOS_SUMINISTRO, required = true)
            AgregarSuministroRequest agregarSuministroRequest) {
        articuloService.agregarSuministro(agregarSuministroRequest.getArticuloID(), agregarSuministroRequest.getCantidad());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = SwaggerConstants.ARTICULO_GET_SUMMARY, description = SwaggerConstants.ARTICULO_GET_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = SwaggerConstants.ARTICULO_GET_SUCCESS),
            @ApiResponse(responseCode = "400", description = SwaggerConstants.RESPONSE_BAD_REQUEST, content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = SwaggerConstants.RESPONSE_INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/get-articulos")
    public ResponseEntity<PagedResult<ArticuloResponse>> getArticulos(
            @RequestParam(defaultValue = SwaggerConstants.ORDER_DEFAULT) @Parameter(description = SwaggerConstants.ARTICULO_ORDER_DESCRIPTION) String sortBy,
            @RequestParam(defaultValue = SwaggerConstants.ORDER_DEFAULT_ASC) @Parameter(description = SwaggerConstants.ARTICULO_ORDER_DIRECTION_DESCRIPTION) String order,
            @PageableDefault(size = 5) @Parameter(description = SwaggerConstants.ARTICULO_PAGINATION_DESCRIPTION) Pageable pageable) {

        SortUtil.Direction direction = SortUtil.Direction.ASC;
        if ("desc".equalsIgnoreCase(order)) {
            direction = SortUtil.Direction.DESC;
        }

        SortUtil sort = new SortUtil(sortBy, direction);

        PageRequestUtil pageRequest = new PageRequestUtil(pageable.getPageNumber(), pageable.getPageSize());

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

    @GetMapping("/articulo-info/{articuloID}")
    public ResponseEntity<ArticuloInfoResponse> getArticuloInfo(@PathVariable Long articuloID) {
        Articulo articulo = articuloService.articuloInfo(articuloID);
        return ResponseEntity.ok(articuloInfoResponseMapper.toResponse(articulo));
    }

    @Operation(summary = "Get article details by IDs", description = "Returns article details for the provided list of article IDs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of articles"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/get-articulos-by-ids")
    public ResponseEntity<PagedResult<ArticuloCarritoInfoResponse>> getArticulosByIds(
            @RequestBody ArticuloCarritoInfoRequest request) {

        SortUtil.Direction direction = "desc".equalsIgnoreCase(request.getOrder()) ? SortUtil.Direction.DESC : SortUtil.Direction.ASC;
        SortUtil sort = new SortUtil(request.getSortBy(), direction);

        PagedResult<Articulo> result = articuloService.getArticulosByIdsAndFilters(
                request.getArticuloIds(), request.getCategoriaNombre(), request.getMarcaNombre(), sort,
                new PageRequestUtil(request.getPageable().getPageNumber(), request.getPageable().getPageSize())
        );

        PagedResult<ArticuloCarritoInfoResponse> response = new PagedResult<>(
                result.getContent().stream()
                        .map(articuloCarritoInfoResponseMapper::toResponse)
                        .toList(),
                result.getPage(),
                result.getPageSize(),
                result.getTotalPages(),
                result.getTotalCount()
        );

        return ResponseEntity.ok(response);
    }
}
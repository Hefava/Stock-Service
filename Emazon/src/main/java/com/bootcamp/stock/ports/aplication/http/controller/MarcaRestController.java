package com.bootcamp.stock.ports.aplication.http.controller;

import com.bootcamp.stock.domain.utils.PagedResult;
import com.bootcamp.stock.domain.api.IMarcaServicePort;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.utils.PageRequestMarca;
import com.bootcamp.stock.domain.utils.SortMarca;
import com.bootcamp.stock.ports.aplication.http.dto.MarcaRequest;
import com.bootcamp.stock.ports.aplication.http.dto.MarcaResponse;
import com.bootcamp.stock.ports.aplication.http.mapper.MarcaResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/marca")
@RequiredArgsConstructor
public class MarcaRestController {
    private final IMarcaServicePort marcaService;
    private final MarcaResponseMapper marcaResponseMapper;

    @PostMapping
    @Operation(summary = "Guardar una marca", description = "Crea una nueva marca en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Marca creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    public ResponseEntity<Void> saveMarca(@RequestBody MarcaRequest marcaRequest) {
        Marca marca = new Marca(null, marcaRequest.getNombre(), marcaRequest.getDescripcion());
        marcaService.saveMarca(marca);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener marcas ordenadas por nombre", description = "Obtiene una lista de marcas ordenadas por nombre y paginadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marcas obtenidas exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
    })
    @GetMapping
    public ResponseEntity<PagedResult<MarcaResponse>> getMarcasByNombre(
            @RequestParam(defaultValue = "asc") @Parameter(description = "Sort order: asc or desc") String order,
            @PageableDefault(size = 5) @Parameter(hidden = true) Pageable pageable) {

        SortMarca.Direction direction = order.equalsIgnoreCase("desc") ? SortMarca.Direction.DESC : SortMarca.Direction.ASC;
        SortMarca sortDomain = new SortMarca("nombre", direction);
        PageRequestMarca pageRequestDomain = new PageRequestMarca(pageable.getPageNumber(), pageable.getPageSize());

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
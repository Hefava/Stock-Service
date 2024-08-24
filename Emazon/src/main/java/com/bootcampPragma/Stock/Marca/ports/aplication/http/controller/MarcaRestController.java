package com.bootcampPragma.Stock.Marca.ports.aplication.http.controller;

import com.bootcampPragma.Stock.Marca.domain.api.IMarcaServicePort;
import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.domain.utils.PageRequestMarca;
import com.bootcampPragma.Stock.Marca.domain.utils.SortMarca;
import com.bootcampPragma.Stock.Marca.ports.aplication.http.dto.MarcaRequest;
import com.bootcampPragma.Stock.Marca.ports.aplication.http.dto.MarcaResponse;
import com.bootcampPragma.Stock.Marca.ports.aplication.http.mapper.MarcaResponseMapper;
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

import java.util.List;

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
    public ResponseEntity<List<MarcaResponse>> getMarcasByNombre(
            @RequestParam(defaultValue = "asc") @Parameter(description = "Orden de clasificación: asc o desc") String order,
            @PageableDefault(size = 5) @Parameter(hidden = true) Pageable pageable) {

        SortMarca.Direction direction = order.equalsIgnoreCase("desc") ? SortMarca.Direction.DESC : SortMarca.Direction.ASC;
        SortMarca sortDomain = new SortMarca("nombre", direction);
        PageRequestMarca pageRequestDomain = new PageRequestMarca(pageable.getPageNumber(), pageable.getPageSize());

        List<Marca> marcas = marcaService.getMarcasByNombre(sortDomain, pageRequestDomain);
        List<MarcaResponse> responses = marcaResponseMapper.toResponseList(marcas);
        return ResponseEntity.ok(responses);
    }
}


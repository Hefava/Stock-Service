package com.bootcampPragma.Stock.ports.aplication.http.controller;

import com.bootcampPragma.Stock.domain.api.IMarcaServicePort;
import com.bootcampPragma.Stock.domain.model.Marca;
import com.bootcampPragma.Stock.ports.aplication.http.dto.MarcaRequest;
import com.bootcampPragma.Stock.ports.aplication.http.mapper.marca.MarcaResponseMapper;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Void> saveMarca(@RequestBody MarcaRequest marcaRequest) {
        Marca marca = new Marca(null, marcaRequest.getNombre(), marcaRequest.getDescripcion());
        marcaService.saveMarca(marca);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
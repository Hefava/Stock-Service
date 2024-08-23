package com.bootcampPragma.Stock.Marca.ports.aplication.http.controller;

import com.bootcampPragma.Stock.Marca.domain.api.IMarcaServicePort;
import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.ports.aplication.http.dto.MarcaRequest;
import com.bootcampPragma.Stock.Marca.ports.aplication.http.mapper.MarcaResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
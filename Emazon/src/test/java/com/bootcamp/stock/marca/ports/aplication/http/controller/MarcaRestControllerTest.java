package com.bootcamp.stock.marca.ports.aplication.http.controller;

import com.bootcamp.stock.marca.domain.api.IMarcaServicePort;
import com.bootcamp.stock.marca.domain.model.Marca;
import com.bootcamp.stock.marca.domain.utils.PageRequestMarca;
import com.bootcamp.stock.marca.domain.utils.SortMarca;
import com.bootcamp.stock.marca.ports.aplication.http.dto.MarcaRequest;
import com.bootcamp.stock.marca.ports.aplication.http.dto.MarcaResponse;
import com.bootcamp.stock.marca.ports.aplication.http.mapper.MarcaResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MarcaRestControllerTest {

    @Mock
    private IMarcaServicePort marcaService;

    @Mock
    private MarcaResponseMapper marcaResponseMapper;

    @InjectMocks
    private MarcaRestController marcaRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveMarca_ShouldReturnCreatedStatus() {
        MarcaRequest marcaRequest = new MarcaRequest("Nike", "Ropa deportiva");

        ResponseEntity<Void> response = marcaRestController.saveMarca(marcaRequest);

        verify(marcaService, times(1)).saveMarca(any(Marca.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getMarcasByNombre_ShouldReturnSortedMarcas() {
        Pageable pageable = PageRequest.of(0, 5);

        List<Marca> marcas = List.of(
                new Marca(1L, "Adidas", "Ropa deportiva"),
                new Marca(2L, "Nike", "Ropa deportiva"),
                new Marca(3L, "Puma", "Ropa deportiva")
        );

        List<MarcaResponse> marcaResponses = List.of(
                new MarcaResponse(1L, "Adidas", "Ropa deportiva"),
                new MarcaResponse(2L, "Nike", "Ropa deportiva"),
                new MarcaResponse(3L, "Puma", "Ropa deportiva")
        );

        when(marcaService.getMarcasByNombre(any(SortMarca.class), any(PageRequestMarca.class))).thenReturn(marcas);
        when(marcaResponseMapper.toResponseList(marcas)).thenReturn(marcaResponses);

        ResponseEntity<List<MarcaResponse>> response = marcaRestController.getMarcasByNombre("asc", pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(marcaResponses, response.getBody());
        verify(marcaService, times(1)).getMarcasByNombre(any(SortMarca.class), any(PageRequestMarca.class));
        verify(marcaResponseMapper, times(1)).toResponseList(marcas);
    }

    @Test
    void getMarcasByNombre_ShouldUseDescendingOrderWhenSpecified() {
        Pageable pageable = PageRequest.of(0, 5);

        List<Marca> marcas = List.of(
                new Marca(3L, "Puma", "Ropa deportiva"),
                new Marca(2L, "Nike", "Ropa deportiva"),
                new Marca(1L, "Adidas", "Ropa deportiva")
        );

        List<MarcaResponse> marcaResponses = List.of(
                new MarcaResponse(3L, "Puma", "Ropa deportiva"),
                new MarcaResponse(2L, "Nike", "Ropa deportiva"),
                new MarcaResponse(1L, "Adidas", "Ropa deportiva")
        );

        when(marcaService.getMarcasByNombre(any(SortMarca.class), any(PageRequestMarca.class))).thenReturn(marcas);
        when(marcaResponseMapper.toResponseList(marcas)).thenReturn(marcaResponses);

        ResponseEntity<List<MarcaResponse>> response = marcaRestController.getMarcasByNombre("desc", pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(marcaResponses, response.getBody());
        verify(marcaService, times(1)).getMarcasByNombre(any(SortMarca.class), any(PageRequestMarca.class));
        verify(marcaResponseMapper, times(1)).toResponseList(marcas);
    }
}
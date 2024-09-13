package com.bootcamp.stock.marca.ports.aplication.http.controller;

import com.bootcamp.stock.domain.api.IMarcaServicePort;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;
import com.bootcamp.stock.ports.aplication.http.controller.MarcaRestController;
import com.bootcamp.stock.ports.aplication.http.dto.MarcaResponse;
import com.bootcamp.stock.ports.aplication.http.mapper.MarcaResponseMapper;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
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

        PagedResult<Marca> pagedResult = new PagedResult<>(marcas, 0, 5, 1, 3);
        PagedResult<MarcaResponse> expectedPagedResult = new PagedResult<>(marcaResponses, 0, 5, 1, 3);

        when(marcaService.getMarcasByNombre(any(SortUtil.class), any(PageRequestUtil.class))).thenReturn(pagedResult);
        when(marcaResponseMapper.toResponseList(marcas)).thenReturn(marcaResponses);

        ResponseEntity<PagedResult<MarcaResponse>> response = marcaRestController.getMarcasByNombre("asc", pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPagedResult, response.getBody());
        verify(marcaService, times(1)).getMarcasByNombre(any(SortUtil.class), any(PageRequestUtil.class));
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

        PagedResult<Marca> pagedResult = new PagedResult<>(marcas, 0, 5, 1, 3);
        PagedResult<MarcaResponse> expectedPagedResult = new PagedResult<>(marcaResponses, 0, 5, 1, 3);

        when(marcaService.getMarcasByNombre(any(SortUtil.class), any(PageRequestUtil.class))).thenReturn(pagedResult);
        when(marcaResponseMapper.toResponseList(marcas)).thenReturn(marcaResponses);

        ResponseEntity<PagedResult<MarcaResponse>> response = marcaRestController.getMarcasByNombre("desc", pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPagedResult, response.getBody());
        verify(marcaService, times(1)).getMarcasByNombre(any(SortUtil.class), any(PageRequestUtil.class));
        verify(marcaResponseMapper, times(1)).toResponseList(marcas);
    }
}
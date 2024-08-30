package com.bootcamp.stock.articulo.ports.aplication.http.controller;

import com.bootcamp.stock.domain.api.IArticuloServicePort;
import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.utils.Pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.Pagination.SortUtil;
import com.bootcamp.stock.ports.aplication.http.dto.ArticuloRequest;
import com.bootcamp.stock.ports.aplication.http.dto.ArticuloResponse;
import com.bootcamp.stock.ports.aplication.http.mapper.ArticuloRequestMapper;
import com.bootcamp.stock.ports.aplication.http.mapper.ArticuloResponseMapper;
import com.bootcamp.stock.domain.utils.Pagination.PagedResult;
import com.bootcamp.stock.ports.aplication.http.controller.ArticuloRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ArticuloRestControllerTest {

    @Mock
    private IArticuloServicePort articuloService;

    @Mock
    private ArticuloRequestMapper articuloRequestMapper;

    @Mock
    private ArticuloResponseMapper articuloResponseMapper;

    @InjectMocks
    private ArticuloRestController articuloRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveArticulo() {
        // Arrange
        ArticuloRequest articuloRequest = new ArticuloRequest();
        Articulo articulo = new Articulo();
        when(articuloRequestMapper.toArticulo(articuloRequest)).thenReturn(articulo);

        // Act
        ResponseEntity<Void> responseEntity = articuloRestController.saveArticulo(articuloRequest);

        // Assert
        verify(articuloService, times(1)).saveArticulo(articulo);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testGetArticulosSortedByArticuloNameAsc() {
        // Arrange
        List<Articulo> articulos = Collections.singletonList(new Articulo());
        PagedResult<Articulo> pagedResult = new PagedResult<>(articulos, 0, 10, 1, 1L);

        when(articuloService.getArticulos(any(SortUtil.class), any(PageRequestUtil.class)))
                .thenReturn(pagedResult);

        ArticuloResponse articuloResponse = new ArticuloResponse();
        when(articuloResponseMapper.toResponse(any(Articulo.class))).thenReturn(articuloResponse);

        // Act & Assert
        var responseEntity = articuloRestController.getArticulos("nombre", "asc", PageRequest.of(0, 10));
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().getContent().size());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(articuloService, times(1)).getArticulos(any(SortUtil.class), any(PageRequestUtil.class));
        verify(articuloResponseMapper, times(1)).toResponse(any(Articulo.class));
    }

    @Test
    void testGetArticulosSortedByArticuloNameDesc() {
        // Arrange
        List<Articulo> articulos = Collections.singletonList(new Articulo());
        PagedResult<Articulo> pagedResult = new PagedResult<>(articulos, 0, 20, 1, 1L);

        when(articuloService.getArticulos(any(SortUtil.class), any(PageRequestUtil.class)))
                .thenReturn(pagedResult);

        ArticuloResponse articuloResponse = new ArticuloResponse();
        when(articuloResponseMapper.toResponse(any(Articulo.class))).thenReturn(articuloResponse);

        // Act & Assert
        var responseEntity = articuloRestController.getArticulos("nombre", "desc", PageRequest.of(0, 20));
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().getContent().size());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(articuloService, times(1)).getArticulos(any(SortUtil.class), any(PageRequestUtil.class));
        verify(articuloResponseMapper, times(1)).toResponse(any(Articulo.class));
    }

    @Test
    void testGetArticulosSortedByMarcaNameAsc() {
        // Arrange
        List<Articulo> articulos = Collections.singletonList(new Articulo());
        PagedResult<Articulo> pagedResult = new PagedResult<>(articulos, 0, 10, 1, 1L);

        when(articuloService.getArticulos(any(SortUtil.class), any(PageRequestUtil.class)))
                .thenReturn(pagedResult);

        ArticuloResponse articuloResponse = new ArticuloResponse();
        when(articuloResponseMapper.toResponse(any(Articulo.class))).thenReturn(articuloResponse);

        // Act & Assert
        var responseEntity = articuloRestController.getArticulos("marcaNombre", "asc", PageRequest.of(0, 10));
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().getContent().size());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(articuloService, times(1)).getArticulos(any(SortUtil.class), any(PageRequestUtil.class));
        verify(articuloResponseMapper, times(1)).toResponse(any(Articulo.class));
    }

    @Test
    void testGetArticulosSortedByMarcaNameDesc() {
        // Arrange
        List<Articulo> articulos = Collections.singletonList(new Articulo());
        PagedResult<Articulo> pagedResult = new PagedResult<>(articulos, 0, 20, 1, 1L);

        when(articuloService.getArticulos(any(SortUtil.class), any(PageRequestUtil.class)))
                .thenReturn(pagedResult);

        ArticuloResponse articuloResponse = new ArticuloResponse();
        when(articuloResponseMapper.toResponse(any(Articulo.class))).thenReturn(articuloResponse);

        // Act & Assert
        var responseEntity = articuloRestController.getArticulos("marcaNombre", "desc", PageRequest.of(0, 20));
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().getContent().size());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(articuloService, times(1)).getArticulos(any(SortUtil.class), any(PageRequestUtil.class));
        verify(articuloResponseMapper, times(1)).toResponse(any(Articulo.class));
    }

    @Test
    void testGetArticulosSortedByCategoriaNameAsc() {
        // Arrange
        List<Articulo> articulos = Collections.singletonList(new Articulo());
        PagedResult<Articulo> pagedResult = new PagedResult<>(articulos, 0, 10, 1, 1L);

        when(articuloService.findAllOrderByCategoriaNombre(any(SortUtil.class), any(PageRequestUtil.class)))
                .thenReturn(pagedResult);

        ArticuloResponse articuloResponse = new ArticuloResponse();
        when(articuloResponseMapper.toResponse(any(Articulo.class))).thenReturn(articuloResponse);

        // Act & Assert
        var responseEntity = articuloRestController.getArticulos("categoriaNombre", "asc", PageRequest.of(0, 10));
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().getContent().size());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(articuloService, times(1)).findAllOrderByCategoriaNombre(any(SortUtil.class), any(PageRequestUtil.class));
        verify(articuloResponseMapper, times(1)).toResponse(any(Articulo.class));
    }

    @Test
    void testGetArticulosSortedByCategoriaNameDesc() {
        // Arrange
        List<Articulo> articulos = Collections.singletonList(new Articulo());
        PagedResult<Articulo> pagedResult = new PagedResult<>(articulos, 0, 20, 1, 1L);

        when(articuloService.findAllOrderByCategoriaNombre(any(SortUtil.class), any(PageRequestUtil.class)))
                .thenReturn(pagedResult);

        ArticuloResponse articuloResponse = new ArticuloResponse();
        when(articuloResponseMapper.toResponse(any(Articulo.class))).thenReturn(articuloResponse);

        // Act & Assert
        var responseEntity = articuloRestController.getArticulos("categoriaNombre", "desc", PageRequest.of(0, 20));
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().getContent().size());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(articuloService, times(1)).findAllOrderByCategoriaNombre(any(SortUtil.class), any(PageRequestUtil.class));
        verify(articuloResponseMapper, times(1)).toResponse(any(Articulo.class));
    }
}
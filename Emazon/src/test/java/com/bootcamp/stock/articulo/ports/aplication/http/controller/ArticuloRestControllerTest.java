package com.bootcamp.stock.articulo.ports.aplication.http.controller;

import com.bootcamp.stock.articulo.domain.api.IArticuloServicePort;
import com.bootcamp.stock.articulo.domain.model.Articulo;
import com.bootcamp.stock.articulo.ports.aplication.http.dto.ArticuloRequest;
import com.bootcamp.stock.articulo.ports.aplication.http.mapper.ArticuloRequestMapper;
import com.bootcamp.stock.articulo.ports.aplication.http.mapper.ArticuloResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void saveArticulo_ShouldReturnCreatedStatus_WhenArticuloIsValid() {
        // Arrange
        ArticuloRequest articuloRequest = new ArticuloRequest();
        Articulo articulo = new Articulo(1L, "Laptop", "Gaming Laptop", 5L, 1200.0, new HashSet<>(), null);

        when(articuloRequestMapper.toArticulo(articuloRequest)).thenReturn(articulo);

        // Act
        ResponseEntity<Void> response = articuloRestController.saveArticulo(articuloRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(articuloService, times(1)).saveArticulo(articulo);
    }

    @Test
    void saveArticulo_ShouldHandleException_WhenServiceThrowsException() {
        // Arrange
        ArticuloRequest articuloRequest = new ArticuloRequest();
        Articulo articulo = new Articulo(1L, "Laptop", "Gaming Laptop", 5L, 1200.0, new HashSet<>(), null);

        when(articuloRequestMapper.toArticulo(articuloRequest)).thenReturn(articulo);
        doThrow(new RuntimeException("Internal Server Error")).when(articuloService).saveArticulo(articulo);

        // Act & Assert
        try {
            articuloRestController.saveArticulo(articuloRequest);
        } catch (RuntimeException e) {
            assertEquals("Internal Server Error", e.getMessage());
        }

        verify(articuloService, times(1)).saveArticulo(articulo);
    }
}
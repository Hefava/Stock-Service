package com.bootcamp.stock.articulo.ports.persistency.mysql.adapter;

import com.bootcamp.stock.articulo.domain.model.Articulo;
import com.bootcamp.stock.articulo.ports.persistency.mysql.entity.ArticuloEntity;
import com.bootcamp.stock.articulo.ports.persistency.mysql.mapper.ArticuloEntityMapper;
import com.bootcamp.stock.articulo.ports.persistency.mysql.repository.IArticuloRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticuloAdapterTest {

    @Mock
    private IArticuloRepository articuloRepository;

    @Mock
    private ArticuloEntityMapper articuloEntityMapper;

    @InjectMocks
    private ArticuloAdapter articuloAdapter;

    private Articulo articulo;
    private ArticuloEntity articuloEntity;

    @BeforeEach
    void setUp() {
        articulo = new Articulo(1L, "Laptop", "Gaming Laptop", 5L, 1200.0, Set.of(), null);
        articuloEntity = new ArticuloEntity(1L, "Laptop", "Gaming Laptop", 5L, 1200.0, null, null);
    }

    @Test
    void saveArticulo_ShouldSaveArticulo() {
        when(articuloEntityMapper.toEntity(any(Articulo.class))).thenReturn(articuloEntity);

        articuloAdapter.saveArticulo(articulo);

        verify(articuloRepository, times(1)).save(articuloEntity);
    }

    @Test
    void findByNombre_ShouldReturnArticulo_WhenArticuloExists() {
        when(articuloRepository.findByNombre("Laptop")).thenReturn(Optional.of(articuloEntity));
        when(articuloEntityMapper.toArticulo(any(ArticuloEntity.class))).thenReturn(articulo);

        Optional<Articulo> result = articuloAdapter.findByNombre("Laptop");

        assertTrue(result.isPresent());
        assertEquals(articulo, result.get());
    }

    @Test
    void findByNombre_ShouldReturnEmpty_WhenArticuloDoesNotExist() {
        when(articuloRepository.findByNombre("Laptop")).thenReturn(Optional.empty());

        Optional<Articulo> result = articuloAdapter.findByNombre("Laptop");

        assertTrue(result.isEmpty());
    }
}

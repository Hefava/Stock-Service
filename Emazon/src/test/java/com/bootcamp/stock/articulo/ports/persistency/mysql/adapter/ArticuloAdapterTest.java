package com.bootcamp.stock.articulo.ports.persistency.mysql.adapter;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.utils.Pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.Pagination.SortUtil;
import com.bootcamp.stock.ports.persistency.mysql.entity.ArticuloEntity;
import com.bootcamp.stock.ports.persistency.mysql.mapper.ArticuloEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.repository.IArticuloRepository;
import com.bootcamp.stock.domain.utils.Pagination.PagedResult;
import com.bootcamp.stock.ports.persistency.mysql.adapter.ArticuloAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
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

    @Test
    void getArticulos_ShouldReturnPagedResult() {
        SortUtil sortDomain = new SortUtil("nombre", SortUtil.Direction.ASC);
        PageRequestUtil pageRequestDomain = new PageRequestUtil(0, 10);

        org.springframework.data.domain.Sort sort = org.springframework.data.domain.Sort.by(sortDomain.getProperty());
        if (sortDomain.getDirection() == SortUtil.Direction.DESC) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        PageRequest pageRequest = PageRequest.of(pageRequestDomain.getPage(), pageRequestDomain.getSize(), sort);
        Page<ArticuloEntity> page = mock(Page.class);

        when(articuloRepository.findAll(pageRequest)).thenReturn(page);
        when(page.getContent()).thenReturn(List.of(articuloEntity));
        when(page.getNumber()).thenReturn(0);
        when(page.getSize()).thenReturn(10);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(articuloEntityMapper.toArticulo(any(ArticuloEntity.class))).thenReturn(articulo);

        PagedResult<Articulo> result = articuloAdapter.getArticulos(sortDomain, pageRequestDomain);

        assertEquals(1, result.getContent().size());
        assertEquals(0, result.getPage());
        assertEquals(10, result.getPageSize());
        assertEquals(1, result.getTotalPages());
        assertEquals(1L, result.getTotalCount());
    }

    @Test
    void findAllOrderByCategoriaNombre_ShouldReturnPagedResult() {
        SortUtil sortDomain = new SortUtil("categorias.nombre", SortUtil.Direction.ASC);
        PageRequestUtil pageRequestDomain = new PageRequestUtil(0, 10);

        PageRequest pageRequest = PageRequest.of(pageRequestDomain.getPage(), pageRequestDomain.getSize(), org.springframework.data.domain.Sort.by("categorias.nombre").ascending());
        Page<ArticuloEntity> page = mock(Page.class);

        when(articuloRepository.findAllOrderByCategoriaNombre(pageRequest)).thenReturn(page);
        when(page.getContent()).thenReturn(List.of(articuloEntity));
        when(page.getNumber()).thenReturn(0);
        when(page.getSize()).thenReturn(10);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(articuloEntityMapper.toArticulo(any(ArticuloEntity.class))).thenReturn(articulo);

        PagedResult<Articulo> result = articuloAdapter.findAllOrderByCategoriaNombre(sortDomain, pageRequestDomain);

        assertEquals(1, result.getContent().size());
        assertEquals(0, result.getPage());
        assertEquals(10, result.getPageSize());
        assertEquals(1, result.getTotalPages());
        assertEquals(1L, result.getTotalCount());
    }
}
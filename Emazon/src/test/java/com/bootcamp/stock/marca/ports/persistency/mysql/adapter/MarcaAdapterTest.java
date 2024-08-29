package com.bootcamp.stock.marca.ports.persistency.mysql.adapter;

import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.utils.PageRequestUtil;
import com.bootcamp.stock.domain.utils.PagedResult;
import com.bootcamp.stock.domain.utils.SortUtil;
import com.bootcamp.stock.ports.persistency.mysql.adapter.MarcaAdapter;
import com.bootcamp.stock.ports.persistency.mysql.entity.MarcaEntity;
import com.bootcamp.stock.ports.persistency.mysql.mapper.MarcaEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.repository.IMarcaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MarcaAdapterTest {

    @Mock
    private IMarcaRepository marcaRepository;

    @Mock
    private MarcaEntityMapper marcaEntityMapper;

    @InjectMocks
    private MarcaAdapter marcaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveMarca_ShouldCallRepositorySave() {
        Marca marca = new Marca(1L, "Nike", "Sporting goods");

        marcaAdapter.saveMarca(marca);

        verify(marcaEntityMapper, times(1)).toEntity(marca);
        verify(marcaRepository, times(1)).save(any());
    }

    @Test
    void getMarcasByNombre_ShouldReturnPagedResult() {
        SortUtil sort = new SortUtil("name", SortUtil.Direction.ASC);
        PageRequestUtil pageRequestMarca = new PageRequestUtil(0, 5);

        Page<MarcaEntity> page = mock(Page.class);
        List<MarcaEntity> entities = List.of(
                new MarcaEntity(1L, "Nike", "Sporting goods"),
                new MarcaEntity(2L, "Adidas", "Sportswear")
        );

        when(marcaRepository.findAll(any(PageRequest.class))).thenReturn(page);
        when(page.getContent()).thenReturn(entities);
        when(page.getNumber()).thenReturn(0);
        when(page.getSize()).thenReturn(5);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(2L);

        List<Marca> marcas = List.of(
                new Marca(1L, "Nike", "Sporting goods"),
                new Marca(2L, "Adidas", "Sportswear")
        );

        when(marcaEntityMapper.toMarca(any())).thenReturn(marcas.get(0), marcas.get(1));

        PagedResult<Marca> result = marcaAdapter.getMarcasByNombre(sort, pageRequestMarca);

        assertEquals(0, result.getPage());
        assertEquals(5, result.getPageSize());
        assertEquals(1, result.getTotalPages());
        assertEquals(2L, result.getTotalCount());
        assertEquals(marcas, result.getContent());

        verify(marcaRepository, times(1)).findAll(any(PageRequest.class));
        verify(marcaEntityMapper, times(2)).toMarca(any());
    }

    @Test
    void findByNombre_ShouldReturnMarca() {
        MarcaEntity entity = new MarcaEntity(1L, "Nike", "Sporting goods");
        Marca expectedMarca = new Marca(1L, "Nike", "Sporting goods");

        when(marcaRepository.findByNombre("Nike")).thenReturn(Optional.of(entity));
        when(marcaEntityMapper.toMarca(entity)).thenReturn(expectedMarca);

        Optional<Marca> result = marcaAdapter.findByNombre("Nike");

        assertEquals(Optional.of(expectedMarca), result);
        verify(marcaRepository, times(1)).findByNombre("Nike");
        verify(marcaEntityMapper, times(1)).toMarca(entity);
    }

    @Test
    void findByNombre_ShouldReturnEmptyOptionalWhenNotFound() {
        when(marcaRepository.findByNombre("NonExistent")).thenReturn(Optional.empty());

        Optional<Marca> result = marcaAdapter.findByNombre("NonExistent");

        assertEquals(Optional.empty(), result);
        verify(marcaRepository, times(1)).findByNombre("NonExistent");
        verify(marcaEntityMapper, never()).toMarca(any());
    }
}
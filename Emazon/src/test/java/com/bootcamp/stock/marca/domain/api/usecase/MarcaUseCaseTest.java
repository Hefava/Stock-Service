package com.bootcamp.stock.marca.domain.api.usecase;

import com.bootcamp.stock.domain.api.usecase.MarcaUseCase;
import com.bootcamp.stock.domain.utils.PageRequestUtil;
import com.bootcamp.stock.domain.utils.PagedResult;
import com.bootcamp.stock.domain.exception.InvalidMarcaDescriptionLengthException;
import com.bootcamp.stock.domain.exception.InvalidMarcaNameLengthException;
import com.bootcamp.stock.domain.exception.MarcaAlreadyExistsException;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.spi.IMarcaPersistencePort;
import com.bootcamp.stock.domain.utils.MarcaConstants;
import com.bootcamp.stock.domain.utils.SortUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MarcaUseCaseTest {

    @Mock
    private IMarcaPersistencePort marcaPersistencePort;

    @InjectMocks
    private MarcaUseCase marcaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveMarca_WhenMarcaAlreadyExists_ShouldThrowMarcaAlreadyExistsException() {
        Marca marca = new Marca(null, "Nike", "Ropa deportiva");
        when(marcaPersistencePort.findByNombre(marca.getNombre())).thenReturn(Optional.of(marca));

        assertThrows(MarcaAlreadyExistsException.class, () -> marcaUseCase.saveMarca(marca));
        verify(marcaPersistencePort, never()).saveMarca(any(Marca.class));
    }

    @Test
    void saveMarca_WhenMarcaNameTooLong_ShouldThrowInvalidMarcaNameLengthException() {
        String longName = "A".repeat(MarcaConstants.MAX_NOMBRE_LENGTH.getValue() + 1);
        Marca marca = new Marca(null, longName, "Ropa deportiva");

        assertThrows(InvalidMarcaNameLengthException.class, () -> marcaUseCase.saveMarca(marca));
        verify(marcaPersistencePort, never()).saveMarca(any(Marca.class));
    }

    @Test
    void saveMarca_WhenMarcaDescriptionTooLong_ShouldThrowInvalidMarcaDescriptionLengthException() {
        String longDescription = "B".repeat(MarcaConstants.MAX_DESCRIPCION_LENGTH.getValue() + 1);
        Marca marca = new Marca(null, "Nike", longDescription);

        assertThrows(InvalidMarcaDescriptionLengthException.class, () -> marcaUseCase.saveMarca(marca));
        verify(marcaPersistencePort, never()).saveMarca(any(Marca.class));
    }

    @Test
    void saveMarca_WhenValidMarca_ShouldSaveMarcaSuccessfully() {
        Marca marca = new Marca(null, "Nike", "Ropa deportiva");
        when(marcaPersistencePort.findByNombre(marca.getNombre())).thenReturn(Optional.empty());

        marcaUseCase.saveMarca(marca);
        verify(marcaPersistencePort).saveMarca(marca);
    }

    @Test
    void getMarcasByNombre_ShouldReturnSortedMarcas() {
        List<Marca> marcas = List.of(
                new Marca(1L, "Adidas", "Ropa deportiva"),
                new Marca(2L, "Puma", "Ropa deportiva"),
                new Marca(3L, "Nike", "Ropa deportiva")
        );

        SortUtil sort = new SortUtil("nombre", SortUtil.Direction.ASC);
        PageRequestUtil pageRequest = new PageRequestUtil(0, 3);

        when(marcaPersistencePort.getMarcasByNombre(sort, pageRequest)).thenReturn(new PagedResult<>(marcas, 0, 3, 1, 3));

        PagedResult<Marca> result = marcaUseCase.getMarcasByNombre(sort, pageRequest);

        assertEquals(3, result.getContent().size());
        assertEquals("Adidas", result.getContent().get(0).getNombre());
        verify(marcaPersistencePort).getMarcasByNombre(sort, pageRequest);
    }
}
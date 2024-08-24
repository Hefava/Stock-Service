package com.bootcampPragma.Stock.Marca.domain.api.usecase;

import com.bootcampPragma.Stock.Marca.domain.exception.InvalidMarcaDescriptionLengthException;
import com.bootcampPragma.Stock.Marca.domain.exception.InvalidMarcaNameLengthException;
import com.bootcampPragma.Stock.Marca.domain.exception.MarcaAlreadyExistsException;
import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.domain.spi.IMarcaPersistencePort;
import com.bootcampPragma.Stock.Marca.domain.utils.MarcaConstants;
import com.bootcampPragma.Stock.Marca.domain.utils.PageRequestMarca;
import com.bootcampPragma.Stock.Marca.domain.utils.SortMarca;
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
        verify(marcaPersistencePort, times(0)).saveMarca(any(Marca.class));
    }

    @Test
    void saveMarca_WhenMarcaNameTooLong_ShouldThrowInvalidMarcaNameLengthException() {
        String longName = "A".repeat(MarcaConstants.MAX_NOMBRE_LENGTH.getValue() + 1);
        Marca marca = new Marca(null, longName, "Ropa deportiva");

        assertThrows(InvalidMarcaNameLengthException.class, () -> marcaUseCase.saveMarca(marca));
        verify(marcaPersistencePort, times(0)).saveMarca(any(Marca.class));
    }

    @Test
    void saveMarca_WhenMarcaDescriptionTooLong_ShouldThrowInvalidMarcaDescriptionLengthException() {
        String longDescription = "B".repeat(MarcaConstants.MAX_DESCRIPCION_LENGTH.getValue() + 1);
        Marca marca = new Marca(null, "Nike", longDescription);

        assertThrows(InvalidMarcaDescriptionLengthException.class, () -> marcaUseCase.saveMarca(marca));
        verify(marcaPersistencePort, times(0)).saveMarca(any(Marca.class));
    }

    @Test
    void saveMarca_WhenValidMarca_ShouldSaveMarcaSuccessfully() {
        Marca marca = new Marca(null, "Nike", "Ropa deportiva");
        when(marcaPersistencePort.findByNombre(marca.getNombre())).thenReturn(Optional.empty());

        marcaUseCase.saveMarca(marca);
        verify(marcaPersistencePort, times(1)).saveMarca(marca);
    }

    @Test
    void getMarcasByNombre_ShouldReturnSortedMarcas() {
        List<Marca> marcas = List.of(
                new Marca(1L, "Adidas", "Ropa deportiva"),
                new Marca(2L, "Puma", "Ropa deportiva"),
                new Marca(3L, "Nike", "Ropa deportiva")
        );

        SortMarca sort = new SortMarca("nombre", SortMarca.Direction.ASC);
        PageRequestMarca pageRequest = new PageRequestMarca(0, 3);

        when(marcaPersistencePort.getMarcasByNombre(sort, pageRequest)).thenReturn(marcas);

        List<Marca> result = marcaUseCase.getMarcasByNombre(sort, pageRequest);

        assertEquals(3, result.size());
        assertEquals("Adidas", result.get(0).getNombre());
        verify(marcaPersistencePort, times(1)).getMarcasByNombre(sort, pageRequest);
    }
}
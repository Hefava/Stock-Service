package com.bootcampPragma.Stock.Marca.domain.api.usecase;

import com.bootcampPragma.Stock.Marca.domain.exception.InvalidMarcaDescriptionLengthException;
import com.bootcampPragma.Stock.Marca.domain.exception.InvalidMarcaNameLengthException;
import com.bootcampPragma.Stock.Marca.domain.exception.MarcaAlreadyExistsException;
import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.domain.spi.IMarcaPersistencePort;
import com.bootcampPragma.Stock.Marca.domain.utils.MarcaConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        // Arrange
        String longName = "A".repeat(MarcaConstants.MAX_NOMBRE_LENGTH.getValue() + 1); // 51 caracteres
        Marca marca = new Marca(null, longName, "Ropa deportiva");

        assertThrows(InvalidMarcaNameLengthException.class, () -> marcaUseCase.saveMarca(marca));
        verify(marcaPersistencePort, times(0)).saveMarca(any(Marca.class));
    }

    @Test
    void saveMarca_WhenMarcaDescriptionTooLong_ShouldThrowInvalidMarcaDescriptionLengthException() {
        String longDescription = "B".repeat(MarcaConstants.MAX_DESCRIPCION_LENGTH.getValue() + 1); // 121 caracteres
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
}

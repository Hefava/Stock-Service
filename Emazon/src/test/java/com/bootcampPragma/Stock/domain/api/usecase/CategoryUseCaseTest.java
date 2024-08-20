package com.bootcampPragma.Stock.domain.api.usecase;

import com.bootcampPragma.Stock.domain.api.ICategoryServicePort;
import com.bootcampPragma.Stock.domain.exception.CategoryAlreadyExistsException;
import com.bootcampPragma.Stock.domain.exception.InvalidCategoryDescriptionLengthException;
import com.bootcampPragma.Stock.domain.exception.InvalidCategoryNameLengthException;
import com.bootcampPragma.Stock.domain.model.Category;
import com.bootcampPragma.Stock.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowExceptionWhenCategoryNameAlreadyExists() {
        // Arrange
        Category category = new Category(null, "Electrónica", "Productos electrónicos");
        when(categoryPersistencePort.findByNombre("Electrónica")).thenReturn(Optional.of(category));

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void shouldThrowExceptionWhenCategoryNameExceedsMaxLength() {
        // Arrange
        String longName = "Nombre de categoría que excede cincuenta caracteres permitido por la regla de negocio";
        Category category = new Category(null, longName, "Descripción válida");

        // Act & Assert
        assertThrows(InvalidCategoryNameLengthException.class, () -> categoryUseCase.saveCategory(category));
    }

    @Test
    void shouldThrowExceptionWhenCategoryDescriptionExceedsMaxLength() {
        // Arrange
        String longDescription = "Descripción de categoría que excede noventa caracteres permitido por la regla de negocio.";
        Category category = new Category(null, "Electrónica", longDescription);

        // Act & Assert
        assertThrows(InvalidCategoryDescriptionLengthException.class, () -> categoryUseCase.saveCategory(category));
    }
}
package com.bootcampPragma.Stock.Categoria.domain.api.usecase;

import com.bootcampPragma.Stock.Categoria.domain.exception.CategoryAlreadyExistsException;
import com.bootcampPragma.Stock.Categoria.domain.exception.InvalidCategoryDescriptionLengthException;
import com.bootcampPragma.Stock.Categoria.domain.exception.InvalidCategoryNameLengthException;
import com.bootcampPragma.Stock.Categoria.domain.model.Category;
import com.bootcampPragma.Stock.Categoria.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void saveCategory_WhenCategoryAlreadyExists_ShouldThrowCategoryAlreadyExistsException() {
        // Arrange
        Category category = new Category(null, "Electrónica", "Artículos electrónicos");
        when(categoryPersistencePort.findByNombre(category.getNombre())).thenReturn(Optional.of(category));

        // Act & Assert
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));
        verify(categoryPersistencePort, times(0)).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_WhenCategoryNameTooLong_ShouldThrowInvalidCategoryNameLengthException() {
        // Arrange
        String longName = "A".repeat(51);
        Category category = new Category(null, longName, "Artículos electrónicos");

        assertThrows(InvalidCategoryNameLengthException.class, () -> categoryUseCase.saveCategory(category));
        verify(categoryPersistencePort, times(0)).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_WhenCategoryDescriptionTooLong_ShouldThrowInvalidCategoryDescriptionLengthException() {
        String longDescription = "B".repeat(91);
        Category category = new Category(null, "Electrónica", longDescription);

        assertThrows(InvalidCategoryDescriptionLengthException.class, () -> categoryUseCase.saveCategory(category));
        verify(categoryPersistencePort, times(0)).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_WhenValidCategory_ShouldSaveCategorySuccessfully() {
        Category category = new Category(null, "Electrónica", "Artículos electrónicos");
        when(categoryPersistencePort.findByNombre(category.getNombre())).thenReturn(Optional.empty());

        categoryUseCase.saveCategory(category);

        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }
}
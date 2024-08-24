package com.bootcamp.stock.categoria.domain.api.usecase;

import com.bootcamp.stock.categoria.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp.stock.categoria.domain.exception.InvalidCategoryDescriptionLengthException;
import com.bootcamp.stock.categoria.domain.exception.InvalidCategoryNameLengthException;
import com.bootcamp.stock.categoria.domain.model.Category;
import com.bootcamp.stock.categoria.domain.spi.ICategoryPersistencePort;
import com.bootcamp.stock.categoria.domain.utils.PageRequestCategory;
import com.bootcamp.stock.categoria.domain.utils.SortCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
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
        Category category = new Category(null, "Electrónica", "Artículos electrónicos");
        when(categoryPersistencePort.findByNombre(category.getNombre())).thenReturn(Optional.of(category));

        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));
        verify(categoryPersistencePort, times(0)).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_WhenCategoryNameTooLong_ShouldThrowInvalidCategoryNameLengthException() {
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

    @Test
    void getCategoriesByNombre_ShouldReturnSortedCategories() {
        Category category1 = new Category(1L, "Electrónica", "Artículos electrónicos");
        Category category2 = new Category(2L, "Libros", "Diferentes géneros de libros");
        List<Category> expectedCategories = Arrays.asList(category1, category2);

        SortCategory sort = new SortCategory("nombre", SortCategory.Direction.ASC);
        PageRequestCategory pageRequest = new PageRequestCategory(0, 10);

        when(categoryPersistencePort.getCategoriesByNombre(sort, pageRequest)).thenReturn(expectedCategories);

        List<Category> result = categoryUseCase.getCategoriesByNombre(sort, pageRequest);

        assertEquals(expectedCategories, result);
        verify(categoryPersistencePort, times(1)).getCategoriesByNombre(sort, pageRequest);
    }
}
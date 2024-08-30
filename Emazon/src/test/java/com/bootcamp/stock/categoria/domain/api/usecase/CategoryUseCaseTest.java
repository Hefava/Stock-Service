package com.bootcamp.stock.categoria.domain.api.usecase;

import com.bootcamp.stock.domain.api.usecase.CategoryUseCase;
import com.bootcamp.stock.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp.stock.domain.exception.InvalidCategoryDescriptionLengthException;
import com.bootcamp.stock.domain.exception.InvalidCategoryNameLengthException;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.spi.ICategoryPersistencePort;
import com.bootcamp.stock.domain.utils.constants.CategoriaConstants;
import com.bootcamp.stock.domain.utils.Pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.Pagination.PagedResult;
import com.bootcamp.stock.domain.utils.Pagination.SortUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_WhenCategoryNameTooLong_ShouldThrowInvalidCategoryNameLengthException() {
        String longName = "A".repeat(CategoriaConstants.MAX_NOMBRE_LENGTH.getValue() + 1);
        Category category = new Category(null, longName, "Artículos electrónicos");

        assertThrows(InvalidCategoryNameLengthException.class, () -> categoryUseCase.saveCategory(category));
        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_WhenCategoryDescriptionTooLong_ShouldThrowInvalidCategoryDescriptionLengthException() {
        String longDescription = "B".repeat(CategoriaConstants.MAX_DESCRIPCION_LENGTH.getValue() + 1);
        Category category = new Category(null, "Electrónica", longDescription);

        assertThrows(InvalidCategoryDescriptionLengthException.class, () -> categoryUseCase.saveCategory(category));
        verify(categoryPersistencePort, never()).saveCategory(any(Category.class));
    }

    @Test
    void saveCategory_WhenValidCategory_ShouldSaveCategorySuccessfully() {
        Category category = new Category(null, "Electrónica", "Artículos electrónicos");
        when(categoryPersistencePort.findByNombre(category.getNombre())).thenReturn(Optional.empty());

        categoryUseCase.saveCategory(category);
        verify(categoryPersistencePort).saveCategory(category);
    }

    @Test
    void getCategoriesByNombre_ShouldReturnSortedCategories() {
        Category category1 = new Category(1L, "Electrónica", "Artículos electrónicos");
        Category category2 = new Category(2L, "Libros", "Diferentes géneros de libros");
        List<Category> expectedCategories = Arrays.asList(category1, category2);

        SortUtil sort = new SortUtil("nombre", SortUtil.Direction.ASC);
        PageRequestUtil pageRequest = new PageRequestUtil(0, 10);

        PagedResult<Category> pagedResult = new PagedResult<>(expectedCategories, 2, 0, 10, 2);

        when(categoryPersistencePort.getCategoriesByNombre(sort, pageRequest)).thenReturn(pagedResult);

        PagedResult<Category> result = categoryUseCase.getCategoriesByNombre(sort, pageRequest);

        assertEquals(pagedResult, result);
        verify(categoryPersistencePort).getCategoriesByNombre(sort, pageRequest);
    }
}
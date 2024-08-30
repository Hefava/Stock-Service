package com.bootcamp.stock.articulo.domain.api.usecase;

import com.bootcamp.stock.domain.exception.categoryCantBeRepeatedException;
import com.bootcamp.stock.domain.exception.invalidCategoryCountException;
import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.spi.IArticuloPersistencePort;
import com.bootcamp.stock.domain.utils.Pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.Pagination.SortUtil;
import com.bootcamp.stock.domain.api.usecase.ArticuloUseCase;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.utils.Pagination.PagedResult;
import com.bootcamp.stock.domain.model.Marca;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticuloUseCaseTest {

    @Mock
    private IArticuloPersistencePort articuloPersistencePort;

    @InjectMocks
    private ArticuloUseCase articuloUseCase;

    @Test
    void saveArticulo_ShouldThrowInvalidCategoryCountException_WhenNoCategories() {
        Articulo articulo = new Articulo(1L, "Laptop", "Gaming Laptop", 5L, 1200.0, Set.of(), new Marca(1L, "MarcaX", "Descripción de MarcaX"));

        assertThrows(invalidCategoryCountException.class, () -> articuloUseCase.saveArticulo(articulo));
    }

    @Test
    void saveArticulo_ShouldThrowInvalidCategoryCountException_WhenMoreThanThreeCategories() {
        Set<Category> categorias = Set.of(
                new Category(1L, "Electrónica", "Descripción"),
                new Category(2L, "Gaming", "Descripción"),
                new Category(3L, "Computadoras", "Descripción"),
                new Category(4L, "Portátiles", "Descripción")
        );
        Articulo articulo = new Articulo(1L, "Laptop", "Gaming Laptop", 5L, 1200.0, categorias, new Marca(1L, "MarcaX", "Descripción de MarcaX"));

        assertThrows(invalidCategoryCountException.class, () -> articuloUseCase.saveArticulo(articulo));
    }

    @Test
    void saveArticulo_ShouldThrowCategoryCantBeRepeatedException_WhenCategoriesAreRepeated() {
        Set<Category> categorias = Set.of(
                new Category(1L, "Electrónica", "Descripción"),
                new Category(1L, "Electrónica", "Descripción")
        );
        Articulo articulo = new Articulo(1L, "Laptop", "Gaming Laptop", 5L, 1200.0, categorias, new Marca(1L, "MarcaX", "Descripción de MarcaX"));

        assertThrows(categoryCantBeRepeatedException.class, () -> articuloUseCase.saveArticulo(articulo));
    }

    @Test
    void saveArticulo_ShouldSaveArticulo_WhenValidCategories() {
        Set<Category> categorias = Set.of(
                new Category(1L, "Electrónica", "Descripción"),
                new Category(2L, "Gaming", "Descripción")
        );
        Articulo articulo = new Articulo(1L, "Laptop", "Gaming Laptop", 5L, 1200.0, categorias, new Marca(1L, "MarcaX", "Descripción de MarcaX"));

        articuloUseCase.saveArticulo(articulo);

        verify(articuloPersistencePort, times(1)).saveArticulo(articulo);
    }

    @Test
    void getArticulos_ShouldReturnPagedResult() {
        SortUtil sort = mock(SortUtil.class);
        PageRequestUtil pageRequest = mock(PageRequestUtil.class);
        PagedResult<Articulo> expectedPagedResult = mock(PagedResult.class);

        when(articuloPersistencePort.getArticulos(sort, pageRequest)).thenReturn(expectedPagedResult);

        PagedResult<Articulo> actualPagedResult = articuloUseCase.getArticulos(sort, pageRequest);

        verify(articuloPersistencePort, times(1)).getArticulos(sort, pageRequest);
        assertSame(expectedPagedResult, actualPagedResult);
    }

    @Test
    void findAllOrderByCategoriaNombre_ShouldReturnPagedResult() {
        SortUtil sort = mock(SortUtil.class);
        PageRequestUtil pageRequest = mock(PageRequestUtil.class);
        PagedResult<Articulo> expectedPagedResult = mock(PagedResult.class);

        when(articuloPersistencePort.findAllOrderByCategoriaNombre(sort, pageRequest)).thenReturn(expectedPagedResult);

        PagedResult<Articulo> actualPagedResult = articuloUseCase.findAllOrderByCategoriaNombre(sort, pageRequest);

        verify(articuloPersistencePort, times(1)).findAllOrderByCategoriaNombre(sort, pageRequest);
        assertSame(expectedPagedResult, actualPagedResult);
    }
}
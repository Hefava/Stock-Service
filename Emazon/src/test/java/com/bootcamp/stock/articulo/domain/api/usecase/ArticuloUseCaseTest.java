package com.bootcamp.stock.articulo.domain.api.usecase;

import com.bootcamp.stock.domain.exception.ArticuloNotFoundException;
import com.bootcamp.stock.domain.exception.categoryCantBeRepeatedException;
import com.bootcamp.stock.domain.exception.invalidCategoryCountException;
import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.spi.IArticuloPersistencePort;
import com.bootcamp.stock.domain.api.usecase.ArticuloUseCase;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
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
    void agregarSuministro_ShouldThrowArticuloNotFoundException_WhenArticuloNotFound() {
        Long articuloId = 1L;
        Long cantidad = 10L;

        when(articuloPersistencePort.findById(articuloId)).thenReturn(Optional.empty());

        assertThrows(ArticuloNotFoundException.class, () -> articuloUseCase.agregarSuministro(articuloId, cantidad));

        verify(articuloPersistencePort, times(1)).findById(articuloId);
    }

    @Test
    void agregarSuministro_ShouldUpdateCantidad_WhenArticuloExists() {
        Long articuloId = 1L;
        Long cantidad = 10L;
        Articulo articulo = new Articulo(articuloId, "Laptop", "Gaming Laptop", 5L, 1200.0, Set.of(new Category(1L, "Electrónica", "Descripción")), new Marca(1L, "MarcaX", "Descripción de MarcaX"));

        when(articuloPersistencePort.findById(articuloId)).thenReturn(Optional.of(articulo));

        articuloUseCase.agregarSuministro(articuloId, cantidad);

        assertEquals(15L, articulo.getCantidad());
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
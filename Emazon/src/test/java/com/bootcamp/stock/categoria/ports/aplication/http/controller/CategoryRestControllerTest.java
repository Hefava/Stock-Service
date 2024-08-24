package com.bootcamp.stock.categoria.ports.aplication.http.controller;

import com.bootcamp.stock.categoria.domain.api.ICategoryServicePort;
import com.bootcamp.stock.categoria.domain.model.Category;
import com.bootcamp.stock.categoria.domain.utils.PageRequestCategory;
import com.bootcamp.stock.categoria.domain.utils.SortCategory;
import com.bootcamp.stock.categoria.ports.aplication.http.dto.CategoryRequest;
import com.bootcamp.stock.categoria.ports.aplication.http.dto.CategoryResponse;
import com.bootcamp.stock.categoria.ports.aplication.http.mapper.CategoryResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryRestControllerTest {

    @Mock
    private ICategoryServicePort categoryService;

    @Mock
    private CategoryResponseMapper categoryResponseMapper;

    @InjectMocks
    private CategoryRestController categoryRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_ShouldReturnCreatedStatus() {
        CategoryRequest categoryRequest = new CategoryRequest("Electrónica", "Artículos electrónicos");

        ResponseEntity<Void> response = categoryRestController.saveCategory(categoryRequest);

        verify(categoryService, times(1)).saveCategory(any(Category.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getCategoriesByNombre_ShouldReturnSortedCategories() {
        Pageable pageable = PageRequest.of(0, 5);

        List<Category> categories = List.of(
                new Category(1L, "Electrónica", "Artículos electrónicos"),
                new Category(2L, "Ropa", "Artículos de vestir"),
                new Category(3L, "Juguetes", "Juguetes para niños")
        );

        List<CategoryResponse> categoryResponses = List.of(
                new CategoryResponse(1L, "Electrónica", "Artículos electrónicos"),
                new CategoryResponse(2L, "Ropa", "Artículos de vestir"),
                new CategoryResponse(3L, "Juguetes", "Juguetes para niños")
        );

        when(categoryService.getCategoriesByNombre(any(SortCategory.class), any(PageRequestCategory.class))).thenReturn(categories);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponses);

        ResponseEntity<List<CategoryResponse>> response = categoryRestController.getCategoriesByNombre("asc", pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryResponses, response.getBody());
        verify(categoryService, times(1)).getCategoriesByNombre(any(SortCategory.class), any(PageRequestCategory.class));
        verify(categoryResponseMapper, times(1)).toResponseList(categories);
    }

    @Test
    void getCategoriesByNombre_ShouldUseDescendingOrderWhenSpecified() {
        Pageable pageable = PageRequest.of(0, 5);

        List<Category> categories = List.of(
                new Category(3L, "Juguetes", "Juguetes para niños"),
                new Category(2L, "Ropa", "Artículos de vestir"),
                new Category(1L, "Electrónica", "Artículos electrónicos")
        );

        List<CategoryResponse> categoryResponses = List.of(
                new CategoryResponse(3L, "Juguetes", "Juguetes para niños"),
                new CategoryResponse(2L, "Ropa", "Artículos de vestir"),
                new CategoryResponse(1L, "Electrónica", "Artículos electrónicos")
        );

        when(categoryService.getCategoriesByNombre(any(SortCategory.class), any(PageRequestCategory.class))).thenReturn(categories);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponses);

        ResponseEntity<List<CategoryResponse>> response = categoryRestController.getCategoriesByNombre("desc", pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(categoryResponses, response.getBody());
        verify(categoryService, times(1)).getCategoriesByNombre(any(SortCategory.class), any(PageRequestCategory.class));
        verify(categoryResponseMapper, times(1)).toResponseList(categories);
    }
}

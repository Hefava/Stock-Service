package com.bootcamp.stock.categoria.ports.aplication.http.controller;

import com.bootcamp.stock.domain.api.ICategoryServicePort;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;
import com.bootcamp.stock.ports.aplication.http.controller.CategoryRestController;
import com.bootcamp.stock.ports.aplication.http.dto.CategoryRequest;
import com.bootcamp.stock.ports.aplication.http.dto.CategoryResponse;
import com.bootcamp.stock.ports.aplication.http.mapper.CategoryResponseMapper;
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
    void getCategoriesByNombre_ShouldReturnPagedCategories() {
        Pageable pageable = PageRequest.of(0, 5);

        Category category1 = new Category(1L, "Electrónica", "Artículos electrónicos");
        Category category2 = new Category(2L, "Ropa", "Artículos de vestir");
        Category category3 = new Category(3L, "Juguetes", "Juguetes para niños");

        List<Category> categories = List.of(category1, category2, category3);

        List<CategoryResponse> categoryResponses = List.of(
                new CategoryResponse(1L, "Electrónica", "Artículos electrónicos"),
                new CategoryResponse(2L, "Ropa", "Artículos de vestir"),
                new CategoryResponse(3L, "Juguetes", "Juguetes para niños")
        );

        PagedResult<Category> pagedResult = new PagedResult<>(categories, 0, 5, 1, 3);
        PagedResult<CategoryResponse> expectedPagedResult = new PagedResult<>(
                categoryResponses, 0, 5, 1, 3);

        when(categoryService.getCategoriesByNombre(any(SortUtil.class), any(PageRequestUtil.class)))
                .thenReturn(pagedResult);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponses);

        ResponseEntity<PagedResult<CategoryResponse>> response = categoryRestController.getCategoriesByNombre("asc", pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPagedResult, response.getBody());
        verify(categoryService, times(1)).getCategoriesByNombre(any(SortUtil.class), any(PageRequestUtil.class));
        verify(categoryResponseMapper, times(1)).toResponseList(categories);
    }

    @Test
    void getCategoriesByNombre_ShouldUseDescendingOrderWhenSpecified() {
        Pageable pageable = PageRequest.of(0, 5);

        Category category1 = new Category(1L, "Electrónica", "Artículos electrónicos");
        Category category2 = new Category(2L, "Ropa", "Artículos de vestir");
        Category category3 = new Category(3L, "Juguetes", "Juguetes para niños");

        List<Category> categories = List.of(category3, category2, category1);

        List<CategoryResponse> categoryResponses = List.of(
                new CategoryResponse(3L, "Juguetes", "Juguetes para niños"),
                new CategoryResponse(2L, "Ropa", "Artículos de vestir"),
                new CategoryResponse(1L, "Electrónica", "Artículos electrónicos")
        );

        PagedResult<Category> pagedResult = new PagedResult<>(categories, 0, 5, 1, 3);
        PagedResult<CategoryResponse> expectedPagedResult = new PagedResult<>(
                categoryResponses, 0, 5, 1, 3);

        when(categoryService.getCategoriesByNombre(any(SortUtil.class), any(PageRequestUtil.class)))
                .thenReturn(pagedResult);
        when(categoryResponseMapper.toResponseList(categories)).thenReturn(categoryResponses);

        ResponseEntity<PagedResult<CategoryResponse>> response = categoryRestController.getCategoriesByNombre("desc", pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedPagedResult, response.getBody());
        verify(categoryService, times(1)).getCategoriesByNombre(any(SortUtil.class), any(PageRequestUtil.class));
        verify(categoryResponseMapper, times(1)).toResponseList(categories);
    }
}
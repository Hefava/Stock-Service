package com.bootcampPragma.Stock.Categoria.ports.aplication.http.controller;

import com.bootcampPragma.Stock.Categoria.domain.api.ICategoryServicePort;
import com.bootcampPragma.Stock.Categoria.domain.model.Category;
import com.bootcampPragma.Stock.Categoria.ports.aplication.http.dto.CategoryRequest;
import com.bootcampPragma.Stock.Categoria.ports.aplication.http.mapper.CategoryResponseMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryRestController.class)
class CategoryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryServicePort categoryService;

    @MockBean
    private CategoryResponseMapper categoryResponseMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategory_ShouldReturnCreatedStatus() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest("Electrónica", "Artículos electrónicos");
        doNothing().when(categoryService).saveCategory(any(Category.class));

        mockMvc.perform(post("/categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    void getCategoriesByNombre_ShouldReturnOkStatus() throws Exception {
        when(categoryService.getCategoriesByNombre(any(), any())).thenReturn(Collections.emptyList());
        when(categoryResponseMapper.toResponseList(any())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/categoria")
                        .param("order", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
package com.bootcampPragma.Stock.ports.aplication.http.controller;

import com.bootcampPragma.Stock.domain.api.ICategoryServicePort;
import com.bootcampPragma.Stock.domain.model.Category;
import com.bootcampPragma.Stock.ports.aplication.http.dto.CategoryRequest;
import com.bootcampPragma.Stock.ports.aplication.http.dto.CategoryResponse;
import com.bootcampPragma.Stock.ports.aplication.http.mapper.category.CategoryResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryServicePort categoryService;
    private final CategoryResponseMapper categoryResponseMapper;

    @PostMapping
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = new Category(null, categoryRequest.getNombre(), categoryRequest.getDescripcion());
        categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategoriesAscByNombre() {
        List<Category> categories = categoryService.getCategoriesAscByNombre();
        List<CategoryResponse> responses = categoryResponseMapper.toResponseList(categories);
        return ResponseEntity.ok(responses);
    }
}
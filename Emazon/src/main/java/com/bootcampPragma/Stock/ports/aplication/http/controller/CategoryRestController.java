package com.bootcampPragma.Stock.ports.aplication.http.controller;

import com.bootcampPragma.Stock.domain.api.ICategoryServicePort;
import com.bootcampPragma.Stock.domain.model.Category;
import com.bootcampPragma.Stock.domain.utils.PageRequestDomain;
import com.bootcampPragma.Stock.domain.utils.SortDomain;
import com.bootcampPragma.Stock.ports.aplication.http.dto.CategoryRequest;
import com.bootcampPragma.Stock.ports.aplication.http.dto.CategoryResponse;
import com.bootcampPragma.Stock.ports.aplication.http.mapper.category.CategoryResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<List<CategoryResponse>> getCategoriesByNombre(
            @RequestParam(defaultValue = "asc") String order,
            @PageableDefault(size = 5) Pageable pageable) {

        SortDomain.Direction direction = order.equalsIgnoreCase("desc") ? SortDomain.Direction.DESC : SortDomain.Direction.ASC;
        SortDomain sortDomain = new SortDomain("nombre", direction);
        PageRequestDomain pageRequestDomain = new PageRequestDomain(pageable.getPageNumber(), pageable.getPageSize());

        List<Category> categories = categoryService.getCategoriesByNombre(sortDomain, pageRequestDomain);
        List<CategoryResponse> responses = categoryResponseMapper.toResponseList(categories);
        return ResponseEntity.ok(responses);
    }
}

package com.bootcamp.stock.categoria.ports.aplication.http.mapper;

import com.bootcamp.stock.categoria.ports.aplication.http.dto.CategoryResponse;
import com.bootcamp.stock.categoria.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categoryList);
}

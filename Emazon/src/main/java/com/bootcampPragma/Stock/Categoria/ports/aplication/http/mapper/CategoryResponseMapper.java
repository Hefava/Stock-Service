package com.bootcampPragma.Stock.Categoria.ports.aplication.http.mapper;

import com.bootcampPragma.Stock.Categoria.ports.aplication.http.dto.CategoryResponse;
import com.bootcampPragma.Stock.Categoria.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categoryList);
}

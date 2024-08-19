package com.bootcampPragma.Stock.ports.aplication.http.mapper.category;

import com.bootcampPragma.Stock.ports.aplication.http.dto.CategoryResponse;
import com.bootcampPragma.Stock.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper {

    CategoryResponse toResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> categoryList);
}

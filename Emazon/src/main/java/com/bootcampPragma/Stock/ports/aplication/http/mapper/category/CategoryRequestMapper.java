package com.bootcampPragma.Stock.ports.aplication.http.mapper.category;

import com.bootcampPragma.Stock.ports.aplication.http.dto.CategoryRequest;
import com.bootcampPragma.Stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {
    @Mapping(target = "categoryID", ignore = true)
    Category toCategory(CategoryRequest categoryRequest);
}
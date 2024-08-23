package com.bootcampPragma.Stock.Categoria.ports.aplication.http.mapper;

import com.bootcampPragma.Stock.Categoria.ports.aplication.http.dto.CategoryRequest;
import com.bootcampPragma.Stock.Categoria.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {
    @Mapping(target = "categoryID", ignore = true)
    Category toCategory(CategoryRequest categoryRequest);
}
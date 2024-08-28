package com.bootcamp.stock.ports.aplication.http.mapper;

import com.bootcamp.stock.ports.aplication.http.dto.CategoryRequest;
import com.bootcamp.stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {
    @Mapping(target = "categoryID", ignore = true)
    Category toCategory(CategoryRequest categoryRequest);
}
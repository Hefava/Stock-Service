package com.bootcamp.stock.categoria.ports.persistency.mysql.mapper;

import com.bootcamp.stock.categoria.domain.model.Category;
import com.bootcamp.stock.categoria.ports.persistency.mysql.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {
    CategoryEntity toEntity(Category category);

    Category toCategory(CategoryEntity entity);

    List<Category> toCategoryList(List<CategoryEntity> categoryEntityList);
}

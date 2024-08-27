package com.bootcamp.stock.categoria.domain.spi;

import com.bootcamp.stock.categoria.domain.model.Category;
import com.bootcamp.stock.categoria.domain.utils.PageRequestCategory;
import com.bootcamp.stock.categoria.domain.utils.PagedResult;
import com.bootcamp.stock.categoria.domain.utils.SortCategory;

import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);

    PagedResult<Category> getCategoriesByNombre(SortCategory sort, PageRequestCategory pageRequest);

    Optional<Category> findByNombre(String nombre);
}

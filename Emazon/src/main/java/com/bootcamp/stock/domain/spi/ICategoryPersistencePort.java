package com.bootcamp.stock.domain.spi;

import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.utils.Pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.Pagination.PagedResult;
import com.bootcamp.stock.domain.utils.Pagination.SortUtil;

import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);

    PagedResult<Category> getCategoriesByNombre(SortUtil sortDomain, PageRequestUtil pageRequestDomain);

    Optional<Category> findByNombre(String nombre);
}

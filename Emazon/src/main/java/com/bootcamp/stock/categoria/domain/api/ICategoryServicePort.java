package com.bootcamp.stock.categoria.domain.api;

import com.bootcamp.stock.categoria.domain.model.Category;
import com.bootcamp.stock.categoria.domain.utils.PageRequestCategory;
import com.bootcamp.stock.categoria.domain.utils.PagedResult;
import com.bootcamp.stock.categoria.domain.utils.SortCategory;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    PagedResult<Category> getCategoriesByNombre(SortCategory sort, PageRequestCategory pageRequest);
}
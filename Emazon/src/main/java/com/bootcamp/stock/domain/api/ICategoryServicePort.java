package com.bootcamp.stock.domain.api;

import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.utils.PageRequestCategory;
import com.bootcamp.stock.domain.utils.PagedResult;
import com.bootcamp.stock.domain.utils.SortCategory;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    PagedResult<Category> getCategoriesByNombre(SortCategory sort, PageRequestCategory pageRequest);
}
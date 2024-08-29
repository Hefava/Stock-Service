package com.bootcamp.stock.domain.api;

import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.utils.PageRequestUtil;
import com.bootcamp.stock.domain.utils.PagedResult;
import com.bootcamp.stock.domain.utils.SortUtil;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    PagedResult<Category> getCategoriesByNombre(SortUtil sort, PageRequestUtil pageRequest);
}
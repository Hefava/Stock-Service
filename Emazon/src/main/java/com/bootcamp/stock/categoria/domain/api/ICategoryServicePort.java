package com.bootcamp.stock.categoria.domain.api;

import com.bootcamp.stock.categoria.domain.model.Category;
import com.bootcamp.stock.categoria.domain.utils.PageRequestCategory;
import com.bootcamp.stock.categoria.domain.utils.SortCategory;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);

    List<Category> getCategoriesByNombre(SortCategory sort, PageRequestCategory pageRequest);
}
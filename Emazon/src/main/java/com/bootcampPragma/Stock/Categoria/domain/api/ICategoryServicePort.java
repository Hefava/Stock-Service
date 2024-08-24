package com.bootcampPragma.Stock.Categoria.domain.api;

import com.bootcampPragma.Stock.Categoria.domain.model.Category;
import com.bootcampPragma.Stock.Categoria.domain.utils.PageRequestCategory;
import com.bootcampPragma.Stock.Categoria.domain.utils.SortCategory;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);

    List<Category> getCategoriesByNombre(SortCategory sort, PageRequestCategory pageRequest);
}
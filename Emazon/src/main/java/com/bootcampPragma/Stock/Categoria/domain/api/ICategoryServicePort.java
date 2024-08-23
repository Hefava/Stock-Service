package com.bootcampPragma.Stock.Categoria.domain.api;

import com.bootcampPragma.Stock.Categoria.domain.model.Category;
import com.bootcampPragma.Stock.Categoria.domain.utils.PageRequestDomain;
import com.bootcampPragma.Stock.Categoria.domain.utils.SortDomain;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);

    List<Category> getCategoriesByNombre(SortDomain sort, PageRequestDomain pageRequest);
}
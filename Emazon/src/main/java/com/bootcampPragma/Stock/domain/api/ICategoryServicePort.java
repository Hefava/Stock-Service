package com.bootcampPragma.Stock.domain.api;

import com.bootcampPragma.Stock.domain.model.Category;
import com.bootcampPragma.Stock.domain.utils.PageRequestDomain;
import com.bootcampPragma.Stock.domain.utils.SortDomain;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);

    List<Category> getCategoriesByNombre(SortDomain sort, PageRequestDomain pageRequest);
}
package com.bootcampPragma.Stock.domain.api;

import com.bootcampPragma.Stock.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(Category category);

    List<Category> getCategoriesAscByNombre();

}
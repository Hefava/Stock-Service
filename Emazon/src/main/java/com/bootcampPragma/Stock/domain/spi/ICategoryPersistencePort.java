package com.bootcampPragma.Stock.domain.spi;

import com.bootcampPragma.Stock.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    List<Category> getCategoriesAscByNombre();
}
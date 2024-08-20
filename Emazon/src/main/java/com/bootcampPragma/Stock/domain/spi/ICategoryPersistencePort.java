package com.bootcampPragma.Stock.domain.spi;

import com.bootcampPragma.Stock.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);

    List<Category> getCategoriesAscByNombre();

    Optional<Category> findByNombre(String nombre);
}
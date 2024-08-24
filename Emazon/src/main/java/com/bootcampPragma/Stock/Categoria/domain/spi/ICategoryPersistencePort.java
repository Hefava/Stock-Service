package com.bootcampPragma.Stock.Categoria.domain.spi;

import com.bootcampPragma.Stock.Categoria.domain.model.Category;
import com.bootcampPragma.Stock.Categoria.domain.utils.PageRequestCategory;
import com.bootcampPragma.Stock.Categoria.domain.utils.SortCategory;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);

    List<Category> getCategoriesByNombre(SortCategory sort, PageRequestCategory pageRequest);

    Optional<Category> findByNombre(String nombre);
}

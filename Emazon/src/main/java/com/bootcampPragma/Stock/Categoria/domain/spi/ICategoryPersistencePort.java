package com.bootcampPragma.Stock.Categoria.domain.spi;

import com.bootcampPragma.Stock.Categoria.domain.model.Category;
import com.bootcampPragma.Stock.Categoria.domain.utils.PageRequestDomain;
import com.bootcampPragma.Stock.Categoria.domain.utils.SortDomain;

import java.util.List;
import java.util.Optional;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);

    List<Category> getCategoriesByNombre(SortDomain sort, PageRequestDomain pageRequest);

    Optional<Category> findByNombre(String nombre);
}

package com.bootcampPragma.Stock.Categoria.domain.api.usecase;

import com.bootcampPragma.Stock.Categoria.domain.api.ICategoryServicePort;
import com.bootcampPragma.Stock.Categoria.domain.exception.CategoryAlreadyExistsException;
import com.bootcampPragma.Stock.Categoria.domain.exception.InvalidCategoryDescriptionLengthException;
import com.bootcampPragma.Stock.Categoria.domain.exception.InvalidCategoryNameLengthException;
import com.bootcampPragma.Stock.Categoria.domain.model.Category;
import com.bootcampPragma.Stock.Categoria.domain.spi.ICategoryPersistencePort;
import com.bootcampPragma.Stock.Categoria.domain.utils.CategoriaConstants;
import com.bootcampPragma.Stock.Categoria.domain.utils.PageRequestCategory;
import com.bootcampPragma.Stock.Categoria.domain.utils.SortCategory;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryPersistencePort.findByNombre(category.getNombre()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        if (category.getNombre().length() > CategoriaConstants.MAX_NOMBRE_LENGTH.getValue()) {
            throw new InvalidCategoryNameLengthException();
        }
        if (category.getDescripcion().length() > CategoriaConstants.MAX_DESCRIPCION_LENGTH.getValue()) {
            throw new InvalidCategoryDescriptionLengthException();
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getCategoriesByNombre(SortCategory sort, PageRequestCategory pageRequest) {
        return categoryPersistencePort.getCategoriesByNombre(sort, pageRequest);
    }
}

package com.bootcampPragma.Stock.domain.api.usecase;

import com.bootcampPragma.Stock.domain.api.ICategoryServicePort;
import com.bootcampPragma.Stock.domain.exception.CategoryAlreadyExistsException;
import com.bootcampPragma.Stock.domain.exception.InvalidCategoryDescriptionLengthException;
import com.bootcampPragma.Stock.domain.exception.InvalidCategoryNameLengthException;
import com.bootcampPragma.Stock.domain.model.Category;
import com.bootcampPragma.Stock.domain.spi.ICategoryPersistencePort;

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
        if (category.getNombre().length() > 50) {
            throw new InvalidCategoryNameLengthException();
        }
        if (category.getDescripcion().length() > 90) {
            throw new InvalidCategoryDescriptionLengthException();
        }
        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getCategoriesAscByNombre() {
        return categoryPersistencePort.getCategoriesAscByNombre();
    }
}
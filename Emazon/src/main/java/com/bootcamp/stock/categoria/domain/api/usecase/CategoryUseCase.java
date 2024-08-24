package com.bootcamp.stock.categoria.domain.api.usecase;

import com.bootcamp.stock.categoria.domain.api.ICategoryServicePort;
import com.bootcamp.stock.categoria.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp.stock.categoria.domain.exception.InvalidCategoryDescriptionLengthException;
import com.bootcamp.stock.categoria.domain.exception.InvalidCategoryNameLengthException;
import com.bootcamp.stock.categoria.domain.model.Category;
import com.bootcamp.stock.categoria.domain.spi.ICategoryPersistencePort;
import com.bootcamp.stock.categoria.domain.utils.CategoriaConstants;
import com.bootcamp.stock.categoria.domain.utils.PageRequestCategory;
import com.bootcamp.stock.categoria.domain.utils.SortCategory;

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

package com.bootcamp.stock.domain.api.usecase;

import com.bootcamp.stock.domain.api.ICategoryServicePort;
import com.bootcamp.stock.domain.exception.CategoryAlreadyExistsException;
import com.bootcamp.stock.domain.exception.InvalidCategoryDescriptionLengthException;
import com.bootcamp.stock.domain.exception.InvalidCategoryNameLengthException;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.spi.ICategoryPersistencePort;
import com.bootcamp.stock.domain.utils.CategoriaConstants;
import com.bootcamp.stock.domain.utils.PageRequestUtil;
import com.bootcamp.stock.domain.utils.PagedResult;
import com.bootcamp.stock.domain.utils.SortUtil;

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
    public PagedResult<Category> getCategoriesByNombre(SortUtil sort, PageRequestUtil pageRequest) {
        return categoryPersistencePort.getCategoriesByNombre(sort, pageRequest);
    }
}
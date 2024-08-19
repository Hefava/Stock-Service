package com.bootcampPragma.Stock.ports.persistency.mysql.adapter;

import com.bootcampPragma.Stock.domain.model.Category;
import com.bootcampPragma.Stock.domain.spi.ICategoryPersistencePort;
import com.bootcampPragma.Stock.domain.exception.CategoryAlreadyExistsException;
import com.bootcampPragma.Stock.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcampPragma.Stock.ports.persistency.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByNombre(category.getNombre()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public List<Category> getCategoriesAscByNombre() {
        return categoryRepository.findAllByOrderByNombreAsc().stream()
                .map(categoryEntityMapper::toCategory)
                .collect(Collectors.toList());
    }
}

package com.bootcampPragma.Stock.ports.persistency.mysql.adapter;

import com.bootcampPragma.Stock.domain.model.Category;
import com.bootcampPragma.Stock.domain.spi.ICategoryPersistencePort;
import com.bootcampPragma.Stock.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcampPragma.Stock.ports.persistency.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public List<Category> getCategoriesAscByNombre() {
        return categoryRepository.findAllByOrderByNombreAsc().stream()
                .map(categoryEntityMapper::toCategory)
                .toList();
    }

    @Override
    public Optional<Category> findByNombre(String nombre) {
        return categoryRepository.findByNombre(nombre)
                .map(categoryEntityMapper::toCategory);
    }
}

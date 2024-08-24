package com.bootcamp.stock.categoria.ports.persistency.mysql.adapter;

import com.bootcamp.stock.categoria.domain.model.Category;
import com.bootcamp.stock.categoria.domain.spi.ICategoryPersistencePort;
import com.bootcamp.stock.categoria.domain.utils.PageRequestCategory;
import com.bootcamp.stock.categoria.domain.utils.SortCategory;
import com.bootcamp.stock.categoria.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcamp.stock.categoria.ports.persistency.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<Category> getCategoriesByNombre(SortCategory sortDomain, PageRequestCategory pageRequestDomain) {
        Sort sort = Sort.by(sortDomain.getProperty());
        if (sortDomain.getDirection() == SortCategory.Direction.DESC) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }
        PageRequest pageRequest = PageRequest.of(pageRequestDomain.getPage(), pageRequestDomain.getSize(), sort);
        return categoryRepository.findAll(pageRequest)
                .map(categoryEntityMapper::toCategory)
                .toList();
    }

    @Override
    public Optional<Category> findByNombre(String nombre) {
        return categoryRepository.findByNombre(nombre)
                .map(categoryEntityMapper::toCategory);
    }
}

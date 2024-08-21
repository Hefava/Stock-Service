package com.bootcampPragma.Stock.ports.persistency.mysql.adapter;

import com.bootcampPragma.Stock.domain.model.Category;
import com.bootcampPragma.Stock.domain.spi.ICategoryPersistencePort;
import com.bootcampPragma.Stock.domain.utils.PageRequestDomain;
import com.bootcampPragma.Stock.domain.utils.SortDomain;
import com.bootcampPragma.Stock.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcampPragma.Stock.ports.persistency.mysql.repository.ICategoryRepository;
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
    public List<Category> getCategoriesByNombre(SortDomain sortDomain, PageRequestDomain pageRequestDomain) {
        Sort sort = Sort.by(sortDomain.getProperty());
        if (sortDomain.getDirection() == SortDomain.Direction.DESC) {
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

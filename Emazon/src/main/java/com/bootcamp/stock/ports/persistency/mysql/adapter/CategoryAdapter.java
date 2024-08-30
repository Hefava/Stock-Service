package com.bootcamp.stock.ports.persistency.mysql.adapter;

import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.spi.ICategoryPersistencePort;
import com.bootcamp.stock.domain.utils.Pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.Pagination.PagedResult;
import com.bootcamp.stock.domain.utils.Pagination.SortUtil;
import com.bootcamp.stock.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.repository.ICategoryRepository;
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
    public PagedResult<Category> getCategoriesByNombre(SortUtil sortDomain, PageRequestUtil pageRequestDomain) {
        Sort.Direction direction = sortDomain.getDirection() == SortUtil.Direction.DESC
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Sort sort = Sort.by(direction, sortDomain.getProperty());
        PageRequest pageRequest = PageRequest.of(
                pageRequestDomain.getPage(),
                pageRequestDomain.getSize(),
                sort
        );

        var page = categoryRepository.findAll(pageRequest);
        List<Category> content = page.getContent().stream()
                .map(categoryEntityMapper::toCategory)
                .toList();

        return new PagedResult<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    public Optional<Category> findByNombre(String nombre) {
        return categoryRepository.findByNombre(nombre)
                .map(categoryEntityMapper::toCategory);
    }
}
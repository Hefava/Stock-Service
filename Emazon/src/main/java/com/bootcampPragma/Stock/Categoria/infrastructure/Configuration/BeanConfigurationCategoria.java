package com.bootcampPragma.Stock.Categoria.infrastructure.Configuration;

import com.bootcampPragma.Stock.Categoria.domain.api.ICategoryServicePort;
import com.bootcampPragma.Stock.Categoria.domain.api.usecase.CategoryUseCase;
import com.bootcampPragma.Stock.Categoria.domain.spi.ICategoryPersistencePort;
import com.bootcampPragma.Stock.Categoria.ports.persistency.mysql.adapter.CategoryAdapter;
import com.bootcampPragma.Stock.Categoria.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcampPragma.Stock.Categoria.ports.persistency.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationCategoria {
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

}

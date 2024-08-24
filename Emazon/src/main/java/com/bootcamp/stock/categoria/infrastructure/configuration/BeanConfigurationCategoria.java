package com.bootcamp.stock.categoria.infrastructure.configuration;

import com.bootcamp.stock.categoria.domain.api.ICategoryServicePort;
import com.bootcamp.stock.categoria.domain.api.usecase.CategoryUseCase;
import com.bootcamp.stock.categoria.domain.spi.ICategoryPersistencePort;
import com.bootcamp.stock.categoria.ports.persistency.mysql.adapter.CategoryAdapter;
import com.bootcamp.stock.categoria.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcamp.stock.categoria.ports.persistency.mysql.repository.ICategoryRepository;
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

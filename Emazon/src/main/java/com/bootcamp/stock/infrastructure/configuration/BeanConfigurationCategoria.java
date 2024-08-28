package com.bootcamp.stock.infrastructure.configuration;

import com.bootcamp.stock.domain.api.ICategoryServicePort;
import com.bootcamp.stock.domain.api.usecase.CategoryUseCase;
import com.bootcamp.stock.domain.spi.ICategoryPersistencePort;
import com.bootcamp.stock.ports.persistency.mysql.adapter.CategoryAdapter;
import com.bootcamp.stock.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.repository.ICategoryRepository;
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

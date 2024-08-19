package com.bootcampPragma.Stock.infrastructure.beanconfiguration;

import com.bootcampPragma.Stock.domain.api.ICategoryServicePort;
import com.bootcampPragma.Stock.domain.spi.ICategoryPersistencePort;
import com.bootcampPragma.Stock.domain.api.usecase.CategoryUseCase;
import com.bootcampPragma.Stock.ports.persistency.mysql.adapter.CategoryAdapter;
import com.bootcampPragma.Stock.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcampPragma.Stock.ports.persistency.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
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

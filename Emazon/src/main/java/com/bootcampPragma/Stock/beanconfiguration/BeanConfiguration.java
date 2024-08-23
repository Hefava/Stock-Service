package com.bootcampPragma.Stock.beanconfiguration;

import com.bootcampPragma.Stock.Categoria.domain.api.ICategoryServicePort;
import com.bootcampPragma.Stock.Categoria.domain.spi.ICategoryPersistencePort;
import com.bootcampPragma.Stock.Categoria.domain.api.usecase.CategoryUseCase;
import com.bootcampPragma.Stock.Categoria.ports.persistency.mysql.adapter.CategoryAdapter;
import com.bootcampPragma.Stock.Categoria.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcampPragma.Stock.Categoria.ports.persistency.mysql.repository.ICategoryRepository;
import com.bootcampPragma.Stock.Marca.domain.api.IMarcaServicePort;
import com.bootcampPragma.Stock.Marca.domain.api.usecase.MarcaUseCase;
import com.bootcampPragma.Stock.Marca.domain.spi.IMarcaPersistencePort;
import com.bootcampPragma.Stock.Marca.ports.aplication.http.mapper.MarcaResponseMapper;
import com.bootcampPragma.Stock.Marca.ports.persistency.mysql.adapter.MarcaAdapter;
import com.bootcampPragma.Stock.Marca.ports.persistency.mysql.mapper.MarcaEntityMapper;
import com.bootcampPragma.Stock.Marca.ports.persistency.mysql.repository.IMarcaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final IMarcaRepository marcaRepository;
    private final MarcaEntityMapper marcaEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

    @Bean
    public IMarcaPersistencePort marcaPersistencePort() {
        return new MarcaAdapter(marcaRepository, marcaEntityMapper);
    }

    @Bean
    public IMarcaServicePort marcaServicePort() {
        return new MarcaUseCase(marcaPersistencePort());
    }

    @Bean
    public MarcaEntityMapper marcaEntityMapper() {
        return Mappers.getMapper(MarcaEntityMapper.class);
    }

    @Bean
    public MarcaResponseMapper marcaResponseMapper() {
        return Mappers.getMapper(MarcaResponseMapper.class);
    }

}

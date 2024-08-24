package com.bootcampPragma.Stock.Marca.infrastructure.Configuration;

import com.bootcampPragma.Stock.Marca.domain.api.IMarcaServicePort;
import com.bootcampPragma.Stock.Marca.domain.api.usecase.MarcaUseCase;
import com.bootcampPragma.Stock.Marca.domain.spi.IMarcaPersistencePort;
import com.bootcampPragma.Stock.Marca.ports.persistency.mysql.adapter.MarcaAdapter;
import com.bootcampPragma.Stock.Marca.ports.persistency.mysql.mapper.MarcaEntityMapper;
import com.bootcampPragma.Stock.Marca.ports.persistency.mysql.repository.IMarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationMarca {
    private final IMarcaRepository marcaRepository;
    private final MarcaEntityMapper marcaEntityMapper;

    @Bean
    public IMarcaPersistencePort marcaPersistencePort() {
        return new MarcaAdapter(marcaRepository, marcaEntityMapper);
    }

    @Bean
    public IMarcaServicePort marcaServicePort() {
        return new MarcaUseCase(marcaPersistencePort());
    }

}

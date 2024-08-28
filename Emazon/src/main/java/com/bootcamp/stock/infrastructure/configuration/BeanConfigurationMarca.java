package com.bootcamp.stock.infrastructure.configuration;

import com.bootcamp.stock.domain.api.IMarcaServicePort;
import com.bootcamp.stock.domain.api.usecase.MarcaUseCase;
import com.bootcamp.stock.domain.spi.IMarcaPersistencePort;
import com.bootcamp.stock.ports.persistency.mysql.adapter.MarcaAdapter;
import com.bootcamp.stock.ports.persistency.mysql.mapper.MarcaEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.repository.IMarcaRepository;
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

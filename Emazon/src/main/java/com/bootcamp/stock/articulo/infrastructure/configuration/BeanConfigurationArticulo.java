package com.bootcamp.stock.articulo.infrastructure.configuration;

import com.bootcamp.stock.articulo.domain.api.IArticuloServicePort;
import com.bootcamp.stock.articulo.domain.api.usecase.ArticuloUseCase;
import com.bootcamp.stock.articulo.domain.spi.IArticuloPersistencePort;
import com.bootcamp.stock.articulo.ports.persistency.mysql.adapter.ArticuloAdapter;
import com.bootcamp.stock.articulo.ports.persistency.mysql.mapper.ArticuloEntityMapper;
import com.bootcamp.stock.articulo.ports.persistency.mysql.repository.IArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationArticulo {

    private final IArticuloRepository articuloRepository;
    private final ArticuloEntityMapper articuloEntityMapper;

    @Bean
    public IArticuloPersistencePort articuloPersistencePort() {
        return new ArticuloAdapter(articuloRepository, articuloEntityMapper);
    }

    @Bean
    public IArticuloServicePort articuloServicePort() {
        return new ArticuloUseCase(articuloPersistencePort());
    }

}
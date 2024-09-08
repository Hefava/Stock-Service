package com.bootcamp.stock.infrastructure.configuration;

import com.bootcamp.stock.domain.api.IArticuloServicePort;
import com.bootcamp.stock.domain.api.ICategoryServicePort;
import com.bootcamp.stock.domain.api.IMarcaServicePort;
import com.bootcamp.stock.domain.api.usecase.ArticuloUseCase;
import com.bootcamp.stock.domain.api.usecase.CategoryUseCase;
import com.bootcamp.stock.domain.api.usecase.MarcaUseCase;
import com.bootcamp.stock.domain.spi.IArticuloPersistencePort;
import com.bootcamp.stock.domain.spi.ICategoryPersistencePort;
import com.bootcamp.stock.domain.spi.IMarcaPersistencePort;
import com.bootcamp.stock.ports.persistency.mysql.adapter.ArticuloAdapter;
import com.bootcamp.stock.ports.persistency.mysql.adapter.CategoryAdapter;
import com.bootcamp.stock.ports.persistency.mysql.adapter.MarcaAdapter;
import com.bootcamp.stock.ports.persistency.mysql.mapper.ArticuloEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.mapper.MarcaEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.repository.IArticuloRepository;
import com.bootcamp.stock.ports.persistency.mysql.repository.ICategoryRepository;
import com.bootcamp.stock.ports.persistency.mysql.repository.IMarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IArticuloRepository articuloRepository;
    private final ArticuloEntityMapper articuloEntityMapper;
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
    public IArticuloPersistencePort articuloPersistencePort() {
        return new ArticuloAdapter(articuloRepository, articuloEntityMapper);
    }

    @Bean
    public IArticuloServicePort articuloServicePort() {
        return new ArticuloUseCase(articuloPersistencePort());
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }
}
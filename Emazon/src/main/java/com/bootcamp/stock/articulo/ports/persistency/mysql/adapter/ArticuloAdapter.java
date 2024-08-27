package com.bootcamp.stock.articulo.ports.persistency.mysql.adapter;

import com.bootcamp.stock.articulo.domain.model.Articulo;
import com.bootcamp.stock.articulo.domain.spi.IArticuloPersistencePort;
import com.bootcamp.stock.articulo.ports.persistency.mysql.mapper.ArticuloEntityMapper;
import com.bootcamp.stock.articulo.ports.persistency.mysql.repository.IArticuloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArticuloAdapter implements IArticuloPersistencePort {
    private final IArticuloRepository articuloRepository;
    private final ArticuloEntityMapper articuloEntityMapper;

    @Override
    public void saveArticulo(Articulo articulo) {
        articuloRepository.save(articuloEntityMapper.toEntity(articulo));
    }

    @Override
    public Optional<Articulo> findByNombre(String nombre) {
        return articuloRepository.findByNombre(nombre)
                .map(articuloEntityMapper::toArticulo);
    }
}
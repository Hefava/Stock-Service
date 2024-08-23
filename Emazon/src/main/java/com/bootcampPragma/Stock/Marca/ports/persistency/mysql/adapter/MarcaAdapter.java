package com.bootcampPragma.Stock.Marca.ports.persistency.mysql.adapter;

import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.domain.spi.IMarcaPersistencePort;
import com.bootcampPragma.Stock.Marca.ports.persistency.mysql.mapper.MarcaEntityMapper;
import com.bootcampPragma.Stock.Marca.ports.persistency.mysql.repository.IMarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MarcaAdapter implements IMarcaPersistencePort {
    private final IMarcaRepository marcaRepository;
    private final MarcaEntityMapper marcaEntityMapper;

    @Override
    public void saveMarca(Marca marca) {
        marcaRepository.save(marcaEntityMapper.toEntity(marca));
    }

    @Override
    public Optional<Marca> findByNombre(String nombre) {
        return marcaRepository.findByNombre(nombre)
                .map(marcaEntityMapper::toMarca);
    }
}
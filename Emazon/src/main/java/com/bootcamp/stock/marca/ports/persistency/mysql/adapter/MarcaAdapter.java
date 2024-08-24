package com.bootcamp.stock.marca.ports.persistency.mysql.adapter;

import com.bootcamp.stock.marca.domain.model.Marca;
import com.bootcamp.stock.marca.domain.spi.IMarcaPersistencePort;
import com.bootcamp.stock.marca.domain.utils.PageRequestMarca;
import com.bootcamp.stock.marca.domain.utils.SortMarca;
import com.bootcamp.stock.marca.ports.persistency.mysql.mapper.MarcaEntityMapper;
import com.bootcamp.stock.marca.ports.persistency.mysql.repository.IMarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public List<Marca> getMarcasByNombre(SortMarca sortMarca, PageRequestMarca pageRequestMarca) {
        Sort sort = Sort.by(sortMarca.getProperty());
        sort = sortMarca.getDirection() == SortMarca.Direction.DESC ? sort.descending() : sort.ascending();
        PageRequest pageRequest = PageRequest.of(pageRequestMarca.getPage(), pageRequestMarca.getSize(), sort);
        return marcaRepository.findAll(pageRequest)
                .map(marcaEntityMapper::toMarca)
                .toList();
    }

    @Override
    public Optional<Marca> findByNombre(String nombre) {
        return marcaRepository.findByNombre(nombre)
                .map(marcaEntityMapper::toMarca);
    }
}
package com.bootcamp.stock.ports.persistency.mysql.adapter;

import com.bootcamp.stock.domain.utils.Pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.Pagination.PagedResult;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.utils.Pagination.SortUtil;
import com.bootcamp.stock.domain.spi.IMarcaPersistencePort;
import com.bootcamp.stock.ports.persistency.mysql.mapper.MarcaEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.repository.IMarcaRepository;
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
    public PagedResult<Marca> getMarcasByNombre(SortUtil sortDomain, PageRequestUtil pageRequestDomain) {
        Sort sort = Sort.by(sortDomain.getProperty());
        if (sortDomain.getDirection() == SortUtil.Direction.DESC) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        org.springframework.data.domain.PageRequest pageRequest = PageRequest.of(
                pageRequestDomain.getPage(),
                pageRequestDomain.getSize(),
                sort
        );

        var page = marcaRepository.findAll(pageRequest);
        List<Marca> content = page.getContent().stream()
                .map(marcaEntityMapper::toMarca)
                .toList();

        return new PagedResult<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    public Optional<Marca> findByNombre(String nombre) {
        return marcaRepository.findByNombre(nombre)
                .map(marcaEntityMapper::toMarca);
    }
}
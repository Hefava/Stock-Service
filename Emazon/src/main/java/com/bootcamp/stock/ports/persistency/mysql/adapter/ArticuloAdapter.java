package com.bootcamp.stock.ports.persistency.mysql.adapter;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.spi.IArticuloPersistencePort;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;
import com.bootcamp.stock.ports.persistency.mysql.entity.ArticuloEntity;
import com.bootcamp.stock.ports.persistency.mysql.mapper.ArticuloEntityMapper;
import com.bootcamp.stock.ports.persistency.mysql.repository.IArticuloRepository;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ArticuloAdapter implements IArticuloPersistencePort {
    private final IArticuloRepository articuloRepository;
    private final ArticuloEntityMapper articuloEntityMapper;

    @Override
    @Transactional
    public void saveArticulo(Articulo articulo) {
        articuloRepository.save(articuloEntityMapper.toEntity(articulo));
    }

    @Override
    public PagedResult<Articulo> getArticulos(SortUtil sortDomain, PageRequestUtil pageRequestDomain) {
        Sort sort = Sort.by(sortDomain.getProperty());
        if (sortDomain.getDirection() == SortUtil.Direction.DESC) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        PageRequest pageRequest = PageRequest.of(
                pageRequestDomain.getPage(),
                pageRequestDomain.getSize(),
                sort
        );

        Page<ArticuloEntity> page = articuloRepository.findAll(pageRequest);
        List<Articulo> content = page.getContent().stream()
                .map(articuloEntityMapper::toArticulo)
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
    public PagedResult<Articulo> findAllOrderByCategoriaNombre(SortUtil sortDomain, PageRequestUtil pageRequestDomain) {
        Sort sort = Sort.by("categorias.nombre").ascending();

        PageRequest pageRequest = PageRequest.of(
                pageRequestDomain.getPage(),
                pageRequestDomain.getSize(),
                sort
        );

        Page<ArticuloEntity> page = articuloRepository.findAllOrderByCategoriaNombre(pageRequest);

        List<Articulo> articulos = page.getContent().stream()
                .map(articuloEntityMapper::toArticulo)
                .distinct()
                .toList();

        return new PagedResult<>(
                articulos,
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    public PagedResult<Articulo> findByIdsAndFilters(List<Long> ids, SortUtil sort, PageRequestUtil pageRequest, String categoriaNombre, String marcaNombre) {
        Sort sortJpa = Sort.by(sort.getProperty());
        if (sort.getDirection() == SortUtil.Direction.DESC) {
            sortJpa = sortJpa.descending();
        } else {
            sortJpa = sortJpa.ascending();
        }

        PageRequest pageRequestJpa = PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), sortJpa);

        Page<ArticuloEntity> page;
        if (categoriaNombre != null && marcaNombre != null) {
            page = articuloRepository.findByArticuloIDInAndCategoriaNombreAndMarcaNombre(ids, categoriaNombre, marcaNombre, pageRequestJpa);
        } else if (categoriaNombre != null) {
            page = articuloRepository.findByArticuloIDInAndCategoriaNombre(ids, categoriaNombre, pageRequestJpa);
        } else if (marcaNombre != null) {
            page = articuloRepository.findByArticuloIDInAndMarcaNombre(ids, marcaNombre, pageRequestJpa);
        } else {
            page = articuloRepository.findAllById(ids, pageRequestJpa);
        }

        List<Articulo> articulos = page.getContent().stream()
                .map(articuloEntityMapper::toArticulo)
                .toList();

        return new PagedResult<>(articulos, page.getNumber(), page.getSize(), page.getTotalPages(), page.getTotalElements());
    }


    @Override
    public Optional<Articulo> findById(Long id) {
        return articuloRepository.findById(id)
                .map(articuloEntityMapper::toArticulo);
    }

    @Override
    public Optional<Articulo> findByNombre(String nombre) {
        return articuloRepository.findByNombre(nombre)
                .map(articuloEntityMapper::toArticulo);
    }
}
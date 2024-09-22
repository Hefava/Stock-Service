package com.bootcamp.stock.domain.api.usecase;

import com.bootcamp.stock.domain.api.IArticuloServicePort;
import com.bootcamp.stock.domain.exception.ArticuloNotFoundException;
import com.bootcamp.stock.domain.exception.categoryCantBeRepeatedException;
import com.bootcamp.stock.domain.exception.invalidCategoryCountException;
import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.spi.IArticuloPersistencePort;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.utils.constants.ArticuloConstants;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;

import java.util.List;

public class ArticuloUseCase implements IArticuloServicePort {

    private final IArticuloPersistencePort articuloPersistencePort;

    public ArticuloUseCase(IArticuloPersistencePort articuloPersistencePort) {
        this.articuloPersistencePort = articuloPersistencePort;
    }

    @Override
    public void saveArticulo(Articulo articulo) {
        if (articulo.getCategorias() == null ||
                articulo.getCategorias().isEmpty() ||
                articulo.getCategorias().size() > ArticuloConstants.MAX_CATEGORIAS) {
            throw new invalidCategoryCountException();
        }

        List<Long> categoriaIdsList = articulo.getCategorias().stream()
                .map(Category::getCategoryID)
                .toList();

        List<Long> categoriaIdsUnicas = categoriaIdsList.stream()
                .distinct()
                .toList();

        if (categoriaIdsList.size() != categoriaIdsUnicas.size()) {
            throw new categoryCantBeRepeatedException();
        }

        articuloPersistencePort.saveArticulo(articulo);
    }

    @Override
    public void agregarSuministro(Long id, Long cantidad) {
        Articulo articulo = articuloPersistencePort.findById(id)
                .orElseThrow(ArticuloNotFoundException::new);

        Long nuevaCantidad = articulo.getCantidad() + cantidad;
        articulo.setCantidad(nuevaCantidad);
        articuloPersistencePort.saveArticulo(articulo);
    }

    @Override
    public Articulo articuloInfo(Long articuloID) {
        return articuloPersistencePort.findById(articuloID)
                .orElseThrow(ArticuloNotFoundException::new);
    }

    @Override
    public PagedResult<Articulo> getArticulos(SortUtil sort, PageRequestUtil pageRequest) {
        return articuloPersistencePort.getArticulos(sort, pageRequest);
    }

    @Override
    public PagedResult<Articulo> getArticulosByIdsAndFilters(
            List<Long> ids,
            String categoriaNombre,
            String marcaNombre,
            SortUtil sort,
            PageRequestUtil pageRequest
    ) {
        return articuloPersistencePort.findByIdsAndFilters(ids, sort, pageRequest, categoriaNombre, marcaNombre);
    }

    @Override
    public PagedResult<Articulo> findAllOrderByCategoriaNombre(SortUtil sort, PageRequestUtil pageRequest) {
        return articuloPersistencePort.findAllOrderByCategoriaNombre(sort, pageRequest);
    }
}
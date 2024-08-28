package com.bootcamp.stock.domain.api.usecase;

import com.bootcamp.stock.domain.api.IArticuloServicePort;
import com.bootcamp.stock.domain.exception.categoryCantBeRepeatedException;
import com.bootcamp.stock.domain.exception.invalidCategoryCountException;
import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.spi.IArticuloPersistencePort;
import com.bootcamp.stock.domain.utils.PageRequestArticulo;
import com.bootcamp.stock.domain.utils.SortArticulo;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.utils.PagedResult;

import java.util.List;

public class ArticuloUseCase implements IArticuloServicePort {

    private final IArticuloPersistencePort articuloPersistencePort;

    public ArticuloUseCase(IArticuloPersistencePort articuloPersistencePort) {
        this.articuloPersistencePort = articuloPersistencePort;
    }

    @Override
    public void saveArticulo(Articulo articulo) {
        if (articulo.getCategorias() == null || articulo.getCategorias().isEmpty() || articulo.getCategorias().size() > 3) {
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
    public PagedResult<Articulo> getArticulos(SortArticulo sort, PageRequestArticulo pageRequest) {
        return articuloPersistencePort.getArticulos(sort, pageRequest);
    }

    @Override
    public PagedResult<Articulo> findAllOrderByCategoriaNombre(SortArticulo sort, PageRequestArticulo pageRequest) {
        return articuloPersistencePort.findAllOrderByCategoriaNombre(sort, pageRequest);
    }
}

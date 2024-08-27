package com.bootcamp.stock.articulo.domain.api.usecase;

import com.bootcamp.stock.articulo.domain.api.IArticuloServicePort;
import com.bootcamp.stock.articulo.domain.exception.categoryCantBeRepeatedException;
import com.bootcamp.stock.articulo.domain.exception.invalidCategoryCountException;
import com.bootcamp.stock.articulo.domain.model.Articulo;
import com.bootcamp.stock.articulo.domain.spi.IArticuloPersistencePort;
import com.bootcamp.stock.categoria.domain.model.Category;

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
}

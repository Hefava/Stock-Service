package com.bootcamp.stock.domain.spi;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.utils.PageRequestArticulo;
import com.bootcamp.stock.domain.utils.SortArticulo;
import com.bootcamp.stock.domain.utils.PagedResult;

import java.util.Optional;

public interface IArticuloPersistencePort {
    void saveArticulo(Articulo articulo);

    PagedResult<Articulo> getArticulos(SortArticulo sort, PageRequestArticulo pageRequest);

    PagedResult<Articulo> findAllOrderByCategoriaNombre(SortArticulo sort, PageRequestArticulo pageRequest);

    Optional<Articulo> findByNombre(String nombre);
}
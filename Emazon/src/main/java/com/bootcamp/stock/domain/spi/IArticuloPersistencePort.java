package com.bootcamp.stock.domain.spi;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.utils.PageRequestUtil;
import com.bootcamp.stock.domain.utils.SortUtil;
import com.bootcamp.stock.domain.utils.PagedResult;

import java.util.Optional;

public interface IArticuloPersistencePort {
    void saveArticulo(Articulo articulo);

    PagedResult<Articulo> getArticulos(SortUtil sort, PageRequestUtil pageRequest);

    PagedResult<Articulo> findAllOrderByCategoriaNombre(SortUtil sort, PageRequestUtil pageRequest);

    Optional<Articulo> findByNombre(String nombre);
}
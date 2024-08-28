package com.bootcamp.stock.articulo.domain.api;

import com.bootcamp.stock.articulo.domain.model.Articulo;
import com.bootcamp.stock.articulo.domain.utils.PageRequestArticulo;
import com.bootcamp.stock.articulo.domain.utils.SortArticulo;
import com.bootcamp.stock.categoria.domain.utils.PagedResult;

public interface IArticuloServicePort {

    void saveArticulo(Articulo articulo);
    PagedResult<Articulo> getArticulos(SortArticulo sort, PageRequestArticulo pageRequest);
    PagedResult<Articulo> findAllOrderByCategoriaNombre(SortArticulo sort, PageRequestArticulo pageRequest);
}
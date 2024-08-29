package com.bootcamp.stock.domain.api;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.utils.PageRequestUtil;
import com.bootcamp.stock.domain.utils.SortUtil;
import com.bootcamp.stock.domain.utils.PagedResult;

public interface IArticuloServicePort {

    void saveArticulo(Articulo articulo);
    PagedResult<Articulo> getArticulos(SortUtil sort, PageRequestUtil pageRequest);
    PagedResult<Articulo> findAllOrderByCategoriaNombre(SortUtil sort, PageRequestUtil pageRequest);
}
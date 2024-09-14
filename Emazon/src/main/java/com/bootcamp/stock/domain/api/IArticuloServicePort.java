package com.bootcamp.stock.domain.api;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;

public interface IArticuloServicePort {

    void saveArticulo(Articulo articulo);
    void agregarSuministro(Long id, Long cantidad);
    PagedResult<Articulo> getArticulos(SortUtil sort, PageRequestUtil pageRequest);
    Articulo articuloInfo(Long articuloID);
    PagedResult<Articulo> findAllOrderByCategoriaNombre(SortUtil sort, PageRequestUtil pageRequest);
}
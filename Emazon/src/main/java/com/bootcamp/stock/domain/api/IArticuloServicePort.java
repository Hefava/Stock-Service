package com.bootcamp.stock.domain.api;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;

import java.util.List;

public interface IArticuloServicePort {

    void saveArticulo(Articulo articulo);
    void agregarSuministro(Long id, Long cantidad);
    void restarExistencia(Long id, Long cantidad);
    PagedResult<Articulo> getArticulos(SortUtil sort, PageRequestUtil pageRequest);
    Articulo articuloInfo(Long articuloID);
    PagedResult<Articulo> findAllOrderByCategoriaNombre(SortUtil sort, PageRequestUtil pageRequest);

    PagedResult<Articulo> getArticulosByIdsAndFilters(
            List<Long> ids,
            String categoriaNombre,
            String marcaNombre,
            SortUtil sort,
            PageRequestUtil pageRequest
    );
}
package com.bootcamp.stock.domain.spi;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;


import java.util.List;
import java.util.Optional;

public interface IArticuloPersistencePort {

    void saveArticulo(Articulo articulo);

    PagedResult<Articulo> getArticulos(SortUtil sort, PageRequestUtil pageRequest);

    PagedResult<Articulo> findByIdsAndFilters(List<Long> ids, SortUtil sort, PageRequestUtil pageRequest, String categoriaNombre, String marcaNombre);

    PagedResult<Articulo> findAllOrderByCategoriaNombre(SortUtil sort, PageRequestUtil pageRequest);

    Optional<Articulo> findById(Long id);

    Optional<Articulo> findByNombre(String nombre);
}
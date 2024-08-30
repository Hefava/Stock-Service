package com.bootcamp.stock.domain.api;

import com.bootcamp.stock.domain.utils.Pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.Pagination.PagedResult;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.utils.Pagination.SortUtil;

public interface IMarcaServicePort {
    void saveMarca(Marca marca);
    PagedResult<Marca> getMarcasByNombre(SortUtil sort, PageRequestUtil pageRequest);
}
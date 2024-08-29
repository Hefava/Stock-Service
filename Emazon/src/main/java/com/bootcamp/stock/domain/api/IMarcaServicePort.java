package com.bootcamp.stock.domain.api;

import com.bootcamp.stock.domain.utils.PageRequestUtil;
import com.bootcamp.stock.domain.utils.PagedResult;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.utils.SortUtil;

public interface IMarcaServicePort {
    void saveMarca(Marca marca);
    PagedResult<Marca> getMarcasByNombre(SortUtil sort, PageRequestUtil pageRequest);
}
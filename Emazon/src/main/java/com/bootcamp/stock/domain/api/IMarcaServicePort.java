package com.bootcamp.stock.domain.api;

import com.bootcamp.stock.domain.utils.PagedResult;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.utils.PageRequestMarca;
import com.bootcamp.stock.domain.utils.SortMarca;

public interface IMarcaServicePort {
    void saveMarca(Marca marca);
    PagedResult<Marca> getMarcasByNombre(SortMarca sort, PageRequestMarca pageRequest);
}
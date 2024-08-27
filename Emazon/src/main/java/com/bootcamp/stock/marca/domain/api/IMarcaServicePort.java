package com.bootcamp.stock.marca.domain.api;

import com.bootcamp.stock.categoria.domain.utils.PagedResult;
import com.bootcamp.stock.marca.domain.model.Marca;
import com.bootcamp.stock.marca.domain.utils.PageRequestMarca;
import com.bootcamp.stock.marca.domain.utils.SortMarca;

public interface IMarcaServicePort {
    void saveMarca(Marca marca);
    PagedResult<Marca> getMarcasByNombre(SortMarca sort, PageRequestMarca pageRequest);
}
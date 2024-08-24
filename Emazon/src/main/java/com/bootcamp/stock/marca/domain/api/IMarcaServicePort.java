package com.bootcamp.stock.marca.domain.api;

import com.bootcamp.stock.marca.domain.model.Marca;
import com.bootcamp.stock.marca.domain.utils.PageRequestMarca;
import com.bootcamp.stock.marca.domain.utils.SortMarca;

import java.util.List;

public interface IMarcaServicePort {
    void saveMarca(Marca marca);
    List<Marca> getMarcasByNombre(SortMarca sort, PageRequestMarca pageRequest);
}
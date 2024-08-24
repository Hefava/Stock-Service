package com.bootcampPragma.Stock.Marca.domain.api;

import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.domain.utils.PageRequestMarca;
import com.bootcampPragma.Stock.Marca.domain.utils.SortMarca;

import java.util.List;

public interface IMarcaServicePort {
    void saveMarca(Marca marca);
    List<Marca> getMarcasByNombre(SortMarca sort, PageRequestMarca pageRequest);
}
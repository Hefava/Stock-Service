package com.bootcamp.stock.domain.spi;

import com.bootcamp.stock.domain.utils.PagedResult;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.utils.PageRequestMarca;
import com.bootcamp.stock.domain.utils.SortMarca;

import java.util.Optional;

public interface IMarcaPersistencePort {
    void saveMarca(Marca marca);
    PagedResult<Marca> getMarcasByNombre(SortMarca sort, PageRequestMarca pageRequest);
    Optional<Marca> findByNombre(String nombre);
}
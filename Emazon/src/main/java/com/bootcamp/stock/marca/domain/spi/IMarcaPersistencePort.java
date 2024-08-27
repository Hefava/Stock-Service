package com.bootcamp.stock.marca.domain.spi;

import com.bootcamp.stock.categoria.domain.utils.PagedResult;
import com.bootcamp.stock.marca.domain.model.Marca;
import com.bootcamp.stock.marca.domain.utils.PageRequestMarca;
import com.bootcamp.stock.marca.domain.utils.SortMarca;

import java.util.Optional;

public interface IMarcaPersistencePort {
    void saveMarca(Marca marca);
    PagedResult<Marca> getMarcasByNombre(SortMarca sort, PageRequestMarca pageRequest);
    Optional<Marca> findByNombre(String nombre);
}
package com.bootcamp.stock.marca.domain.spi;

import com.bootcamp.stock.marca.domain.model.Marca;
import com.bootcamp.stock.marca.domain.utils.PageRequestMarca;
import com.bootcamp.stock.marca.domain.utils.SortMarca;

import java.util.List;
import java.util.Optional;

public interface IMarcaPersistencePort {
    void saveMarca(Marca marca);
    List<Marca> getMarcasByNombre(SortMarca sortMarca, PageRequestMarca pageRequestMarca);
    Optional<Marca> findByNombre(String nombre);
}


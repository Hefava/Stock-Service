package com.bootcampPragma.Stock.Marca.domain.spi;

import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.domain.utils.PageRequestMarca;
import com.bootcampPragma.Stock.Marca.domain.utils.SortMarca;

import java.util.List;
import java.util.Optional;

public interface IMarcaPersistencePort {
    void saveMarca(Marca marca);
    List<Marca> getMarcasByNombre(SortMarca sortMarca, PageRequestMarca pageRequestMarca);
    Optional<Marca> findByNombre(String nombre);
}


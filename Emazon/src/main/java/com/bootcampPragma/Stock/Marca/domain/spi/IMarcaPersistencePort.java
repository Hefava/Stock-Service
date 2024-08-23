package com.bootcampPragma.Stock.Marca.domain.spi;

import com.bootcampPragma.Stock.Marca.domain.model.Marca;

import java.util.Optional;

public interface IMarcaPersistencePort {
    void saveMarca(Marca marca);
    Optional<Marca> findByNombre(String nombre);
}

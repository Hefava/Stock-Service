package com.bootcampPragma.Stock.domain.spi;

import com.bootcampPragma.Stock.domain.model.Marca;

import java.util.Optional;

public interface IMarcaPersistencePort {
    void saveMarca(Marca marca);
    Optional<Marca> findByNombre(String nombre);
}

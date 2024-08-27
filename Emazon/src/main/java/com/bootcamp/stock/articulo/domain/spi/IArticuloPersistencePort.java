package com.bootcamp.stock.articulo.domain.spi;

import com.bootcamp.stock.articulo.domain.model.Articulo;
import java.util.Optional;

public interface IArticuloPersistencePort {
    void saveArticulo(Articulo articulo);

    Optional<Articulo> findByNombre(String nombre);
}

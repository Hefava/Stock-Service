package com.bootcamp.stock.domain.spi;

import com.bootcamp.stock.domain.utils.Pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.Pagination.PagedResult;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.utils.Pagination.SortUtil;
import java.util.Optional;

public interface IMarcaPersistencePort {
    void saveMarca(Marca marca);
    PagedResult<Marca> getMarcasByNombre(SortUtil sort, PageRequestUtil pageRequest);
    Optional<Marca> findByNombre(String nombre);
}
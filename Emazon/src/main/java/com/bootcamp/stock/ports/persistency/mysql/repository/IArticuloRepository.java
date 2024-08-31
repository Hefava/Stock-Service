package com.bootcamp.stock.ports.persistency.mysql.repository;

import com.bootcamp.stock.domain.utils.ArticuloQueries;
import com.bootcamp.stock.ports.persistency.mysql.entity.ArticuloEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IArticuloRepository extends JpaRepository<ArticuloEntity, Long> {
    Optional<ArticuloEntity> findByNombre(String nombre);

    @Query(ArticuloQueries.FIND_ALL_ORDER_BY_CATEGORIA_NOMBRE)
    Page<ArticuloEntity> findAllOrderByCategoriaNombre(Pageable pageable);
}
package com.bootcamp.stock.ports.persistency.mysql.repository;

import com.bootcamp.stock.ports.persistency.mysql.entity.ArticuloEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IArticuloRepository extends JpaRepository<ArticuloEntity, Long> {
    Optional<ArticuloEntity> findByNombre(String nombre);

    @Query("SELECT a FROM ArticuloEntity a JOIN a.categorias c ORDER BY c.nombre ASC")
    Page<ArticuloEntity> findAllOrderByCategoriaNombre(Pageable pageable);
}
package com.bootcamp.stock.articulo.ports.persistency.mysql.repository;

import com.bootcamp.stock.articulo.ports.persistency.mysql.entity.ArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IArticuloRepository extends JpaRepository<ArticuloEntity, Long> {
    Optional<ArticuloEntity> findByNombre(String nombre);
}

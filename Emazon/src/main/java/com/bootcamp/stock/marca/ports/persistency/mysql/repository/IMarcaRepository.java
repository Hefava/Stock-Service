package com.bootcamp.stock.marca.ports.persistency.mysql.repository;

import com.bootcamp.stock.marca.ports.persistency.mysql.entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMarcaRepository extends JpaRepository<MarcaEntity, String> {
    Optional<MarcaEntity> findByNombre(String nombre);
}

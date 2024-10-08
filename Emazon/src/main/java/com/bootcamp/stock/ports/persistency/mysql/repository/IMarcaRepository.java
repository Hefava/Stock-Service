package com.bootcamp.stock.ports.persistency.mysql.repository;

import com.bootcamp.stock.ports.persistency.mysql.entity.MarcaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMarcaRepository extends JpaRepository<MarcaEntity, String> {
    Optional<MarcaEntity> findByNombre(String nombre);
}

package com.bootcampPragma.Stock.ports.persistency.mysql.repository;

import com.bootcampPragma.Stock.ports.persistency.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByNombre(String categoryName);
    List<CategoryEntity> findAllByOrderByNombreAsc();
}

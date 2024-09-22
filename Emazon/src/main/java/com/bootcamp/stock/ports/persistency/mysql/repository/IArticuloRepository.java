package com.bootcamp.stock.ports.persistency.mysql.repository;

import com.bootcamp.stock.domain.utils.ArticuloQueries;
import com.bootcamp.stock.ports.persistency.mysql.entity.ArticuloEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IArticuloRepository extends JpaRepository<ArticuloEntity, Long> {
    Optional<ArticuloEntity> findByNombre(String nombre);

    @Query("SELECT a FROM ArticuloEntity a WHERE a.articuloID IN :ids")
    Page<ArticuloEntity> findAllById(@Param("ids") List<Long> ids, Pageable pageable);

    @Query(
            value = "SELECT a FROM ArticuloEntity a " +
                    "JOIN a.categorias c " +
                    "JOIN a.marca m " +
                    "WHERE a.articuloID IN :ids " +
                    "AND c.nombre = :categoriaNombre " +
                    "AND m.nombre = :marcaNombre"
    )
    Page<ArticuloEntity> findByArticuloIDInAndCategoriaNombreAndMarcaNombre(
            @Param("ids") List<Long> ids,
            @Param("categoriaNombre") String categoriaNombre,
            @Param("marcaNombre") String marcaNombre,
            Pageable pageable
    );

    @Query(
            value = "SELECT a FROM ArticuloEntity a " +
                    "JOIN a.categorias c " +
                    "WHERE a.articuloID IN :ids " +
                    "AND c.nombre = :categoriaNombre"
    )
    Page<ArticuloEntity> findByArticuloIDInAndCategoriaNombre(
            @Param("ids") List<Long> ids,
            @Param("categoriaNombre") String categoriaNombre,
            Pageable pageable
    );

    @Query(
            value = "SELECT a FROM ArticuloEntity a " +
                    "JOIN a.marca m " +
                    "WHERE a.articuloID IN :ids " +
                    "AND m.nombre = :marcaNombre"
    )
    Page<ArticuloEntity> findByArticuloIDInAndMarcaNombre(
            @Param("ids") List<Long> ids,
            @Param("marcaNombre") String marcaNombre,
            Pageable pageable
    );

    @Query(ArticuloQueries.FIND_ALL_ORDER_BY_CATEGORIA_NOMBRE)
    Page<ArticuloEntity> findAllOrderByCategoriaNombre(Pageable pageable);
}

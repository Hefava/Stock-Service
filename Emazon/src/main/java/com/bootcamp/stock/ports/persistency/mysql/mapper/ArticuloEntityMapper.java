package com.bootcamp.stock.ports.persistency.mysql.mapper;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.ports.persistency.mysql.entity.ArticuloEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = {CategoryEntityMapper.class, MarcaEntityMapper.class})
public interface ArticuloEntityMapper {

    ArticuloEntity toEntity(Articulo articulo);

    Articulo toArticulo(ArticuloEntity entity);

    List<Articulo> toArticuloList(List<ArticuloEntity> articuloEntityList);
}

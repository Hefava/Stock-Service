package com.bootcamp.stock.articulo.ports.persistency.mysql.mapper;

import com.bootcamp.stock.articulo.domain.model.Articulo;
import com.bootcamp.stock.articulo.ports.persistency.mysql.entity.ArticuloEntity;
import com.bootcamp.stock.categoria.ports.persistency.mysql.mapper.CategoryEntityMapper;
import com.bootcamp.stock.marca.ports.persistency.mysql.mapper.MarcaEntityMapper;
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

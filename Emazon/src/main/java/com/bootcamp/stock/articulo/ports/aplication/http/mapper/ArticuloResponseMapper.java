package com.bootcamp.stock.articulo.ports.aplication.http.mapper;

import com.bootcamp.stock.articulo.domain.model.Articulo;
import com.bootcamp.stock.articulo.ports.aplication.http.dto.ArticuloResponse;
import com.bootcamp.stock.categoria.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticuloResponseMapper {

    @Mapping(source = "marca.nombre", target = "marcaNombre")
    @Mapping(target = "categoriaNombres", expression = "java(mapCategorias(articulo.getCategorias()))")
    ArticuloResponse toResponse(Articulo articulo);

    default Set<String> mapCategorias(Set<Category> categorias) {
        return categorias.stream()
                .map(Category::getNombre)
                .collect(Collectors.toSet());
    }

    List<ArticuloResponse> toResponseList(List<Articulo> articuloList);
}

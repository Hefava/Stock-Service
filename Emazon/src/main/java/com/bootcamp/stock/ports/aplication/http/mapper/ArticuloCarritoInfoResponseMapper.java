package com.bootcamp.stock.ports.aplication.http.mapper;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.ports.aplication.http.dto.ArticuloCarritoInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ArticuloCarritoInfoResponseMapper {

    @Mapping(source = "marca.nombre", target = "marcaNombre")
    @Mapping(target = "categorias", expression = "java(mapCategorias(articulo.getCategorias()))")
    ArticuloCarritoInfoResponse toResponse(Articulo articulo);

    default List<String> mapCategorias(Set<Category> categorias) {
        return categorias.stream()
                .map(Category::getNombre)
                .toList();
    }

    List<ArticuloCarritoInfoResponse> toResponseList(List<Articulo> articuloList);
}
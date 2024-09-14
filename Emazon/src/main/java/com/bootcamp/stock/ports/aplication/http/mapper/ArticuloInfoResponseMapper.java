package com.bootcamp.stock.ports.aplication.http.mapper;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.ports.aplication.http.dto.ArticuloInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ArticuloInfoResponseMapper {
    @Mapping(target = "categorias", expression = "java(mapCategorias(articulo.getCategorias()))")
    ArticuloInfoResponse toResponse(Articulo articulo);

    default List<ArticuloInfoResponse.CategoriaInfoDto> mapCategorias(Set<Category> categorias) {
        return categorias.stream()
                .map(categoria -> new ArticuloInfoResponse.CategoriaInfoDto(categoria.getCategoryID()))
                .toList();
    }

    List<ArticuloInfoResponse> toResponseList(List<Articulo> articuloList);
}

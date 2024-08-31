package com.bootcamp.stock.ports.aplication.http.mapper;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.ports.aplication.http.dto.ArticuloResponse;
import com.bootcamp.stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticuloResponseMapper {

    @Mapping(source = "marca.nombre", target = "marcaNombre")
    @Mapping(target = "categorias", expression = "java(mapCategorias(articulo.getCategorias()))")
    ArticuloResponse toResponse(Articulo articulo);

    default List<ArticuloResponse.CategoriaDto> mapCategorias(Set<Category> categorias) {
        return categorias.stream()
                .map(categoria -> new ArticuloResponse.CategoriaDto(categoria.getCategoryID(), categoria.getNombre()))
                .toList();
    }

    List<ArticuloResponse> toResponseList(List<Articulo> articuloList);
}
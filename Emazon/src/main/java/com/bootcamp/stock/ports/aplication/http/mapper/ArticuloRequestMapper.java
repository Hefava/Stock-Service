package com.bootcamp.stock.ports.aplication.http.mapper;

import com.bootcamp.stock.domain.model.Articulo;
import com.bootcamp.stock.ports.aplication.http.dto.ArticuloRequest;
import com.bootcamp.stock.domain.model.Category;
import com.bootcamp.stock.domain.model.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticuloRequestMapper {
    @Mapping(source = "marcaID", target = "marcaID") Marca mapMarca(Long marcaID);
    @Mapping(source = "categoryID", target = "categoryID") Category mapCategoria(Long categoryID);

    @Mapping(source = "marcaID", target = "marca")
    @Mapping(source = "categoriaIDs", target = "categorias")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "cantidad", target = "cantidad")
    @Mapping(source = "precio", target = "precio")
    Articulo toArticulo(ArticuloRequest articuloRequest);
}

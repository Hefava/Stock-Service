package com.bootcamp.stock.marca.ports.aplication.http.mapper;

import com.bootcamp.stock.marca.domain.model.Marca;
import com.bootcamp.stock.marca.ports.aplication.http.dto.MarcaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarcaRequestMapper {
    @Mapping(target = "marcaID", ignore = true)
    Marca toMarca(MarcaRequest marcaRequest);
}

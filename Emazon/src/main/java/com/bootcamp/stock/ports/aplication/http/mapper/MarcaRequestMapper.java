package com.bootcamp.stock.ports.aplication.http.mapper;

import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.ports.aplication.http.dto.MarcaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarcaRequestMapper {
    @Mapping(target = "marcaID", ignore = true)
    Marca toMarca(MarcaRequest marcaRequest);
}

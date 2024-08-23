package com.bootcampPragma.Stock.Marca.ports.aplication.http.mapper;

import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.ports.aplication.http.dto.MarcaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarcaRequestMapper {
    @Mapping(target = "marcaID", ignore = true)
    Marca toMarca(MarcaRequest marcaRequest);
}

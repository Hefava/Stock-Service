package com.bootcampPragma.Stock.ports.aplication.http.mapper.marca;

import com.bootcampPragma.Stock.domain.model.Marca;
import com.bootcampPragma.Stock.ports.aplication.http.dto.MarcaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarcaRequestMapper {
    @Mapping(target = "marcaID", ignore = true)
    Marca toMarca(MarcaRequest marcaRequest);
}

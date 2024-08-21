package com.bootcampPragma.Stock.ports.aplication.http.mapper.marca;

import com.bootcampPragma.Stock.domain.model.Marca;
import com.bootcampPragma.Stock.ports.aplication.http.dto.MarcaResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcaResponseMapper {
    MarcaResponse toResponse(Marca marca);
    List<MarcaResponse> toResponseList(List<Marca> marcaList);
}

package com.bootcampPragma.Stock.Marca.ports.aplication.http.mapper;

import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.ports.aplication.http.dto.MarcaResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcaResponseMapper {

    MarcaResponse toResponse(Marca marca);

    List<MarcaResponse> toResponseList(List<Marca> marcaList);
}

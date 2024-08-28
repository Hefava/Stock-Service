package com.bootcamp.stock.ports.aplication.http.mapper;

import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.ports.aplication.http.dto.MarcaResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcaResponseMapper {

    MarcaResponse toResponse(Marca marca);

    List<MarcaResponse> toResponseList(List<Marca> marcaList);
}

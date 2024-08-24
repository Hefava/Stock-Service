package com.bootcamp.stock.marca.ports.aplication.http.mapper;

import com.bootcamp.stock.marca.domain.model.Marca;
import com.bootcamp.stock.marca.ports.aplication.http.dto.MarcaResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcaResponseMapper {

    MarcaResponse toResponse(Marca marca);

    List<MarcaResponse> toResponseList(List<Marca> marcaList);
}

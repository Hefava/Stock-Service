package com.bootcampPragma.Stock.Marca.ports.persistency.mysql.mapper;

import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.ports.persistency.mysql.entity.MarcaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MarcaEntityMapper {
    MarcaEntity toEntity(Marca marca);

    Marca toMarca(MarcaEntity entity);

    List<Marca> toMarcaList(List<MarcaEntity> marcaEntityList);
}
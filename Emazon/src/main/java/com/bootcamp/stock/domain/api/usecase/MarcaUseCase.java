package com.bootcamp.stock.domain.api.usecase;

import com.bootcamp.stock.domain.api.IMarcaServicePort;
import com.bootcamp.stock.domain.exception.InvalidMarcaDescriptionLengthException;
import com.bootcamp.stock.domain.exception.InvalidMarcaNameLengthException;
import com.bootcamp.stock.domain.exception.MarcaAlreadyExistsException;
import com.bootcamp.stock.domain.model.Marca;
import com.bootcamp.stock.domain.spi.IMarcaPersistencePort;
import com.bootcamp.stock.domain.utils.constants.MarcaConstants;
import com.bootcamp.stock.domain.utils.pagination.PageRequestUtil;
import com.bootcamp.stock.domain.utils.pagination.PagedResult;
import com.bootcamp.stock.domain.utils.pagination.SortUtil;

public class MarcaUseCase implements IMarcaServicePort {
    private final IMarcaPersistencePort marcaPersistencePort;

    public MarcaUseCase(IMarcaPersistencePort marcaPersistencePort) {
        this.marcaPersistencePort = marcaPersistencePort;
    }

    @Override
    public void saveMarca(Marca marca) {
        if (marcaPersistencePort.findByNombre(marca.getNombre()).isPresent()) {
            throw new MarcaAlreadyExistsException();
        }
        if (marca.getNombre().length() > MarcaConstants.MAX_NOMBRE_LENGTH.getValue()) {
            throw new InvalidMarcaNameLengthException();
        }
        if (marca.getDescripcion().length() > MarcaConstants.MAX_DESCRIPCION_LENGTH.getValue()) {
            throw new InvalidMarcaDescriptionLengthException();
        }
        marcaPersistencePort.saveMarca(marca);
    }

    @Override
    public PagedResult<Marca> getMarcasByNombre(SortUtil sort, PageRequestUtil pageRequest) {
        return marcaPersistencePort.getMarcasByNombre(sort, pageRequest);
    }

}
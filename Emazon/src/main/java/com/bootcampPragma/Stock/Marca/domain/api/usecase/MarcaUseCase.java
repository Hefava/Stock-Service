package com.bootcampPragma.Stock.Marca.domain.api.usecase;

import com.bootcampPragma.Stock.Marca.domain.api.IMarcaServicePort;
import com.bootcampPragma.Stock.Marca.domain.exception.InvalidMarcaDescriptionLengthException;
import com.bootcampPragma.Stock.Marca.domain.exception.InvalidMarcaNameLengthException;
import com.bootcampPragma.Stock.Marca.domain.exception.MarcaAlreadyExistsException;
import com.bootcampPragma.Stock.Marca.domain.model.Marca;
import com.bootcampPragma.Stock.Marca.domain.spi.IMarcaPersistencePort;
import com.bootcampPragma.Stock.Marca.domain.utils.MarcaConstants;

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
}
package com.bootcamp.stock.marca.domain.api.usecase;

import com.bootcamp.stock.marca.domain.api.IMarcaServicePort;
import com.bootcamp.stock.marca.domain.exception.InvalidMarcaDescriptionLengthException;
import com.bootcamp.stock.marca.domain.exception.InvalidMarcaNameLengthException;
import com.bootcamp.stock.marca.domain.exception.MarcaAlreadyExistsException;
import com.bootcamp.stock.marca.domain.model.Marca;
import com.bootcamp.stock.marca.domain.spi.IMarcaPersistencePort;
import com.bootcamp.stock.marca.domain.utils.MarcaConstants;
import com.bootcamp.stock.marca.domain.utils.PageRequestMarca;
import com.bootcamp.stock.marca.domain.utils.SortMarca;

import java.util.List;

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
    public List<Marca> getMarcasByNombre(SortMarca sort, PageRequestMarca pageRequest) {
        return marcaPersistencePort.getMarcasByNombre(sort, pageRequest);
    }
}
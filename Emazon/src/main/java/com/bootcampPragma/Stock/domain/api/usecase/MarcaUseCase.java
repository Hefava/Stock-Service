package com.bootcampPragma.Stock.domain.api.usecase;

import com.bootcampPragma.Stock.domain.api.IMarcaServicePort;
import com.bootcampPragma.Stock.domain.model.Marca;
import com.bootcampPragma.Stock.domain.spi.IMarcaPersistencePort;

public class MarcaUseCase implements IMarcaServicePort {
    private final IMarcaPersistencePort marcaPersistencePort;

    public MarcaUseCase(IMarcaPersistencePort marcaPersistencePort) {
        this.marcaPersistencePort = marcaPersistencePort;
    }

    @Override
    public void saveMarca(Marca marca) {
        marcaPersistencePort.saveMarca(marca);
    }
}

package com.matrix.tech.backend.apirest.director.domain.infraestructure.service;

import com.matrix.tech.backend.apirest.director.domain.Director;
import java.util.List;
import java.util.Optional;

public interface IDirectorService {

    public List<Director> findAll();

    public Director save(Director cliente);

    public Optional<Director> findById(Long id);

    public void delete(Director cliente);

}

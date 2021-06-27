package com.matrix.tech.backend.apirest.technology.domain.infraestructure.service;

import com.matrix.tech.backend.apirest.technology.domain.Technology;
import java.util.List;
import java.util.Optional;

public interface ITechnologyService {

    public List<Technology> findAll();

    public void save(Technology technology);

    public Optional<Technology> findById(Long id);

    public void delete(Technology technology);

}

package com.matrix.tech.backend.apirest.technology.domain.infraestructure.service;

import java.util.List;
import java.util.Optional;
import com.matrix.tech.backend.apirest.technology.domain.Technology;

public interface ITechnologyService {

  public List<Technology> findAll();

  public void save(Technology technology);

  public Optional<Technology> findById(Long id);

  public void delete(Technology technology);

}

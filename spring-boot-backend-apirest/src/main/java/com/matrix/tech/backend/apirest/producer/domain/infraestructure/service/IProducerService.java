package com.matrix.tech.backend.apirest.producer.domain.infraestructure.service;

import java.util.List;
import java.util.Optional;
import com.matrix.tech.backend.apirest.producer.domain.Producer;

public interface IProducerService {

  public List<Producer> findAll();

  public Producer save(Producer producer);

  public Optional<Producer> findById(Long id);

  public void delete(Producer producer);

}

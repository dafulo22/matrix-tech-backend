package com.matrix.tech.backend.apirest.producer.domain.infraestructure.service;

import com.matrix.tech.backend.apirest.producer.domain.Producer;
import java.util.List;
import java.util.Optional;

public interface IProducerService {

    public List<Producer> findAll();

    public Producer save(Producer producer);

    public Optional<Producer> findById(Long id);

    public void delete(Producer producer);

}

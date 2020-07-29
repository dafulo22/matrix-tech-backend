package com.matrix.tech.backend.apirest.producer.domain.infraestructure.persistence;

import org.springframework.data.repository.CrudRepository;
import com.matrix.tech.backend.apirest.producer.domain.Producer;

public interface IProducerDao extends CrudRepository<Producer, Long> {

}

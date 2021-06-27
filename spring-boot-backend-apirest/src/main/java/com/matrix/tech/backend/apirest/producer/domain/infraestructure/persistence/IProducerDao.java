package com.matrix.tech.backend.apirest.producer.domain.infraestructure.persistence;

import com.matrix.tech.backend.apirest.producer.domain.Producer;
import org.springframework.data.repository.CrudRepository;

public interface IProducerDao extends CrudRepository<Producer, Long> {

}

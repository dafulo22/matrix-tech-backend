package com.matrix.tech.backend.apirest.technology.domain.infraestructure.persistence;

import org.springframework.data.repository.CrudRepository;
import com.matrix.tech.backend.apirest.technology.domain.Technology;

public interface ITechnologyDao extends CrudRepository<Technology, Long> {

}

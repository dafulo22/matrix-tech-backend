package com.matrix.tech.backend.apirest.technology.domain.infraestructure.persistence;

import com.matrix.tech.backend.apirest.technology.domain.Technology;
import org.springframework.data.repository.CrudRepository;

public interface ITechnologyDao extends CrudRepository<Technology, Long> {

}

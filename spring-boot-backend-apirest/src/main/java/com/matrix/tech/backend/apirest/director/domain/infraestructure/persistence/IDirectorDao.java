package com.matrix.tech.backend.apirest.director.domain.infraestructure.persistence;

import org.springframework.data.repository.CrudRepository;
import com.matrix.tech.backend.apirest.director.domain.Director;

public interface IDirectorDao extends CrudRepository<Director, Long> {

}

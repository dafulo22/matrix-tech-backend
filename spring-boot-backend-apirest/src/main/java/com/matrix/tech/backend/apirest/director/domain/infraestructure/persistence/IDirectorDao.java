package com.matrix.tech.backend.apirest.director.domain.infraestructure.persistence;

import com.matrix.tech.backend.apirest.director.domain.Director;
import org.springframework.data.repository.CrudRepository;

public interface IDirectorDao extends CrudRepository<Director, Long> {

}

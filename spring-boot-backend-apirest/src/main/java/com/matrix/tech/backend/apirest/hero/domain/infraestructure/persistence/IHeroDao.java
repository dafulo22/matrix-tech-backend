package com.matrix.tech.backend.apirest.hero.domain.infraestructure.persistence;

import org.springframework.data.repository.CrudRepository;
import com.matrix.tech.backend.apirest.hero.domain.Hero;

public interface IHeroDao extends CrudRepository<Hero, Long> {

}

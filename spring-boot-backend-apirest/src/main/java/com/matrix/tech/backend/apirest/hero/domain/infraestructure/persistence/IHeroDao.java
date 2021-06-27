package com.matrix.tech.backend.apirest.hero.domain.infraestructure.persistence;

import com.matrix.tech.backend.apirest.hero.domain.Hero;
import org.springframework.data.repository.CrudRepository;

public interface IHeroDao extends CrudRepository<Hero, Long> {

}

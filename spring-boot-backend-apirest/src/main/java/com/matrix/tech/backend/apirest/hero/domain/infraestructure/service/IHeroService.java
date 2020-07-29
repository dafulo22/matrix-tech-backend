package com.matrix.tech.backend.apirest.hero.domain.infraestructure.service;

import java.util.List;
import java.util.Optional;
import com.matrix.tech.backend.apirest.hero.domain.Hero;

public interface IHeroService {

  public List<Hero> findAll();

  public Hero save(Hero hero);

  public Optional<Hero> findById(Long id);

  public void delete(Hero hero);

}

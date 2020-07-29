package com.matrix.tech.backend.apirest.hero.domain.infraestructure.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.matrix.tech.backend.apirest.hero.domain.Hero;
import com.matrix.tech.backend.apirest.hero.domain.infraestructure.persistence.IHeroDao;

@Service
public class DefaultHeroService implements IHeroService {

  @Autowired
  private IHeroDao heroDao;

  @Override
  @Transactional(readOnly = true)
  public List<Hero> findAll() {
    return (List<Hero>) heroDao.findAll();
  }

  @Override
  @Transactional
  public Hero save(Hero protagonista) {
    return heroDao.save(protagonista);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Hero> findById(Long id) {
    return heroDao.findById(id);
  }

  @Override
  @Transactional
  public void delete(Hero protagonista) {
    heroDao.delete(protagonista);

  }

}

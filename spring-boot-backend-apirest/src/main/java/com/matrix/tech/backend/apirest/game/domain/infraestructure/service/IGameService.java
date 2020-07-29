package com.matrix.tech.backend.apirest.game.domain.infraestructure.service;

import java.util.List;
import java.util.Optional;
import com.matrix.tech.backend.apirest.game.domain.Game;

public interface IGameService {

  public List<Game> findAll();

  public Game save(Game game);

  public Optional<Game> findById(Long id);

  public void delete(Game game);

}

package com.matrix.tech.backend.apirest.game.domain.infraestructure.persistence;

import com.matrix.tech.backend.apirest.game.domain.Game;
import org.springframework.data.repository.CrudRepository;

public interface IGameDao extends CrudRepository<Game, Long> {

}

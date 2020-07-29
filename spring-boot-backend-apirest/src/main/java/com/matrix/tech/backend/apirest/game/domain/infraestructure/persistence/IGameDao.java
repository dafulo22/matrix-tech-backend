package com.matrix.tech.backend.apirest.game.domain.infraestructure.persistence;

import org.springframework.data.repository.CrudRepository;
import com.matrix.tech.backend.apirest.game.domain.Game;

public interface IGameDao extends CrudRepository<Game, Long> {

}

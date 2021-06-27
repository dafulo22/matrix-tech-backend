package com.matrix.tech.backend.apirest.interfaces.game;

import static com.matrix.tech.backend.apirest.common.constants.Constant.GAME_URL;
import static com.matrix.tech.backend.apirest.common.constants.Constant.READ;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.game.domain.Game;
import com.matrix.tech.backend.apirest.game.domain.infraestructure.service.IGameService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(GAME_URL)
public class BaseGameController {

    @Autowired
    protected IGameService gameService;

    protected Map<String, Object> response = new HashMap<>();

    protected ResponseEntity<?> findGameById(Long id) {

        Optional<Game> game;

        try {
            game = this.gameService.findById(id);
        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, READ);
            return new ResponseEntity<>(this.response,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!game.isPresent()) {
            this.response.put("mensaje",
                "El juego ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(this.response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(game.get(), HttpStatus.OK);
    }
}

package com.matrix.tech.backend.apirest.interfaces.game.delete.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.DELETE;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.game.domain.Game;
import com.matrix.tech.backend.apirest.interfaces.game.BaseGameController;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RemoveGameController extends BaseGameController {

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        ResponseEntity<?> gameResponse = this.findGameById(id);

        if (!Util.verifyResponse(gameResponse)) {
            return gameResponse;
        }

        Game currentGame = (Game) gameResponse.getBody();

        try {
            this.gameService.delete(currentGame);

        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, DELETE);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Juego eliminado con éxito!");

        return new ResponseEntity<>(response, HttpStatus.OK);


    }
}

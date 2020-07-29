package com.matrix.tech.backend.apirest.interfaces.game.update.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.UPDATE;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.game.domain.Game;
import com.matrix.tech.backend.apirest.interfaces.game.BaseGameController;

@RestController
public class UpdaterGameController extends BaseGameController {

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@Valid @RequestBody Game gameParam, BindingResult result,
      @PathVariable Long id) {

    if (Util.hasErrors(result)) {
      return Util.obtainErrorResponse(result);
    }

    ResponseEntity<?> gameResponse = this.findGameById(id);

    if (!Util.verifyResponse(gameResponse)) {
      return gameResponse;
    }

    Game gameUpdated = null;
    Game currentGame = (Game) gameResponse.getBody();

    try {

      Util.fillInDefaultInformation(gameParam, currentGame);

      gameUpdated = this.gameService.save(currentGame);


    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, UPDATE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "El juego ha sido actualizado con éxito!");
    response.put("juego", gameUpdated);

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);


  }

}

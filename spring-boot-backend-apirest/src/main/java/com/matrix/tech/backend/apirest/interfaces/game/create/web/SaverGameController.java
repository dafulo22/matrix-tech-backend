package com.matrix.tech.backend.apirest.interfaces.game.create.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.CREATE;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.game.domain.Game;
import com.matrix.tech.backend.apirest.interfaces.game.BaseGameController;

@RestController
public class SaverGameController extends BaseGameController {

  @PostMapping("/")
  public ResponseEntity<?> create(@Valid @RequestBody Game game, BindingResult result) {

    if (Util.hasErrors(result)) {
      return Util.obtainErrorResponse(result);
    }

    Game newGame = null;
    try {

      newGame = this.gameService.save(game);

    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, CREATE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "El juego ha sido creado con éxito!");
    response.put("juego", newGame);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

  }

}

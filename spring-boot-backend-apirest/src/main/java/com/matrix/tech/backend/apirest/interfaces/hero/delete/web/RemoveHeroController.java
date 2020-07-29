package com.matrix.tech.backend.apirest.interfaces.hero.delete.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.DELETE;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.hero.domain.Hero;
import com.matrix.tech.backend.apirest.interfaces.hero.BaseHeroController;

@RestController
public class RemoveHeroController extends BaseHeroController {

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    ResponseEntity<?> heroResponse = findHeroById(id);

    if (!Util.verifyResponse(heroResponse)) {
      return heroResponse;
    }

    Hero currentHero = (Hero) heroResponse.getBody();

    try {
      this.heroService.delete(currentHero);
    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, DELETE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "Protagonista eliminado con éxito!");

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

  }

}

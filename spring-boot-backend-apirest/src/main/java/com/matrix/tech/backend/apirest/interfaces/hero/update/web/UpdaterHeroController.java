package com.matrix.tech.backend.apirest.interfaces.hero.update.web;

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
import com.matrix.tech.backend.apirest.hero.domain.Hero;
import com.matrix.tech.backend.apirest.interfaces.hero.BaseHeroController;

@RestController
public class UpdaterHeroController extends BaseHeroController {

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@Valid @RequestBody Hero heroParam, BindingResult result,
      @PathVariable Long id) {

    if (Util.hasErrors(result)) {
      return Util.obtainErrorResponse(result);
    }
    
    ResponseEntity<?> hero = findHeroById(id);

    if (!Util.verifyResponse(hero)) {
      return hero;
    }

    Hero heroUpdated = null;
    Hero currentHero = (Hero) hero.getBody();

    try {

      Util.fillInDefaultInformation(heroParam, currentHero);

      heroUpdated = this.heroService.save(currentHero);


    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, UPDATE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "El protagonista ha sido actualizado con éxito!");
    response.put("protagonista", heroUpdated);

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

  }

}

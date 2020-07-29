package com.matrix.tech.backend.apirest.interfaces.hero.create.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.CREATE;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.hero.domain.Hero;
import com.matrix.tech.backend.apirest.interfaces.hero.BaseHeroController;

@RestController
public class SaverHeroController extends BaseHeroController {

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> create(@Valid @RequestBody Hero hero, BindingResult result) {

    if (Util.hasErrors(result)) {
      return Util.obtainErrorResponse(result);
    }

    Hero newHero = null;

    try {

      newHero = this.heroService.save(hero);

    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, CREATE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "El protagonista ha sido creado con éxito!");
    response.put("protagonista", newHero);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }
}

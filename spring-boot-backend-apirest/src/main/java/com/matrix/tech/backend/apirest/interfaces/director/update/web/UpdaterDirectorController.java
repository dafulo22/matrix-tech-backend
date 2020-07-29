package com.matrix.tech.backend.apirest.interfaces.director.update.web;

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
import com.matrix.tech.backend.apirest.director.domain.Director;
import com.matrix.tech.backend.apirest.interfaces.director.BaseDirectorController;

@RestController
public class UpdaterDirectorController extends BaseDirectorController {

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@Valid @RequestBody Director directorParam, BindingResult result,
      @PathVariable Long id) {

    if (Util.hasErrors(result)) {
      return Util.obtainErrorResponse(result);
    }

    ResponseEntity<?> director = findDirectorById(id);

    if (!Util.verifyResponse(director)) {
      return director;
    }

    Director directorUpdated = null;
    Director currentDirector = (Director) director.getBody();

    try {

      Util.fillInDefaultInformation(directorParam, currentDirector);

      directorUpdated = this.directorService.save(currentDirector);


    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, UPDATE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "El director ha sido actualizado con éxito!");
    response.put("director", directorUpdated);

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

  }

}

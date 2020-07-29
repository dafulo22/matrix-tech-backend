package com.matrix.tech.backend.apirest.interfaces.director.delete.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.DELETE;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.director.domain.Director;
import com.matrix.tech.backend.apirest.interfaces.director.BaseDirectorController;

@RestController
public class RemoveDirectorController extends BaseDirectorController {

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {

    ResponseEntity<?> directorResponse = findDirectorById(id);

    if (!Util.verifyResponse(directorResponse)) {
      return directorResponse;
    }

    Director currentDirector = (Director) directorResponse.getBody();

    try {
      this.directorService.delete(currentDirector);

    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, DELETE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "Director eliminado con éxito!");

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

  }

}

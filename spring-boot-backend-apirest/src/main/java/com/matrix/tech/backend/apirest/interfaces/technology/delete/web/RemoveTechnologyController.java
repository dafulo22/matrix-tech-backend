package com.matrix.tech.backend.apirest.interfaces.technology.delete.web;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.interfaces.technology.BaseTechnologyController;
import com.matrix.tech.backend.apirest.technology.domain.Technology;

@RestController
public class RemoveTechnologyController extends BaseTechnologyController {

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    Optional<Technology> tecnologia = findTechnologyById(id);
    if (tecnologia.isPresent()) {
      Technology currentTecnologia = tecnologia.get();
      this.technologyService.delete(currentTecnologia);
    }
  }
  
}
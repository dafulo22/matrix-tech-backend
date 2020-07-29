package com.matrix.tech.backend.apirest.interfaces.technology.update.web;

import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.interfaces.technology.BaseTechnologyController;
import com.matrix.tech.backend.apirest.technology.domain.Technology;

@RestController
public class UpdaterTechnologyController extends BaseTechnologyController {

  @PutMapping("/{id}")
  public Technology update(@RequestBody Technology technologyParam, @PathVariable Long id) {
    Optional<Technology> technology = findTechnologyById(id);

    if (technology.isPresent()) {
      Technology currentTechnology = technology.get();
      currentTechnology.setName(technologyParam.getName());
      currentTechnology.setDescription(technologyParam.getDescription());
      this.technologyService.save(currentTechnology);
      return currentTechnology;

    }

    return technologyParam;

  }
}

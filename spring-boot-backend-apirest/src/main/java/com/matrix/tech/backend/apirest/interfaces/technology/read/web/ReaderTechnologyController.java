package com.matrix.tech.backend.apirest.interfaces.technology.read.web;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.interfaces.technology.BaseTechnologyController;
import com.matrix.tech.backend.apirest.technology.domain.Technology;

@RestController
public class ReaderTechnologyController extends BaseTechnologyController {

  @GetMapping("/")
  public List<Technology> index() {
    return technologyService.findAll();
  }

  @GetMapping("/{id}")
  public Technology show(@PathVariable Long id) {
    Optional<Technology> technology = findTechnologyById(id);
    return (technology.isPresent()) ? technology.get() : null;
  }
}

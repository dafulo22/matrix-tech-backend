package com.matrix.tech.backend.apirest.interfaces.director.read.web;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.director.domain.Director;
import com.matrix.tech.backend.apirest.interfaces.director.BaseDirectorController;

@RestController
public class ReaderDirectorController extends BaseDirectorController {

  @GetMapping("/")
  public List<Director> index() {
    return directorService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> show(@PathVariable Long id) {
    return this.findDirectorById(id);
  }

}

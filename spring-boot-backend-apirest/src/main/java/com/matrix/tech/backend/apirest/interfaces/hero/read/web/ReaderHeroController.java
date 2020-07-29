package com.matrix.tech.backend.apirest.interfaces.hero.read.web;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.hero.domain.Hero;
import com.matrix.tech.backend.apirest.interfaces.hero.BaseHeroController;

@RestController
public class ReaderHeroController extends BaseHeroController {

  @GetMapping("/")
  public List<Hero> index() {
    return heroService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> show(@PathVariable Long id) {
    return findHeroById(id);
  }

}

package com.matrix.tech.backend.apirest.interfaces.producer.read.web;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.interfaces.producer.BaseProducerController;
import com.matrix.tech.backend.apirest.producer.domain.Producer;

@RestController
public class ReaderProducerController extends BaseProducerController {

  @GetMapping("/")
  public List<Producer> index() {
    return producerService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> show(@PathVariable Long id) {
    return findProducerById(id);
  }

}

package com.matrix.tech.backend.apirest.interfaces.producer;

import static com.matrix.tech.backend.apirest.common.constants.Constant.PRODUCER_URL;
import static com.matrix.tech.backend.apirest.common.constants.Constant.READ;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.producer.domain.Producer;
import com.matrix.tech.backend.apirest.producer.domain.infraestructure.service.IProducerService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(PRODUCER_URL)
public class BaseProducerController {

  @Autowired
  protected IProducerService producerService;

  protected Map<String, Object> response = new HashMap<>();

  protected ResponseEntity<?> findProducerById(Long id) {
    Optional<Producer> producer;
    try {
      producer = this.producerService.findById(id);
    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, READ);
      return new ResponseEntity<Map<String, Object>>(this.response,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

    if (!producer.isPresent()) {
      this.response.put("mensaje",
          "El productor ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
      return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Producer>(producer.get(), HttpStatus.OK);
  }
}

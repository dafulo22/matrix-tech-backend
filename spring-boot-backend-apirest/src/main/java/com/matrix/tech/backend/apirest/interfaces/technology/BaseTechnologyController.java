package com.matrix.tech.backend.apirest.interfaces.technology;

import static com.matrix.tech.backend.apirest.common.constants.Constant.TECHNOLOGY_URL;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.technology.domain.Technology;
import com.matrix.tech.backend.apirest.technology.domain.infraestructure.service.ITechnologyService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(TECHNOLOGY_URL)
public class BaseTechnologyController {

  @Autowired
  protected ITechnologyService technologyService;

  protected Optional<Technology> findTechnologyById(Long id) {
    Optional<Technology> technology = this.technologyService.findById(id);
    return technology;
  }
}

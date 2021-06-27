package com.matrix.tech.backend.apirest.interfaces.hero;

import static com.matrix.tech.backend.apirest.common.constants.Constant.HERO_URL;
import static com.matrix.tech.backend.apirest.common.constants.Constant.READ;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.hero.domain.Hero;
import com.matrix.tech.backend.apirest.hero.domain.infraestructure.service.IHeroService;
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

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(HERO_URL)
public class BaseHeroController {

    @Autowired
    protected IHeroService heroService;

    protected Map<String, Object> response = new HashMap<>();

    protected ResponseEntity findHeroById(Long id) {
        Optional<Hero> hero;
        try {
            hero = this.heroService.findById(id);
        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, READ);
            return new ResponseEntity<>(this.response,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!hero.isPresent()) {
            this.response.put("mensaje",
                "El heroe ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(this.response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(hero.get(), HttpStatus.OK);
    }
}

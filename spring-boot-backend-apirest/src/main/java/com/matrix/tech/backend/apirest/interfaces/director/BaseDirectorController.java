package com.matrix.tech.backend.apirest.interfaces.director;

import static com.matrix.tech.backend.apirest.common.constants.Constant.DIRECTOR_URL;
import static com.matrix.tech.backend.apirest.common.constants.Constant.READ;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.director.domain.Director;
import com.matrix.tech.backend.apirest.director.domain.infraestructure.service.IDirectorService;
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
@RequestMapping(DIRECTOR_URL)
public class BaseDirectorController {

    @Autowired
    protected IDirectorService directorService;

    protected Map<String, Object> response = new HashMap<>();

    protected ResponseEntity<?> findDirectorById(Long id) {
        Optional<Director> director;
        try {
            director = this.directorService.findById(id);
        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, READ);
            return new ResponseEntity<>(this.response,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!director.isPresent()) {
            this.response.put("mensaje",
                "El director ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(this.response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(director.get(), HttpStatus.OK);
    }
}

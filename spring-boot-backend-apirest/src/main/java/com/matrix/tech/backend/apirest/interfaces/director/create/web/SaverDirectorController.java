package com.matrix.tech.backend.apirest.interfaces.director.create.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.CREATE;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.director.domain.Director;
import com.matrix.tech.backend.apirest.interfaces.director.BaseDirectorController;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaverDirectorController extends BaseDirectorController {

    @PostMapping("/")
    public ResponseEntity create(@Valid @RequestBody Director director, BindingResult result) {

        if (Util.hasErrors(result)) {
            return Util.obtainErrorResponse(result);
        }

        Director newDirector = null;

        try {

            newDirector = this.directorService.save(director);

        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, CREATE);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El director ha sido creado con éxito!");
        response.put("director", newDirector);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

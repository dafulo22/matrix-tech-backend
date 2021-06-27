package com.matrix.tech.backend.apirest.interfaces.rental;

import static com.matrix.tech.backend.apirest.common.constants.Constant.READ;
import static com.matrix.tech.backend.apirest.common.constants.Constant.RENTAL_URL;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.rental.domain.Rental;
import com.matrix.tech.backend.apirest.rental.domain.infraestructure.service.IRentalService;
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
@RequestMapping(RENTAL_URL)
public class BaseRentalController {

    @Autowired
    protected IRentalService rentalService;

    protected Map<String, Object> response = new HashMap<>();

    protected ResponseEntity<?> findRentalById(Long id) {
        Optional<Rental> producer;
        try {
            producer = this.rentalService.findById(id);
        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, READ);
            return new ResponseEntity<>(this.response,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!producer.isPresent()) {
            this.response.put("mensaje",
                "La renta ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(this.response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(producer.get(), HttpStatus.OK);
    }
}

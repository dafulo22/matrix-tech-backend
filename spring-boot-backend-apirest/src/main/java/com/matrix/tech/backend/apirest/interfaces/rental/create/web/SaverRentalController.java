package com.matrix.tech.backend.apirest.interfaces.rental.create.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.CREATE;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.interfaces.rental.BaseRentalController;
import com.matrix.tech.backend.apirest.rental.domain.Rental;

@RestController
public class SaverRentalController extends BaseRentalController {

  @RequestMapping(method = RequestMethod.POST,path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)  
  public ResponseEntity<?> create(@Valid @RequestBody Rental rental, BindingResult result) {

    if (Util.hasErrors(result)) {
      return Util.obtainErrorResponse(result);
    }

    Rental newRental = null;

    try {

      newRental = this.rentalService.save(rental);

    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, CREATE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "El alquiler ha sido creado con éxito!");
    response.put("alquiler", newRental);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

}

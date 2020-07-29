package com.matrix.tech.backend.apirest.interfaces.rental.delete.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.DELETE;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.interfaces.rental.BaseRentalController;
import com.matrix.tech.backend.apirest.rental.domain.Rental;

@RestController
public class RemoveRentalController extends BaseRentalController {

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    ResponseEntity<?> rentalResponse = findRentalById(id);

    if (!Util.verifyResponse(rentalResponse)) {
      return rentalResponse;
    }

    Rental currentRental = (Rental) rentalResponse.getBody();

    try {
      this.rentalService.delete(currentRental);
    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, DELETE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "Renta eliminada con éxito!");

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

}

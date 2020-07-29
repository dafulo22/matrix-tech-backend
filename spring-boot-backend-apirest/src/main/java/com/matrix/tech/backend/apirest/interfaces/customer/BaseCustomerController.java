package com.matrix.tech.backend.apirest.interfaces.customer;

import static com.matrix.tech.backend.apirest.common.constants.Constant.CUSTOMER_URL;
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
import com.matrix.tech.backend.apirest.customer.domain.Customer;
import com.matrix.tech.backend.apirest.customer.domain.infraestructure.service.ICustomerService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(CUSTOMER_URL)
public class BaseCustomerController {

  @Autowired
  protected ICustomerService customerService;

  protected Map<String, Object> response = new HashMap<>();

  protected ResponseEntity<?> findCustomerById(Long id) {
    Optional<Customer> customer;

    try {
      customer = this.customerService.findById(id);
    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, READ);
      return new ResponseEntity<Map<String, Object>>(this.response,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

    if (!customer.isPresent()) {
      this.response.put("mensaje",
          "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
      return new ResponseEntity<Map<String, Object>>(this.response, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
  }
}

package com.matrix.tech.backend.apirest.interfaces.customer.create.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.CREATE;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.customer.domain.Customer;
import com.matrix.tech.backend.apirest.interfaces.customer.BaseCustomerController;

@RestController
public class SaverCustomerController extends BaseCustomerController {

  @PostMapping("/")
  public ResponseEntity<?> create(@Valid @RequestBody Customer customer, BindingResult result) {

    if (Util.hasErrors(result)) {
      return Util.obtainErrorResponse(result);
    }


    if (this.customerService.existsByDocument(customer.getDocument())) {
      response.put("mensaje", "El cliente que intenta registrar ya existe en la base de datos");
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    
    Customer newCustomer = null;

    try {

      newCustomer = this.customerService.save(customer);

    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, CREATE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "El cliente ha sido creado con éxito!");
    response.put("cliente", newCustomer);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

}

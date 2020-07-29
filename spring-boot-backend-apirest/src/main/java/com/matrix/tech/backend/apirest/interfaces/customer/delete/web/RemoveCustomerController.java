package com.matrix.tech.backend.apirest.interfaces.customer.delete.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.DELETE;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.customer.domain.Customer;
import com.matrix.tech.backend.apirest.interfaces.customer.BaseCustomerController;

@RestController
public class RemoveCustomerController extends BaseCustomerController {

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {

    ResponseEntity<?> customerResponse = findCustomerById(id);

    if (!Util.verifyResponse(customerResponse)) {
      return customerResponse;
    }

    Customer currentCustomer = (Customer) customerResponse.getBody();

    try {
      this.customerService.delete(currentCustomer);

    } catch (DataAccessException e) {
      this.response = Util.obtainResponseError(e, DELETE);
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje", "Cliente eliminado con éxito!");

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

  }
}

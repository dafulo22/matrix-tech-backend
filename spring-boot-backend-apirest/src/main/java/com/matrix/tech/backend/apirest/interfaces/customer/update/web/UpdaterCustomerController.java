package com.matrix.tech.backend.apirest.interfaces.customer.update.web;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.customer.domain.Customer;
import com.matrix.tech.backend.apirest.interfaces.customer.BaseCustomerController;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdaterCustomerController extends BaseCustomerController {

    @PutMapping("/{id}")
    public ResponseEntity update(@Valid @RequestBody Customer customerParam, BindingResult result,
        @PathVariable Long id) {

        if (Util.hasErrors(result)) {
            return Util.obtainErrorResponse(result);
        }

        ResponseEntity<?> myCustomer = findCustomerById(id);

        if (!Util.verifyResponse(myCustomer)) {
            return myCustomer;
        }

        Customer customerUpdated = null;
        Customer currentCustomer = (Customer) myCustomer.getBody();

        try {

            fillInCustomerInformation(customerParam, currentCustomer);
            customerUpdated = this.customerService.save(currentCustomer);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el cliente en la base de datos");
            response.put("error",
                e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente ha sido actualizado con éxito!");
        response.put("cliente", customerUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }

    /**
     * @param customerParam
     * @param currentCustomer
     */
    private void fillInCustomerInformation(Customer customerParam, Customer currentCustomer) {

        currentCustomer.setAddress(customerParam.getAddress());
        currentCustomer.setBirthDate(customerParam.getBirthDate());
        currentCustomer.setCellPhone(customerParam.getCellPhone());
        currentCustomer.setDescription(customerParam.getDescription());
        currentCustomer.setDocument(customerParam.getDocument());
        currentCustomer.setEmail(customerParam.getEmail());
        currentCustomer.setGender(customerParam.getGender());
        currentCustomer.setIdentificationType(customerParam.getIdentificationType());
        currentCustomer.setLastName(customerParam.getLastName());
        currentCustomer.setName(customerParam.getName());
        currentCustomer.setPhone(customerParam.getPhone());

    }

}

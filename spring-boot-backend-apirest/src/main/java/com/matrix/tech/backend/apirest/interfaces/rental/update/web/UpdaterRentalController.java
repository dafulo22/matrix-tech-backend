package com.matrix.tech.backend.apirest.interfaces.rental.update.web;

import static com.matrix.tech.backend.apirest.common.constants.Constant.READ;
import static com.matrix.tech.backend.apirest.common.constants.Constant.UPDATE;

import com.matrix.tech.backend.apirest.common.Util;
import com.matrix.tech.backend.apirest.customer.domain.Customer;
import com.matrix.tech.backend.apirest.customer.domain.infraestructure.service.ICustomerService;
import com.matrix.tech.backend.apirest.interfaces.rental.BaseRentalController;
import com.matrix.tech.backend.apirest.inventory.domain.Inventory;
import com.matrix.tech.backend.apirest.inventory.domain.infraestructure.service.IInventoryService;
import com.matrix.tech.backend.apirest.rental.domain.Rental;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdaterRentalController extends BaseRentalController {

    @Autowired
    protected ICustomerService customerService;

    @Autowired
    protected IInventoryService inventoryService;

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Rental rentalParam, BindingResult result,
        @PathVariable Long id) {

        if (Util.hasErrors(result)) {
            return Util.obtainErrorResponse(result);
        }

        ResponseEntity<?> rental = findRentalById(id);

        if (!Util.verifyResponse(rental)) {
            return rental;
        }

        Optional<Customer> customer;

        try {
            customer = this.customerService.findById(rentalParam.getCustomer().getId());
        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, READ);
            return new ResponseEntity<>(this.response,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!customer.isPresent()) {
            this.response.put("mensaje",
                "El alquiler ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(this.response, HttpStatus.NOT_FOUND);
        }

        Optional<Inventory> inventory;

        try {
            inventory = this.inventoryService.findById(rentalParam.getCustomer().getId());
        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, READ);
            return new ResponseEntity<>(this.response,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!inventory.isPresent()) {
            this.response.put("mensaje",
                "El alquiler ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<>(this.response, HttpStatus.NOT_FOUND);
        }

        Rental rentalUpdated = null;
        Rental currentRental = (Rental) rental.getBody();

        Customer currentCustomer = (Customer) customer.get();
        Inventory currentInventory = (Inventory) inventory.get();

        try {

            fillRentalInformation(rentalParam, currentRental, currentCustomer, currentInventory);

            rentalUpdated = this.rentalService.save(currentRental);


        } catch (DataAccessException e) {
            this.response = Util.obtainResponseError(e, UPDATE);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La renta ha sido actualizado con éxito!");
        response.put("alquiler", rentalUpdated);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    /**
     * @param rentalParam
     * @param currentRental
     * @param currentCustomer
     * @param currentInventory
     */
    private void fillRentalInformation(Rental rentalParam, Rental currentRental,
        Customer currentCustomer, Inventory currentInventory) {
        currentRental.setCustomer(currentCustomer);
        currentRental.setInventory(currentInventory);

        currentRental.setRentalDate(rentalParam.getRentalDate());
        currentRental.setReturnDate(rentalParam.getReturnDate());
    }
}

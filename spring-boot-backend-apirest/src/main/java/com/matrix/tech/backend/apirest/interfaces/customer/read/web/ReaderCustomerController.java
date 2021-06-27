package com.matrix.tech.backend.apirest.interfaces.customer.read.web;

import com.matrix.tech.backend.apirest.customer.domain.Customer;
import com.matrix.tech.backend.apirest.interfaces.customer.BaseCustomerController;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReaderCustomerController extends BaseCustomerController {

    @GetMapping("/")
    public List<Customer> index() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        return this.findCustomerById(id);
    }

}

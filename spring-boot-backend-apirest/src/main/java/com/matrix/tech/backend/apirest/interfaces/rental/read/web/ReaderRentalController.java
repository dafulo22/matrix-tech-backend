package com.matrix.tech.backend.apirest.interfaces.rental.read.web;

import com.matrix.tech.backend.apirest.interfaces.rental.BaseRentalController;
import com.matrix.tech.backend.apirest.rental.domain.Rental;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReaderRentalController extends BaseRentalController {

    @GetMapping("/")
    public List<Rental> index() {
        return rentalService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id) {
        return findRentalById(id);
    }

}

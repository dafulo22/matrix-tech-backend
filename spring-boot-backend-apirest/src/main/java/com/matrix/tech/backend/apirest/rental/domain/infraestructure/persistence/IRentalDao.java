package com.matrix.tech.backend.apirest.rental.domain.infraestructure.persistence;

import com.matrix.tech.backend.apirest.rental.domain.Rental;
import org.springframework.data.repository.CrudRepository;

public interface IRentalDao extends CrudRepository<Rental, Long> {

}

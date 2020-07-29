package com.matrix.tech.backend.apirest.rental.domain.infraestructure.persistence;

import org.springframework.data.repository.CrudRepository;
import com.matrix.tech.backend.apirest.rental.domain.Rental;

public interface IRentalDao extends CrudRepository<Rental, Long> {

}

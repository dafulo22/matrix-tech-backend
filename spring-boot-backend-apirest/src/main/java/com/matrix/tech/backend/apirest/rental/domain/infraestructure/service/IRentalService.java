package com.matrix.tech.backend.apirest.rental.domain.infraestructure.service;

import java.util.List;
import java.util.Optional;
import com.matrix.tech.backend.apirest.rental.domain.Rental;

public interface IRentalService {

  public List<Rental> findAll();

  public Rental save(Rental producer);

  public Optional<Rental> findById(Long id);

  public void delete(Rental producer);

}

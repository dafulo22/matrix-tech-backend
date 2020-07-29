package com.matrix.tech.backend.apirest.rental.domain.infraestructure.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.matrix.tech.backend.apirest.rental.domain.Rental;
import com.matrix.tech.backend.apirest.rental.domain.infraestructure.persistence.IRentalDao;

@Service
public class DefaultRentalService implements IRentalService {

  @Autowired
  private IRentalDao rentalDao;

  @Override
  @Transactional(readOnly = true)
  public List<Rental> findAll() {
    return (List<Rental>) rentalDao.findAll();
  }

  @Override
  @Transactional
  public Rental save(Rental productor) {
    return rentalDao.save(productor);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Rental> findById(Long id) {
    return rentalDao.findById(id);
  }

  @Override
  @Transactional
  public void delete(Rental productor) {
    rentalDao.delete(productor);

  }

}

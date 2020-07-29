package com.matrix.tech.backend.apirest.customer.domain.infraestructure.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.matrix.tech.backend.apirest.customer.domain.Customer;
import com.matrix.tech.backend.apirest.customer.domain.infraestructure.persistence.ICustomerDao;

@Service
public class DefaultCustomerService implements ICustomerService {

  @Autowired
  private ICustomerDao customerDao;

  @Override
  @Transactional(readOnly = true)
  public List<Customer> findAll() {
    return (List<Customer>) customerDao.findAll();
  }

  @Override
  @Transactional
  public Customer save(Customer cliente) {
    return customerDao.save(cliente);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Customer> findById(Long id) {
    return customerDao.findById(id);
  }

  @Override
  public boolean existsByDocument(String document) {
    return customerDao.existsByDocument(document);
  }

  @Override
  @Transactional
  public void delete(Customer cliente) {
    customerDao.delete(cliente);

  }

}

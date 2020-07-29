package com.matrix.tech.backend.apirest.customer.domain.infraestructure.persistence;

import org.springframework.data.repository.CrudRepository;
import com.matrix.tech.backend.apirest.customer.domain.Customer;

public interface ICustomerDao extends CrudRepository<Customer, Long> {

  boolean existsByDocument(String document);

}

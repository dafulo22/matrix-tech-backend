package com.matrix.tech.backend.apirest.customer.domain.infraestructure.persistence;

import com.matrix.tech.backend.apirest.customer.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerDao extends CrudRepository<Customer, Long> {

    boolean existsByDocument(String document);

}

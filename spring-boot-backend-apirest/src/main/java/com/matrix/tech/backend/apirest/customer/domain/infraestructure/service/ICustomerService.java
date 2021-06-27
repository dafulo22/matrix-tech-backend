package com.matrix.tech.backend.apirest.customer.domain.infraestructure.service;

import com.matrix.tech.backend.apirest.customer.domain.Customer;
import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    public List<Customer> findAll();

    public Customer save(Customer cliente);

    public Optional<Customer> findById(Long id);

    public boolean existsByDocument(String document);

    public void delete(Customer cliente);

}

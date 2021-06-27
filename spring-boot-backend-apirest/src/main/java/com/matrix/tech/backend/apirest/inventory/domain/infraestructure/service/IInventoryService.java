package com.matrix.tech.backend.apirest.inventory.domain.infraestructure.service;

import com.matrix.tech.backend.apirest.inventory.domain.Inventory;
import java.util.List;
import java.util.Optional;

public interface IInventoryService {

    public List<Inventory> findAll();

    public Inventory save(Inventory producer);

    public Optional<Inventory> findById(Long id);

    public void delete(Inventory producer);

}

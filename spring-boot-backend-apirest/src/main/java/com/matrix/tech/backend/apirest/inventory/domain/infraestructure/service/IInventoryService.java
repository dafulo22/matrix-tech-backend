package com.matrix.tech.backend.apirest.inventory.domain.infraestructure.service;

import java.util.List;
import java.util.Optional;
import com.matrix.tech.backend.apirest.inventory.domain.Inventory;

public interface IInventoryService {

  public List<Inventory> findAll();

  public Inventory save(Inventory producer);

  public Optional<Inventory> findById(Long id);

  public void delete(Inventory producer);

}

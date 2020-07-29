package com.matrix.tech.backend.apirest.inventory.domain.infraestructure.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.matrix.tech.backend.apirest.inventory.domain.Inventory;
import com.matrix.tech.backend.apirest.inventory.domain.infraestructure.persistence.IInventoryDao;

@Service
public class DefaultInventoryService implements IInventoryService {

  @Autowired
  private IInventoryDao inventoryDao;

  @Override
  @Transactional(readOnly = true)
  public List<Inventory> findAll() {
    return (List<Inventory>) inventoryDao.findAll();
  }

  @Override
  @Transactional
  public Inventory save(Inventory productor) {
    return inventoryDao.save(productor);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Inventory> findById(Long id) {
    return inventoryDao.findById(id);
  }

  @Override
  @Transactional
  public void delete(Inventory productor) {
    inventoryDao.delete(productor);

  }

}

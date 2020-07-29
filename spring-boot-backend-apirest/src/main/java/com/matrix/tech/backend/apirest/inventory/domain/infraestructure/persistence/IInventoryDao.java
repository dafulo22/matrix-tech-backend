package com.matrix.tech.backend.apirest.inventory.domain.infraestructure.persistence;

import org.springframework.data.repository.CrudRepository;
import com.matrix.tech.backend.apirest.inventory.domain.Inventory;

public interface IInventoryDao extends CrudRepository<Inventory, Long> {

}

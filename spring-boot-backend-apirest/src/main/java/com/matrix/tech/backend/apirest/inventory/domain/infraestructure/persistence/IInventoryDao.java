package com.matrix.tech.backend.apirest.inventory.domain.infraestructure.persistence;

import com.matrix.tech.backend.apirest.inventory.domain.Inventory;
import org.springframework.data.repository.CrudRepository;

public interface IInventoryDao extends CrudRepository<Inventory, Long> {

}

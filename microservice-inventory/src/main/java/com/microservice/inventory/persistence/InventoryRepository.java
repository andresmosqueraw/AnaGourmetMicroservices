package com.microservice.inventory.persistence;

import com.microservice.inventory.entities.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    List<Inventory> findByStatusInventory(int statusInventory);
}

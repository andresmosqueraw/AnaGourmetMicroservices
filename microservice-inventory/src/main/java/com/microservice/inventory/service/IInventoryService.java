package com.microservice.inventory.service;

import com.microservice.inventory.entities.Inventory;

import java.util.List;

public interface IInventoryService {
    List<Inventory> getAllInventories();
    Inventory getInventoryById(Long id);
    void saveInventory(Inventory inventory);
    void deleteInventory(Long id);
    void updateInventory(Long id, Inventory inventory);
}

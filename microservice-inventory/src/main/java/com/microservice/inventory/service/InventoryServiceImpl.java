package com.microservice.inventory.service;

import com.microservice.inventory.entities.Inventory;
import com.microservice.inventory.persistence.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements IInventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getAllInventories() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public void updateInventory(Long id, Inventory inventory) {
        Inventory inventoryToUpdate = inventoryRepository.findById(id).orElseThrow();
        inventoryToUpdate.setProductName(inventory.getProductName());
        inventoryToUpdate.setQuantity(inventory.getQuantity());
        inventoryToUpdate.setUnitPrice(inventory.getUnitPrice());
        inventoryToUpdate.setSupplierId(inventory.getSupplierId());
        inventoryToUpdate.setCreatedAt(inventory.getCreatedAt());
        inventoryToUpdate.setUserId(inventory.getUserId());
        inventoryToUpdate.setStatusInventory(inventory.getStatusInventory());
        inventoryRepository.save(inventoryToUpdate);
    }
}

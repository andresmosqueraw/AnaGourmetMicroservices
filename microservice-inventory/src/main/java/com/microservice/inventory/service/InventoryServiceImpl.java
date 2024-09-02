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
        return inventoryRepository.findByStatusInventory(1);
    }

    @Override
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveInventory(Inventory inventory) {
        if(inventory.getCreatedAt() == null){
            inventory.setCreatedAt(java.time.Instant.now());
        }
        inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(Long id) {
        Inventory inventoryToUpdate = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado por ID " + id));
        inventoryToUpdate.setStatusInventory(0);
        inventoryRepository.save(inventoryToUpdate);
    }

    @Override
    public void updateInventory(Long id, Inventory inventory) {
        Inventory inventoryToUpdate = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
        if (inventory.getProductName() != null) {
            inventoryToUpdate.setProductName(inventory.getProductName());
        }
        if (inventory.getQuantity() != null) {
            inventoryToUpdate.setQuantity(inventory.getQuantity());
        }
        if (inventory.getUnitPrice() != null) {
            inventoryToUpdate.setUnitPrice(inventory.getUnitPrice());
        }
        if (inventory.getSupplierId() != null) {
            inventoryToUpdate.setSupplierId(inventory.getSupplierId());
        }
        if (inventory.getCreatedAt() != null) {
            inventoryToUpdate.setCreatedAt(inventory.getCreatedAt());
        }
        if (inventory.getUserId() != null) {
            inventoryToUpdate.setUserId(inventory.getUserId());
        }
        if (inventory.getStatusInventory() != null) {
            inventoryToUpdate.setStatusInventory(inventory.getStatusInventory());
        }
        inventoryRepository.save(inventoryToUpdate);
    }
}

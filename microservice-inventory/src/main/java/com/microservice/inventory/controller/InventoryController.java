package com.microservice.inventory.controller;

import com.microservice.inventory.entities.Inventory;
import com.microservice.inventory.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:4200")
public class InventoryController {

    @Autowired
    private IInventoryService inventoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveInventory(@RequestBody Inventory inventory) {
        inventoryService.saveInventory(inventory);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findInventoryById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok("Inventory deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        inventoryService.updateInventory(id, inventory);
        return ResponseEntity.ok("Inventory updated");
    }
}

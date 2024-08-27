package com.microservice.supplier.controller;

import com.microservice.supplier.entities.Supplier;
import com.microservice.supplier.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplier")
@CrossOrigin(origins = "http://localhost:4200")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSupplier(@RequestBody Supplier supplier) {
        supplierService.saveSupplier(supplier);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Supplier deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        supplierService.updateSupplier(id, supplier);
        return ResponseEntity.ok("Supplier updated");
    }
}

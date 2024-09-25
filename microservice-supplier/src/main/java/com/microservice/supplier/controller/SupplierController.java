package com.microservice.supplier.controller;

import com.microservice.supplier.entities.Supplier;
import com.microservice.supplier.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplier")
@CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Supplier> saveSupplier(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierService.saveSupplier(supplier);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @GetMapping("/all")
    public ResponseEntity<?> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok("Proveedor Eliminado");
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        supplierService.updateSupplier(id, supplier);
        return ResponseEntity.ok("Supplier updated");
    }
}

package com.microservice.sale.controller;

import com.microservice.sale.entities.Sale;
import com.microservice.sale.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sale")
@CrossOrigin(origins = "http://localhost:4200")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSale(@RequestBody Sale sale) {
        saleService.saveSale(sale);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok("Sale deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSale(@PathVariable Long id, @RequestBody Sale sale) {
        saleService.updateSale(id, sale);
        return ResponseEntity.ok("Sale updated");
    }
}

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

    @PostMapping("/createSale")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSale(@RequestBody Sale sale) {
        saleService.saveSale(sale);
    }

    @GetMapping("/allSales")
    public ResponseEntity<?> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @GetMapping("/searchSale/{id}")
    public ResponseEntity<?> findSaleById(@PathVariable Integer id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @DeleteMapping("/deleteSale/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Integer id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok("Venta eliminada");
    }

    @PutMapping("/updateSale/{id}")
    public ResponseEntity<?> updateSale(@PathVariable Integer id, @RequestBody Sale sale) {
        saleService.updateSale(id, sale);
        return ResponseEntity.ok("Venta actualizada");
    }

}

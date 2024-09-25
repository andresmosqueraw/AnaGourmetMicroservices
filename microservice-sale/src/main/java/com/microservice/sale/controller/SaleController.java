package com.microservice.sale.controller;

import com.microservice.sale.entities.Sale;
import com.microservice.sale.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sale")
@CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSale(@RequestBody Sale sale) {
        saleService.saveSale(sale);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @GetMapping("/all")
    public ResponseEntity<?> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok("Sale deleted");
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSale(@PathVariable Long id, @RequestBody Sale sale) {
        saleService.updateSale(id, sale);
        return ResponseEntity.ok("Sale updated");
    }
}

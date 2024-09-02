package com.microservice.sale.service;

import com.microservice.sale.entities.Sale;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ISaleService {
    List<Sale> getAllSales();
    Sale getSaleById(Long id);
    void saveSale(Sale sale);
    void deleteSale(Long id);
    void updateSale(Long id, Sale sale);
}

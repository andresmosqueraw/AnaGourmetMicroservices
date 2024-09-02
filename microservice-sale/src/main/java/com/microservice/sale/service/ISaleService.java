package com.microservice.sale.service;

import com.microservice.sale.entities.Sale;

import java.util.List;

public interface ISaleService {
    List<Sale> getAllSales();
    Sale getSaleById(Integer id);
    void saveSale(Sale sale);
    void deleteSale(Integer id);
    void updateSale(Integer id, Sale sale);
}

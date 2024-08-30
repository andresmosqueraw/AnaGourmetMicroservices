package com.microservice.sale.service;

import com.microservice.sale.entities.Sale;
import com.microservice.sale.persistence.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements ISaleService{

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> getAllSales() {
        return (List<Sale>) saleRepository.findAll();
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    @Override
    public void updateSale(Long id, Sale sale) {
        Sale saleToUpdate = saleRepository.findById(id).orElseThrow();
        saleToUpdate.setCustomerId(sale.getCustomerId());
        saleToUpdate.setTypeLunch(sale.getTypeLunch());
        saleToUpdate.setQuantity(sale.getQuantity());
        saleToUpdate.setTotalSale(sale.getTotalSale());
        saleToUpdate.setSaleDate(sale.getSaleDate());
        saleRepository.save(saleToUpdate);
    }
}

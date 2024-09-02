package com.microservice.sale.service;

import com.microservice.sale.entities.Sale;
import com.microservice.sale.persistence.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SaleServiceImpl implements ISaleService{

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findByStatusSales(1);
    }

    @Override
    public Sale getSaleById(Integer id) {
        return saleRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void saveSale(Sale sale) {
        // Mirar como se puede conectar con el servicio del
        // cliente pasar el dato cuando se efectue la venta
        if (sale.getCreatedAt() == null) {
            sale.setCreatedAt(Instant.now());
        }
        saleRepository.save(sale);
    }

    @Override
    @Transactional
    public void deleteSale(Integer id) {
       Sale saleToUpdate = saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada por ID " + id));
       saleToUpdate.setStatusSales(0);
       saleRepository.save(saleToUpdate);
    }

    @Override
    public void updateSale(Integer id, Sale sale) {
        Sale saleToUpdate = saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        // Actualiza solo si el nuevo valor no es nulo
        if (sale.getCustomerId() != null) {
            //customer_id y user_id son los mismos
            saleToUpdate.setCustomerId(sale.getCustomerId());
        }
        if (sale.getTypeLunch() != null) {
            saleToUpdate.setTypeLunch(sale.getTypeLunch());
        }
        if (sale.getQuantity() != null) {
            saleToUpdate.setQuantity(sale.getQuantity());
        }
        if (sale.getTotalSale() != null) {
            saleToUpdate.setTotalSale(sale.getTotalSale());
        }
        if (sale.getSaleDate() != null) {
            saleToUpdate.setSaleDate(sale.getSaleDate());
        }
        if (sale.getStatusSales() != null) {
            saleToUpdate.setStatusSales(sale.getStatusSales());
        }
        saleRepository.save(saleToUpdate);
    }
}

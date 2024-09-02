package com.microservice.supplier.service;

import com.microservice.supplier.entities.Supplier;
import com.microservice.supplier.persistence.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findByStatusSupplier(1);
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveSupplier(Supplier supplier) {
        if (supplier.getSupplierProduct() == null || supplier.getSupplierProduct().isEmpty()) {
            throw new IllegalArgumentException("Supplier must have at least one product.");
        }
        if(supplier.getCreatedAt() == null) {
            supplier.setCreatedAt(Instant.now());
        }
        supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplierToUpdate = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Proveedor no encontrado por ID " + id));
        supplierToUpdate.setStatusSupplier(0);
        supplierRepository.save(supplierToUpdate);
    }

    @Override
    public void updateSupplier(Long id, Supplier supplier) {
        Supplier supplierToUpdate = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found"));

        // Actualiza solo si el nuevo valor no es nulo
        if (supplier.getSupplierName() != null) {
            supplierToUpdate.setSupplierName(supplier.getSupplierName());
        }
        if (supplier.getSupplierProduct() != null) {
            supplierToUpdate.setSupplierProduct(supplier.getSupplierProduct());
        }
        if (supplier.getPhone() != null) {
            supplierToUpdate.setPhone(supplier.getPhone());
        }
        if (supplier.getStatusSupplier() != null) {
            supplierToUpdate.setStatusSupplier(supplier.getStatusSupplier());
        }

        supplierRepository.save(supplierToUpdate);
    }

}

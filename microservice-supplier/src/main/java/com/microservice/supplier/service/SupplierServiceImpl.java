package com.microservice.supplier.service;

import com.microservice.supplier.entities.Supplier;
import com.microservice.supplier.persistence.SupplierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Supplier> getAllSuppliers() {
        return (List<Supplier>) supplierRepository.findAll();
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    @Transactional
    public void deleteSupplier(Long id) {
        Supplier supplierToUpdate = supplierRepository.findById(id).orElseThrow(() -> new RuntimeException("Supplier not found with id " + id));
        supplierToUpdate.setStatusSupplier(0);  // Asumiendo que "inactivo" es un valor válido para el campo status_supplier
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

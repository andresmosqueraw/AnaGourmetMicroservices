package com.microservice.supplier.service;

import com.microservice.supplier.entities.Supplier;
import com.microservice.supplier.persistence.SupplierRepository;
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
    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public void updateSupplier(Long id, Supplier supplier) {
        Supplier supplierToUpdate = supplierRepository.findById(id).orElseThrow();
        supplierToUpdate.setSupplierName(supplier.getSupplierName());
        supplierToUpdate.setSuppliedProduct(supplier.getSuppliedProduct());
        supplierToUpdate.setPhone(supplier.getPhone());
        supplierRepository.save(supplierToUpdate);
    }
}

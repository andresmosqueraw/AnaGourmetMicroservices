package com.microservice.supplier.service;

import com.microservice.supplier.entities.Supplier;

import java.util.List;

public interface ISupplierService {

    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(Long id);
    Supplier saveSupplier(Supplier supplier);
    void deleteSupplier(Long id);
    void updateSupplier(Long id, Supplier supplier);

}

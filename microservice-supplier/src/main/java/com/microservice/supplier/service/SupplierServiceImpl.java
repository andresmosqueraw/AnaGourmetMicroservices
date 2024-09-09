package com.microservice.supplier.service;

import com.microservice.supplier.entities.Supplier;
import com.microservice.supplier.persistence.SupplierRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    public Supplier saveSupplier(Supplier supplier) {
        if (supplier.getSupplierProduct() == null || supplier.getSupplierProduct().isEmpty()) {
            throw new IllegalArgumentException("Supplier must have at least one product.");
        }
        if(supplier.getCreatedAt() == null) {
            supplier.setCreatedAt(Instant.now());
        }
        supplier.setIpAddress(getClientIpAddress());
        return supplierRepository.save(supplier);
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

    private String getClientIpAddress() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        System.out.println("Client IP Address: " + ipAddress);
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        System.out.println("Client IP Address: " + ipAddress);
        return ipAddress;
    }

}

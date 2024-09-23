package com.microservice.supplier.service;

import com.microservice.supplier.entities.Supplier;
import com.microservice.supplier.persistence.SupplierRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplierServiceImplTest {

    @Mock
    private HttpServletRequest httpServletRequest;


    @InjectMocks
    private SupplierServiceImpl supplierService;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(httpServletRequest));

        when(httpServletRequest.getHeader("X-FORWARDED-FOR")).thenReturn("127.0.0.1");
        when(httpServletRequest.getRemoteAddr()).thenReturn("127.0.0.1");
    }


    @Test
    void testGetAllSuppliers() {
        Supplier supplier1 = new Supplier();
        supplier1.setSupplierId(1L);
        supplier1.setSupplierName("Supplier 1");
        supplier1.setSupplierProduct("Product 1");
        supplier1.setStatusSupplier(1);

        Supplier supplier2 = new Supplier();
        supplier2.setSupplierId(2L);
        supplier2.setSupplierName("Supplier 2");
        supplier2.setSupplierProduct("Product 2");
        supplier2.setStatusSupplier(1);

        when(supplierRepository.findByStatusSupplier(1)).thenReturn(Arrays.asList(supplier1, supplier2));

        List<Supplier> suppliers = supplierService.getAllSuppliers();

        assertNotNull(suppliers);
        assertEquals(2, suppliers.size());
        verify(supplierRepository, times(1)).findByStatusSupplier(1);
    }

    @Test
    void testGetSupplierById() {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        supplier.setSupplierName("Supplier 1");
        supplier.setSupplierProduct("Product 1");

        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));

        Supplier foundSupplier = supplierService.getSupplierById(1L);

        assertNotNull(foundSupplier);
        assertEquals("Supplier 1", foundSupplier.getSupplierName());
        verify(supplierRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierName("Supplier 1");
        supplier.setSupplierProduct("Product 1");

        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        Supplier savedSupplier = supplierService.saveSupplier(supplier);

        assertNotNull(savedSupplier);
        assertEquals("Supplier 1", savedSupplier.getSupplierName());
        verify(supplierRepository, times(1)).save(any(Supplier.class));
    }

    @Test
    void testDeleteSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        supplier.setStatusSupplier(1);

        when(supplierRepository.findById(1L)).thenReturn(Optional.of(supplier));
        when(supplierRepository.save(any(Supplier.class))).thenReturn(supplier);

        supplierService.deleteSupplier(1L);

        assertEquals(0, supplier.getStatusSupplier());
        verify(supplierRepository, times(1)).findById(1L);
        verify(supplierRepository, times(1)).save(supplier);
    }

    @Test
    void testUpdateSupplier() {
        Supplier existingSupplier = new Supplier();
        existingSupplier.setSupplierId(1L);
        existingSupplier.setSupplierName("Supplier 1");
        existingSupplier.setSupplierProduct("Product 1");

        Supplier updatedSupplier = new Supplier();
        updatedSupplier.setSupplierName("Updated Supplier");
        updatedSupplier.setSupplierProduct("Updated Product");

        when(supplierRepository.findById(1L)).thenReturn(Optional.of(existingSupplier));
        when(supplierRepository.save(any(Supplier.class))).thenReturn(existingSupplier);

        supplierService.updateSupplier(1L, updatedSupplier);

        assertEquals("Updated Supplier", existingSupplier.getSupplierName());
        assertEquals("Updated Product", existingSupplier.getSupplierProduct());
        verify(supplierRepository, times(1)).findById(1L);
        verify(supplierRepository, times(1)).save(existingSupplier);
    }
}
package com.microservice.supplier.controller;

import com.microservice.supplier.entities.Supplier;
import com.microservice.supplier.service.ISupplierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.Instant;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SupplierControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SupplierController supplierController;

    @Mock
    private ISupplierService supplierService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
    }

    @Test
    void testSaveSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        supplier.setSupplierName("Supplier 1");
        supplier.setSupplierProduct("Product 1");
        supplier.setCreatedAt(Instant.now());

        when(supplierService.saveSupplier(any(Supplier.class))).thenReturn(supplier);

        mockMvc.perform(post("/api/supplier/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"supplierName\":\"Supplier 1\",\"supplierProduct\":\"Product 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.supplierId").value(1))
                .andExpect(jsonPath("$.supplierName").value("Supplier 1"));

        verify(supplierService, times(1)).saveSupplier(any(Supplier.class));
    }

    @Test
    void testGetAllSuppliers() throws Exception {
        Supplier supplier1 = new Supplier();
        supplier1.setSupplierId(1L);
        supplier1.setSupplierName("Supplier 1");
        supplier1.setSupplierProduct("Product 1");

        Supplier supplier2 = new Supplier();
        supplier2.setSupplierId(2L);
        supplier2.setSupplierName("Supplier 2");
        supplier2.setSupplierProduct("Product 2");

        when(supplierService.getAllSuppliers()).thenReturn(Arrays.asList(supplier1, supplier2));

        mockMvc.perform(get("/api/supplier/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].supplierId").value(1))
                .andExpect(jsonPath("$[1].supplierId").value(2));

        verify(supplierService, times(1)).getAllSuppliers();
    }

    @Test
    void testFindSupplierById() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        supplier.setSupplierName("Supplier 1");
        supplier.setSupplierProduct("Product 1");

        when(supplierService.getSupplierById(1L)).thenReturn(supplier);

        mockMvc.perform(get("/api/supplier/search/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.supplierId").value(1))
                .andExpect(jsonPath("$.supplierName").value("Supplier 1"));

        verify(supplierService, times(1)).getSupplierById(1L);
    }

    @Test
    void testDeleteSupplier() throws Exception {
        doNothing().when(supplierService).deleteSupplier(1L);

        mockMvc.perform(delete("/api/supplier/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Proveedor Eliminado"));

        verify(supplierService, times(1)).deleteSupplier(1L);
    }

    @Test
    void testUpdateSupplier() throws Exception {
        doNothing().when(supplierService).updateSupplier(eq(1L), any(Supplier.class));

        mockMvc.perform(put("/api/supplier/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"supplierName\":\"Updated Supplier\",\"supplierProduct\":\"Updated Product\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Supplier updated"));

        verify(supplierService, times(1)).updateSupplier(eq(1L), any(Supplier.class));
    }
}
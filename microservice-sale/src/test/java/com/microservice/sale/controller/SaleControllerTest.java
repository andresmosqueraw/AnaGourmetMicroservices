package com.microservice.sale.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.microservice.sale.entities.Sale;
import com.microservice.sale.service.ISaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SaleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ISaleService saleService;

    @InjectMocks
    private SaleController saleController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(saleController).build();

        // Inicializar ObjectMapper con JavaTimeModule
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        // Deshabilitar escritura de fechas como timestamps (opcional)
        // objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void testSaveSale() throws Exception {
        Sale sale = createSale(null);

        doNothing().when(saleService).saveSale(any(Sale.class));

        mockMvc.perform(post("/api/sale/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sale)))
                .andExpect(status().isCreated());

        verify(saleService, times(1)).saveSale(any(Sale.class));
    }

    // ... (resto de los métodos de prueba)

    // Método auxiliar para crear objetos Sale
    private Sale createSale(Integer id) {
        Sale sale = new Sale();
        sale.setId(id);
        sale.setCustomerId(1);
        sale.setTypeLunch("Lunch Type");
        sale.setQuantity(2);
        sale.setTotalSale(new BigDecimal("100.00"));
        sale.setSaleDate(LocalDate.now());
        sale.setCreatedAt(Instant.now());
        sale.setUserId(1);
        sale.setStatusSales(1);
        return sale;
    }
}
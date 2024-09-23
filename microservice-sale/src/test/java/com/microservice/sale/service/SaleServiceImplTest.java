package com.microservice.sale.service;

import com.microservice.sale.entities.Sale;
import com.microservice.sale.persistence.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaleServiceImplTest {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Mock
    private SaleRepository saleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSales() {
        Sale sale1 = createSale(1);
        Sale sale2 = createSale(2);

        when(saleRepository.findByStatusSales(1L)).thenReturn(Arrays.asList(sale1, sale2));

        List<Sale> sales = saleService.getAllSales();

        assertNotNull(sales);
        assertEquals(2, sales.size());
        verify(saleRepository, times(1)).findByStatusSales(1L);
    }

    @Test
    void testGetSaleById() {
        Sale sale = createSale(1);

        when(saleRepository.findById(1L)).thenReturn(Optional.of(sale));

        Sale foundSale = saleService.getSaleById(1L);

        assertNotNull(foundSale);
        assertEquals(1, foundSale.getId());
        verify(saleRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveSale() {
        Sale sale = createSale(null);

        when(saleRepository.save(any(Sale.class))).thenAnswer(invocation -> {
            Sale savedSale = invocation.getArgument(0);
            savedSale.setId(1);
            return savedSale;
        });

        saleService.saveSale(sale);

        assertNotNull(sale.getCreatedAt());
        assertEquals(1, sale.getId());
        verify(saleRepository, times(1)).save(sale);
    }

    @Test
    void testDeleteSale() {
        Sale sale = createSale(1);

        when(saleRepository.findById(1L)).thenReturn(Optional.of(sale));
        when(saleRepository.save(any(Sale.class))).thenReturn(sale);

        saleService.deleteSale(1L);

        assertEquals(0, sale.getStatusSales());
        verify(saleRepository, times(1)).findById(1L);
        verify(saleRepository, times(1)).save(sale);
    }

    @Test
    void testUpdateSale() {
        Sale existingSale = createSale(1);
        Sale updatedSale = new Sale();
        updatedSale.setQuantity(5);
        updatedSale.setTotalSale(new BigDecimal("150.00"));

        when(saleRepository.findById(1L)).thenReturn(Optional.of(existingSale));
        when(saleRepository.save(any(Sale.class))).thenReturn(existingSale);

        saleService.updateSale(1L, updatedSale);

        assertEquals(5, existingSale.getQuantity());
        assertEquals(new BigDecimal("150.00"), existingSale.getTotalSale());
        verify(saleRepository, times(1)).findById(1L);
        verify(saleRepository, times(1)).save(existingSale);
    }

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
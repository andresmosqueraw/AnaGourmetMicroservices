package com.microservice.sale.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Sale {
    private Long saleId;
    private Long customerId;
    private Long productId;
    private Integer quantity;
    private BigDecimal totalSale;
    private LocalDate saleDate;
    private LocalDateTime createdAt;
}

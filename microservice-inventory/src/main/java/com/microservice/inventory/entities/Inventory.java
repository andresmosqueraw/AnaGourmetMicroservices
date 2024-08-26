package com.microservice.inventory.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Inventory {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Long supplierId;
    private LocalDateTime createdAt;
}
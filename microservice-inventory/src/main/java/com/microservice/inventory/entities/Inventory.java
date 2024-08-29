package com.microservice.inventory.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "inventories")
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private Long supplierId;
    private LocalDateTime createdAt;
    private int userId;
    private int statusInventory;
}
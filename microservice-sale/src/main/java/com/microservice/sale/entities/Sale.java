package com.microservice.sale.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "sales")
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long saleId;
    private Long customerId;
    private String typeLunch;
    private Integer quantity;
    private BigDecimal totalSale;
    private LocalDate saleDate;
    private LocalDateTime createdAt;
    private int userId;
    private int statusSales;
}

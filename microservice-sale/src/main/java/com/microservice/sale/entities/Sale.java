package com.microservice.sale.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('sales_sale_id_seq')")
    @Column(name = "sale_id", nullable = false)
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @jakarta.validation.constraints.Size(max = 255)
    @jakarta.validation.constraints.NotNull
    @Column(name = "type_lunch", nullable = false)
    private String typeLunch;

    @jakarta.validation.constraints.NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @jakarta.validation.constraints.NotNull
    @Column(name = "total_sale", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalSale;

    @jakarta.validation.constraints.NotNull
    @Column(name = "sale_date", nullable = false)
    private LocalDate saleDate;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "status_sales")
    private Integer statusSales;

}
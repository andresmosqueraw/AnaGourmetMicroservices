package com.microservice.supplier.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "suppliers")
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "")

    private Long supplierId;
    private String supplierName;
    private String suppliedProduct;
    private String phone;
    private LocalDateTime createdAt;
    private int userId;
    private int statusSupplier;
}

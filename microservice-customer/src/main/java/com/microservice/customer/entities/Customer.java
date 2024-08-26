package com.microservice.customer.entities;

import java.time.LocalDateTime;

public class Customer {
    private Long customerId;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
}

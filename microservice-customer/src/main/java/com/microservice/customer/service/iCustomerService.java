package com.microservice.customer.service;

import com.microservice.customer.entities.Customer;

import java.util.List;

public interface iCustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    void saveCustomer(Customer customer);
    void deleteCustomer(Long id);
    void updateCustomer(Long id, Customer customer);
}

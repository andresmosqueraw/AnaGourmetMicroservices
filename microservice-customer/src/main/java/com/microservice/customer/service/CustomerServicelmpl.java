package com.microservice.customer.service;

import com.microservice.customer.entities.Customer;
import com.microservice.customer.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServicelmpl implements iCustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {return (List<Customer>) customerRepository.findAll();}

    @Override
    public Customer getCustomerById(Long id) {return customerRepository.findById(id).orElseThrow();}

    @Override
    public Customer saveCustomer(Customer customer) {customerRepository.save(customer); return customer;}

    @Override
    public void deleteCustomer(Long id) {}

    @Override
    public void updateCustomer(Long id, Customer customer) {}
}

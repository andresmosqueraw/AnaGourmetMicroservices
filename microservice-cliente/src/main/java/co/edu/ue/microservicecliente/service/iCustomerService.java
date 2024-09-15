package co.edu.ue.microservicecliente.service;

import co.edu.ue.microservicecliente.entities.Customer;

import java.util.List;

public interface iCustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Long id);
    void updateCustomer(Long id, Customer customer);
}

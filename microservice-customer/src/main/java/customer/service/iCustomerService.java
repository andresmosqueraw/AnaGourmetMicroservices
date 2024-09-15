package customer.service;

import customer.entities.Customer;

import java.util.List;

public interface iCustomerService {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Long id);
    void updateCustomer(Long id, Customer customer);
}

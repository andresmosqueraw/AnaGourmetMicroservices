package customer.service;

import customer.entities.Customer;
import customer.persistence.CustomerRepository;
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
    public Customer saveCustomer(Customer customer) {
        if(customer.getCreatedAt() == null){
            customer.setCreatedAt(java.time.Instant.now());
        }
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void deleteCustomer(Long id) {}

    @Override
    public void updateCustomer(Long id, Customer customer) {}
}

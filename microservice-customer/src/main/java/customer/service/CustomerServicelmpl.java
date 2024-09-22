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
    public List<Customer> getAllCustomers() {
        return customerRepository.findAllByStatusCustomer(1);
    }

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
    public void deleteCustomer(Long id) {
        Customer customerToDelete = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        customerToDelete.setStatusCustomer(0);
        customerRepository.save(customerToDelete);
    }

    @Override
    public void updateCustomer(Long id, Customer customerDetails) {
        Customer customerToUpdate = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));

        // Actualiza cada uno de los campos necesarios
        customerToUpdate.setName(customerDetails.getName());
        customerToUpdate.setEmail(customerDetails.getEmail());
        customerToUpdate.setPhone(customerDetails.getPhone());
        // No actualizamos createdAt porque es una fecha de creación que no debe cambiar.
        // No actualizamos userId a menos que la lógica de negocio lo requiera.

        customerToUpdate.setStatusCustomer(customerDetails.getStatusCustomer());

        customerRepository.save(customerToUpdate);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}

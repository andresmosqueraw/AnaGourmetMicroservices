package customer.service;

import customer.entities.Customer;
import customer.persistence.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServicelmpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCustomers() {
        Customer customer1 = createCustomer(1);
        Customer customer2 = createCustomer(2);

        when(customerRepository.findAllByStatusCustomer(1)).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertEquals(2, customers.size());
        verify(customerRepository, times(1)).findAllByStatusCustomer(1);
    }

    @Test
    void testGetCustomerById() {
        Customer customer = createCustomer(1);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById(1L);

        assertNotNull(foundCustomer);
        assertEquals(1, foundCustomer.getId());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveCustomer() {
        Customer customer = createCustomer(null);

        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> {
            Customer savedCustomer = invocation.getArgument(0);
            savedCustomer.setId(1);
            return savedCustomer;
        });

        Customer savedCustomer = customerService.saveCustomer(customer);

        assertNotNull(savedCustomer.getCreatedAt());
        assertEquals(1, savedCustomer.getId());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testDeleteCustomer() {
        Customer customer = createCustomer(1);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        customerService.deleteCustomer(1L);

        assertEquals(0, customer.getStatusCustomer());
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testUpdateCustomer() {
        Customer existingCustomer = createCustomer(1);
        Customer updatedCustomer = new Customer();
        updatedCustomer.setName("Updated Name");
        updatedCustomer.setEmail("updated@example.com");
        updatedCustomer.setPhone("1234567890");
        updatedCustomer.setStatusCustomer(1);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        customerService.updateCustomer(1L, updatedCustomer);

        assertEquals("Updated Name", existingCustomer.getName());
        assertEquals("updated@example.com", existingCustomer.getEmail());
        assertEquals("1234567890", existingCustomer.getPhone());
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(existingCustomer);
    }

    @Test
    void testGetCustomerByEmail() {
        Customer customer = createCustomer(1);

        when(customerRepository.findByEmail("test@example.com")).thenReturn(customer);

        Customer foundCustomer = customerService.getCustomerByEmail("test@example.com");

        assertNotNull(foundCustomer);
        assertEquals("test@example.com", foundCustomer.getEmail());
        verify(customerRepository, times(1)).findByEmail("test@example.com");
    }

    // Método auxiliar para crear objetos Customer
    private Customer createCustomer(Integer id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("Test Customer");
        customer.setEmail("test@example.com");
        customer.setPhone("555-1234");
        customer.setCreatedAt(Instant.now());
        customer.setUserId(1);
        customer.setStatusCustomer(1);
        return customer;
    }
}
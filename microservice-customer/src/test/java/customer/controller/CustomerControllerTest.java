package customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import customer.entities.Customer;
import customer.service.iCustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private iCustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Inicializar ObjectMapper con JavaTimeModule
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testSaveCustomer() throws Exception {
        Customer customer = createCustomer(null);
        Customer savedCustomer = createCustomer(1);

        when(customerService.saveCustomer(any(Customer.class))).thenReturn(savedCustomer);

        mockMvc.perform(post("/api/customer/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(customerService, times(1)).saveCustomer(any(Customer.class));
    }

    @Test
    void testGetAllCustomers() throws Exception {
        Customer customer1 = createCustomer(1);
        Customer customer2 = createCustomer(2);

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get("/api/customer/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    void testFindCustomerById() throws Exception {
        Customer customer = createCustomer(1);

        when(customerService.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/api/customer/search/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    void testFindCustomerByEmail() throws Exception {
        Customer customer = createCustomer(1);

        when(customerService.getCustomerByEmail("test@example.com")).thenReturn(customer);

        mockMvc.perform(get("/api/customer/search/email/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));

        verify(customerService, times(1)).getCustomerByEmail("test@example.com");
    }

    @Test
    void testDeleteCustomer() throws Exception {
        doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/api/customer/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente Eliminado"));

        verify(customerService, times(1)).deleteCustomer(1L);
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("Updated Name");
        customer.setEmail("updated@example.com");
        customer.setPhone("1234567890");
        customer.setStatusCustomer(1);

        doNothing().when(customerService).updateCustomer(eq(1L), any(Customer.class));

        Map<String, String> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "Cliente Actualizado");

        mockMvc.perform(put("/api/customer/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Cliente Actualizado"));

        verify(customerService, times(1)).updateCustomer(eq(1L), any(Customer.class));
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
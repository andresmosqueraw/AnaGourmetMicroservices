package com.microservice.customer.controller;


import com.microservice.customer.entities.Customer;
import com.microservice.customer.service.iCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
    @RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private iCustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
        return ResponseEntity.ok("Customer updated");
    }
}

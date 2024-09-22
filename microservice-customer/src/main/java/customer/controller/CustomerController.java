package customer.controller;

import customer.entities.Customer;
import customer.service.iCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    @Autowired
    private iCustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/search/email/{email}")
    public ResponseEntity<?> findCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustsomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Cliente Eliminado");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
        return ResponseEntity.ok("Cliente Actualizado");
    }


}

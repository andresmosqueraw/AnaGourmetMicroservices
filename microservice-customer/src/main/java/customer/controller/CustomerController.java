package customer.controller;

import customer.entities.Customer;
import customer.service.iCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
public class CustomerController {

    @Autowired
    private iCustomerService customerService;

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @GetMapping("/search/email/{email}")
    public ResponseEntity<?> findCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustsomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Cliente Eliminado");
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);

        // Crear un objeto que contiene el mensaje
        Map<String, String> response = new HashMap<>();
        response.put("message", "Cliente Actualizado");

        // Devolver el objeto JSON en lugar de una cadena
        return ResponseEntity.ok(response);
    }


}

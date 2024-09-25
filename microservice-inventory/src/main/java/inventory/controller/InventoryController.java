package inventory.controller;

import inventory.entities.Inventory;
import inventory.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
public class InventoryController {

    @Autowired
    private IInventoryService inventoryService;

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveInventory(@RequestBody Inventory inventory) {
        inventoryService.saveInventory(inventory);
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @GetMapping("/all")
    public ResponseEntity<?> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @GetMapping("/search/{id}")
    public ResponseEntity<?> findInventoryById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok("Inventory deleted");
    }

    @CrossOrigin(origins = {"http://localhost:4200", "https://66f39bc50ee36daff00ec348--anagourmet.netlify.app","http://35.188.66.64:8761", "http://35.188.66.64:8080", "http://35.188.66.64:8100", "http://35.188.66.64:8090", "http://35.188.66.64:8101", "http://35.188.66.64:8201", "http://35.188.66.64:8889", "*"}, allowedHeaders = "*")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        inventoryService.updateInventory(id, inventory);
        return ResponseEntity.ok("Inventory updated");
    }
}

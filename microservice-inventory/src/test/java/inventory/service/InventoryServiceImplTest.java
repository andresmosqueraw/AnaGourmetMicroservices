package inventory.service;

import inventory.entities.Inventory;
import inventory.persistence.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventoryServiceImplTest {

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Mock
    private InventoryRepository inventoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllInventories() {
        Inventory inventory1 = createInventory(1);
        Inventory inventory2 = createInventory(2);

        when(inventoryRepository.findByStatusInventory(1)).thenReturn(Arrays.asList(inventory1, inventory2));

        List<Inventory> inventories = inventoryService.getAllInventories();

        assertNotNull(inventories);
        assertEquals(2, inventories.size());
        verify(inventoryRepository, times(1)).findByStatusInventory(1);
    }

    @Test
    void testGetInventoryById() {
        Inventory inventory = createInventory(1);

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        Inventory foundInventory = inventoryService.getInventoryById(1L);

        assertNotNull(foundInventory);
        assertEquals(1, foundInventory.getProductId());
        verify(inventoryRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveInventory() {
        Inventory inventory = createInventory(null);

        when(inventoryRepository.save(any(Inventory.class))).thenAnswer(invocation -> {
            Inventory savedInventory = invocation.getArgument(0);
            savedInventory.setProductId(1);
            return savedInventory;
        });

        inventoryService.saveInventory(inventory);

        assertNotNull(inventory.getCreatedAt());
        assertEquals(1, inventory.getProductId());
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    void testDeleteInventory() {
        Inventory inventory = createInventory(1);

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        inventoryService.deleteInventory(1L);

        assertEquals(0, inventory.getStatusInventory());
        verify(inventoryRepository, times(1)).findById(1L);
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    void testUpdateInventory() {
        Inventory existingInventory = createInventory(1);
        Inventory updatedInventory = new Inventory();
        updatedInventory.setQuantity(50);
        updatedInventory.setUnitPrice(new BigDecimal("200.00"));

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(existingInventory));
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(existingInventory);

        inventoryService.updateInventory(1L, updatedInventory);

        assertEquals(50, existingInventory.getQuantity());
        assertEquals(new BigDecimal("200.00"), existingInventory.getUnitPrice());
        verify(inventoryRepository, times(1)).findById(1L);
        verify(inventoryRepository, times(1)).save(existingInventory);
    }

    // Método auxiliar para crear objetos Inventory
    private Inventory createInventory(Integer id) {
        Inventory inventory = new Inventory();
        inventory.setProductId(id);
        inventory.setProductName("Product Name");
        inventory.setQuantity(10);
        inventory.setUnitPrice(new BigDecimal("100.00"));
        inventory.setSupplierId(1);
        inventory.setCreatedAt(Instant.now());
        inventory.setUserId(1);
        inventory.setStatusInventory(1);
        return inventory;
    }
}
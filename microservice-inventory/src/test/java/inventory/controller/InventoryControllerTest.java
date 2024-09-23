package inventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import inventory.entities.Inventory;
import inventory.service.IInventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class InventoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IInventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();

        // Inicializar ObjectMapper con JavaTimeModule
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testSaveInventory() throws Exception {
        Inventory inventory = createInventory(null);

        doNothing().when(inventoryService).saveInventory(any(Inventory.class));

        mockMvc.perform(post("/api/inventory/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventory)))
                .andExpect(status().isCreated());

        verify(inventoryService, times(1)).saveInventory(any(Inventory.class));
    }

    @Test
    void testGetAllInventories() throws Exception {
        Inventory inventory1 = createInventory(1);
        Inventory inventory2 = createInventory(2);

        when(inventoryService.getAllInventories()).thenReturn(Arrays.asList(inventory1, inventory2));

        mockMvc.perform(get("/api/inventory/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(inventoryService, times(1)).getAllInventories();
    }

    @Test
    void testFindInventoryById() throws Exception {
        Inventory inventory = createInventory(1);

        when(inventoryService.getInventoryById(1L)).thenReturn(inventory);

        mockMvc.perform(get("/api/inventory/search/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1));

        verify(inventoryService, times(1)).getInventoryById(1L);
    }

    @Test
    void testDeleteInventory() throws Exception {
        doNothing().when(inventoryService).deleteInventory(1L);

        mockMvc.perform(delete("/api/inventory/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Inventory deleted"));

        verify(inventoryService, times(1)).deleteInventory(1L);
    }

    @Test
    void testUpdateInventory() throws Exception {
        Inventory inventory = new Inventory();
        inventory.setQuantity(50);

        doNothing().when(inventoryService).updateInventory(eq(1L), any(Inventory.class));

        mockMvc.perform(put("/api/inventory/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventory)))
                .andExpect(status().isOk())
                .andExpect(content().string("Inventory updated"));

        verify(inventoryService, times(1)).updateInventory(eq(1L), any(Inventory.class));
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
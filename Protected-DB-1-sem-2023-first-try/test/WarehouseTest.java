package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.example.Vendor;
import org.example.Warehouse;

public class WarehouseTest {

    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = new Warehouse();
    }

    // Закрытие EntityManager и EntityManagerFactory в конце тестов
    @AfterEach
    public void tearDown() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Test
    void testGetters() {
        warehouse.setId(1);
        warehouse.setName("WarehouseName");
        warehouse.setQuantity(100);
        Vendor vendor = new Vendor("Country", 12345, "VendorName");
        warehouse.setVendor(vendor);

        assertEquals(1, warehouse.getId());
        assertEquals("WarehouseName", warehouse.getName());
        assertEquals(100, warehouse.getQuantity());
        assertEquals(vendor, warehouse.getVendor());
    }

    @Test
    void testSetName() {
        assertDoesNotThrow(() -> warehouse.setName("ValidName"));
        assertThrows(IllegalArgumentException.class, () -> warehouse.setName(null));
        assertThrows(IllegalArgumentException.class, () -> warehouse.setName(""));
    }

    @Test
    void testSetQuantity() {
        assertDoesNotThrow(() -> warehouse.setQuantity(0));
        assertDoesNotThrow(() -> warehouse.setQuantity(100));
        assertThrows(IllegalArgumentException.class, () -> warehouse.setQuantity(-1));
    }

    @Test
    void testSetVendor() {
        Vendor vendor = new Vendor("Country", 12345, "VendorName");

        assertDoesNotThrow(() -> warehouse.setVendor(vendor));
        assertEquals(vendor, warehouse.getVendor());

        assertDoesNotThrow(() -> warehouse.setVendor(null));
        assertNull(warehouse.getVendor());
    }
}

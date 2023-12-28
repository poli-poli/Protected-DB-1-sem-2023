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

public class VendorTest {

    private Vendor vendor;

    @BeforeEach
    void setUp() {
        vendor = new Vendor("Country", 12345, "VendorName");
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
        assertEquals("Country", vendor.getCountry());
        assertEquals(12345, vendor.getArticle());
        assertEquals("VendorName", vendor.getName());
    }

    @Test
    void testSetters() {
        vendor.setCountry("NewCountry");
        vendor.setArticle(54321);
        vendor.setName("NewVendorName");

        assertEquals("NewCountry", vendor.getCountry());
        assertEquals(54321, vendor.getArticle());
        assertEquals("NewVendorName", vendor.getName());
    }

    @Test
    void testInvalidCountry() {
        assertThrows(IllegalArgumentException.class, () -> vendor.setCountry(null));
        assertThrows(IllegalArgumentException.class, () -> vendor.setCountry(""));
    }

    @Test
    void testInvalidArticle() {
        assertThrows(IllegalArgumentException.class, () -> vendor.setArticle(0));
        assertThrows(IllegalArgumentException.class, () -> vendor.setArticle(-123));
    }

    @Test
    void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> vendor.setName(null));
        assertThrows(IllegalArgumentException.class, () -> vendor.setName(""));
    }
}

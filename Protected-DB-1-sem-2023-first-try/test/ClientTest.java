package test;

mport org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Store;
import org.example.Client;
import org.example.Invoice;
public class ClientTest {

    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
    }

      // Закрытие EntityManager и EntityManagerFactory в конце тестов
    @AfterEach
    public void tearDown() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }


    @Test
    public void testSetName_ValidName() {
        client.setName("John");
        assertEquals("John", client.getName());
    }

    @Test
    public void testSetName_NullName() {
        assertThrows(IllegalArgumentException.class, () -> client.setName(null));
    }

    @Test
    public void testSetName_EmptyName() {
        assertThrows(IllegalArgumentException.class, () -> client.setName(""));
    }

    @Test
    public void testSetCountry_ValidCountry() {
        client.setCountry("USA");
        assertEquals("USA", client.getCountry());
    }

    @Test
    public void testSetCountry_NullCountry() {
        assertThrows(IllegalArgumentException.class, () -> client.setCountry(null));
    }

    @Test
    public void testSetCountry_EmptyCountry() {
        assertThrows(IllegalArgumentException.class, () -> client.setCountry(""));
    }

    @Test
    public void testSetPhone_ValidPhone() {
        client.setPhone("123456789");
        assertEquals("123456789", client.getPhone());
    }

    @Test
    public void testSetPhone_NullPhone() {
        assertThrows(IllegalArgumentException.class, () -> client.setPhone(null));
    }

    @Test
    public void testSetPhone_EmptyPhone() {
        assertThrows(IllegalArgumentException.class, () -> client.setPhone(""));
    }

    @Test
    public void testSetInvoices() {
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        client.setInvoices(Arrays.asList(invoice1, invoice2));
        assertEquals(2, client.getInvoices().size());
        assertTrue(client.getInvoices().contains(invoice1));
        assertTrue(client.getInvoices().contains(invoice2));
    }

    @Test
    public void testSetId() {
        client.setId(1);
        assertEquals(1, client.getId());
    }
}

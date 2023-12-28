package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.example.Store;
import org.example.Client;
import org.example.Invoice;

public class InvoiceTest {

    private Invoice invoice;

    @BeforeEach
    public void setUp() {
        invoice = new Invoice();
    }

    // Закрытие EntityManager и EntityManagerFactory в конце тестов
    @AfterEach
    public void tearDown() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Test
    public void testId() {
        invoice.setId(1);
        assertEquals(1, invoice.getId());
    }

    @Test
    public void testDate() {
        Date date = new Date();
        invoice.setDate(date);
        assertEquals(date, invoice.getDate());
    }

    @Test
    public void testPrice() {
        invoice.setPrice(100.0);
        assertEquals(100.0, invoice.getPrice(), 0.001);
    }

    @Test
    public void testStore() {
        Store store = new Store();
        invoice.setStore(store);
        assertEquals(store, invoice.getStore());
    }

    @Test
    public void testClient() {
        Client client = new Client();
        invoice.setClient(client);
        assertEquals(client, invoice.getClient());
    }

    @Test
    public void testEmptyDate() {
        assertThrows(IllegalArgumentException.class, () -> invoice.setDate(null));
    }

    @Test
    public void testNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> invoice.setPrice(-10.0));
    }

    @Test
    public void testNullStore() {
        assertThrows(IllegalArgumentException.class, () -> invoice.setStore(null));
    }

    @Test
    public void testNullClient() {
        assertThrows(IllegalArgumentException.class, () -> invoice.setClient(null));
    }
}

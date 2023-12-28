package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.example.Invoice;
import org.example.Store;

public class StoreTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("your-persistence-unit"); // Замените на ваш UNIT
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testStoreCreation() {
        entityManager.getTransaction().begin();

        // Создание и сохранение магазина
        Store store = new Store();
        store.setName("Магазин 1");
        store.setPhone("123-456-7890");
        entityManager.persist(store);

        entityManager.getTransaction().commit();

        // Проверка, что магазин был успешно сохранен в базе данных
        Store retrievedStore = entityManager.find(Store.class, store.getId());
        assertNotNull(retrievedStore);
        assertEquals("Магазин 1", retrievedStore.getName());
        assertEquals("123-456-7890", retrievedStore.getPhone());
    }

    @Test
    public void testGetInvoicesForStore() {
        entityManager.getTransaction().begin();

        // Создание магазина
        Store store = new Store();
        store.setName("Магазин 2");
        store.setPhone("987-654-3210");
        entityManager.persist(store);

        // Создание счета для магазина
        Invoice invoice = new Invoice();
        invoice.setStore(store);
        entityManager.persist(invoice);

        entityManager.getTransaction().commit();

        // Получение счетов для магазина
        List<Invoice> invoices = store.getInvoices();
        assertNotNull(invoices);
        assertEquals(1, invoices.size());
    }

    // Закрытие EntityManager и EntityManagerFactory в конце тестов
    @AfterEach
    public void tearDown() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }
}

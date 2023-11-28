package test;

mport org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.BaseMethod;
import org.example.Client;

public class BaseMethodTest {

    private static SessionFactory sessionFactory;
    private static Session session;
    private static BaseMethod<Client> clientBaseMethod;

    @BeforeAll
    static void setUpBeforeClass() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        clientBaseMethod = new BaseMethod<>(session, Client.class);
    }

    @AfterAll
    static void tearDownAfterClass() {
        session.close();
        sessionFactory.close();
    }

    @BeforeEach
    void setUp() {
        // Создаем и сохраняем нового клиента перед каждым тестом
        Client client = new Client("Test Client", "Test Country", "123-456-7890");
        clientBaseMethod.save(client);
    }

    @AfterEach
    void tearDown() {
        // Удаляем клиента после каждого теста
        List<Client> clients = clientBaseMethod.find("name", "Test Client");
        if (!clients.isEmpty()) {
            Client client = clients.get(0);
            clientBaseMethod.delete(client);
        }

    // Закрытие EntityManager и EntityManagerFactory в конце тестов
    public void tearDown() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    @Test
    void testGet() {
        List<Client> clients = clientBaseMethod.find("name", "Test Client");
        assertFalse(clients.isEmpty());
        Client client = clients.get(0);

        Client retrievedClient = clientBaseMethod.get(client.getId());
        assertNotNull(retrievedClient);
        assertEquals(client.getName(), retrievedClient.getName());
    }

    @Test
    void testFind() {
        List<Client> clients = clientBaseMethod.find("name", "Test Client");
        assertFalse(clients.isEmpty());
        Client client = clients.get(0);

        assertEquals("Test Client", client.getName());

        List<Client> emptyList = clientBaseMethod.find("name", "Nonexistent Client");
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void testGetAll() {
        List<Client> allClients = clientBaseMethod.getAll();
        assertFalse(allClients.isEmpty());
    }

    @Test
    void testSave() {
        List<Client> clients = clientBaseMethod.find("name", "Test Client");
        assertFalse(clients.isEmpty());
        Client client = clients.get(0);

        assertEquals("Test Client", client.getName());
    }

    @Test
    void testDelete() {
        List<Client> clients = clientBaseMethod.find("name", "Test Client");
        assertFalse(clients.isEmpty());
        Client client = clients.get(0);

        clientBaseMethod.delete(client);

        List<Client> deletedClients = clientBaseMethod.find("name", "Test Client");
        assertTrue(deletedClients.isEmpty());
    }

}

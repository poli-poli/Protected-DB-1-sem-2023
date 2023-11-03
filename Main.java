package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        // Создаем конфигурацию Hibernate
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        // Создаем фабрику сессий
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Создаем сессию для взаимодействия с базой данных
        Session session = sessionFactory.openSession();

        try {
            // Создание экземпляров BaseMethod для каждой сущности
            BaseMethod<Store> storeMethod = new BaseMethod<>(session, Store.class);
            BaseMethod<Warehouse> warehouseMethod = new BaseMethod<>(session, Warehouse.class);
            BaseMethod<Client> clientMethod = new BaseMethod<>(session, Client.class);
            BaseMethod<Vendor> vendorMethod = new BaseMethod<>(session, Vendor.class);
            BaseMethod<Invoice> invoiceMethod = new BaseMethod<>(session, Invoice.class);

            // Создание и сохранение объектов


            Warehouse warehouse = new Warehouse("Warehouse Name", 100);
            warehouseMethod.save(warehouse);

            Store store = new Store("Store Name", "123-456-7890", warehouse);
            storeMethod.save(store);

            Client client = new Client("Client Name", "Client Country", "987-654-3210");
            clientMethod.save(client);

            Vendor vendor = new Vendor("Vendor Country", 123456, "Vendor Name");
            vendorMethod.save(vendor);

            Invoice invoice = new Invoice(new Date(), 100.0, store, client);
            invoiceMethod.save(invoice);

            store.setWarehouse(warehouse);
            invoice.setStore(store);
            invoice.setClient(client);
            warehouse.setVendor(vendor);

            // Получение и вывод всех объектов определенной сущности
            List<Store> allStores = storeMethod.getAll();
            for (Store s : allStores) {
                System.out.println("Store Name: " + s.getName());
                System.out.println("Phone: " + s.getPhone());
            }

            // По аналогии получение и вывод других сущностей

        } finally {
            // Закрываем сессию и фабрику сессий
            session.close();
            sessionFactory.close();
        }
    }
}
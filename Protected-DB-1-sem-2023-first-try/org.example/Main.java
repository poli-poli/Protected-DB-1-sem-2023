
package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
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

            // Пример создания и сохранения склада (Warehouse)
            Warehouse warehouse = new Warehouse("Название склада", 100);
            warehouseMethod.save(warehouse);

            // Пример создания и сохранения магазина (Store)
            Store store = new Store("Название магазина", "123-456-7890", warehouse);
            storeMethod.save(store);

            // Пример создания и сохранения клиента (Client)
            Client client = new Client("Имя клиента", "Страна клиента", "987-654-3210");
            clientMethod.save(client);

            // Пример создания и сохранения поставщика (Vendor)
            Vendor vendor = new Vendor("Страна поставщика", 123456, "Название поставщика");
            vendorMethod.save(vendor);

            // Пример создания и сохранения счета (Invoice)
            Invoice invoice = new Invoice(new Date(), 100.0, store, client);
            invoiceMethod.save(invoice);

            // Пример установки связей между объектами
            store.setWarehouse(warehouse);
            invoice.setStore(store);
            invoice.setClient(client);
            warehouse.setVendor(vendor);

            // Получение и вывод всех объектов определенной сущности
            List<Store> allStores = storeMethod.getAll();
            for (Store s : allStores) {
                System.out.println("Название магазина: " + s.getName());
                System.out.println("Телефон: " + s.getPhone());
            }

            // По аналогии можно получать и выводить другие сущности

        } finally {
            // Закрываем сессию и фабрику сессий
            session.close();
            sessionFactory.close();
        }
    }
}
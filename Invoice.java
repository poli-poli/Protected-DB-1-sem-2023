package org.example;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * Конструктор для создания объекта Invoice.
     *
     * @param date   Дата создания счета.
     * @param price  Сумма счета.
     * @param store  Магазин, к которому относится счет.
     * @param client Клиент, которому выставлен счет.
     * @throws IllegalArgumentException Если дата, сумма, магазин или клиент не заданы, или сумма меньше или равна нулю.
     */
    public Invoice(Date date, double price, Store store, Client client) {
        // Добавляем проверку ввода: дата, сумма, магазин и клиент не должны быть null.
        if (date == nullstore == null || client == null) {
            throw new IllegalArgumentException("Дата, сумма, магазин и клиент должны быть указаны, и сумма должна быть больше нуля.");
        }

        this.date = date;
        this.price = price;
        this.store = store;
        this.client = client;
    }

    // Геттеры и сеттеры для остальных полей
    // ...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Дата счета не может быть пустой.");
        }
        this.date = date;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        if (store == null) {
            throw new IllegalArgumentException("Магазин счета не может быть пустым.");
        }
        this.store = store;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Сумма счета должна быть больше нуля.");
        }
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Клиент счета не может быть пустым.");
        }
        this.client = client;
    }
}
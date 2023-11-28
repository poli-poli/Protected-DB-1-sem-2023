package org.example;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

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

    // Пустой конструктор для Hibernate
    public Invoice() {
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    private void setDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Дата счета не может быть пустой.");
        }
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Сумма счета должна быть больше нуля.");
        }
        this.price = price;
    }

    public Store getStore() {
        return store;
    }

    private void setStore(Store store) {
        if (store == null) {
            throw new IllegalArgumentException("Магазин счета не может быть пустым.");
        }
        this.store = store;
    }

    public Client getClient() {
        return client;
    }

    private void setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Клиент счета не может быть пустым.");
        }
        this.client = client;
    }
}
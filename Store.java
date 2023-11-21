package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @OneToMany(mappedBy = "store")
    private List<Invoice> invoices;

    /**
     * Конструктор для создания объекта Store.
     *
     * @param name     Название магазина.
     * @param phone    Телефон магазина.
     * @param warehouse Склад, к которому привязан магазин.
     * @throws IllegalArgumentException Если название или телефон магазина равны null или пусты.
     */
    public Store(String name, String phone, Warehouse warehouse) {
        // Добавляем проверку ввода: название и телефон магазина не должны быть null или пустыми строками.
        if (name == null  name.isEmpty()  phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Название и телефон магазина не могут быть пустыми.");
        }

        this.name = name;
        this.phone = phone;
        this.warehouse = warehouse;
    }

    // Геттеры и сеттеры для остальных полей
    // ...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Проверка на пустое название магазина.
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название магазина не может быть пустым.");
        }
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        // Проверка на пустой телефон магазина.
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Телефон магазина не может быть пустым.");
        }
        this.phone = phone;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
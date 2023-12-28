package org.example;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    // Пустой конструктор для Hibernate (обычно требуется Hibernate)
    public Warehouse() {
    }

    // Геттер и сеттер для поля "id"
    public int getId() {
        return id;
    }

    // Приватный сеттер для поля "id", чтобы его нельзя было изменить извне
    private void setId(int id) {
        this.id = id;
    }

    // Геттер и сеттер для поля "name" с проверкой на пустое значение
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название склада не может быть пустым.");
        }
        this.name = name;
    }

    // Геттер и сеттер для поля "quantity" с проверкой на отрицательное значение
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Количество товара на складе не может быть отрицательным.");
        }
        this.quantity = quantity;
    }

    // Геттер и сеттер для поля "vendor"
    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}

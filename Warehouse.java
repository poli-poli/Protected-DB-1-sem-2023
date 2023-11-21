package org.example;

import javax.persistence.*;
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

    /**
     * Конструктор для создания объекта Warehouse.
     *
     * @param name     Название склада.
     * @param quantity Количество товара на складе.
     * @throws IllegalArgumentException Если название склада равно null или пусто, или количество товара меньше нуля.
     */
    public Warehouse(String name, int quantity) {
        // Добавляем проверку ввода: название склада не должно быть null или пустым,
        // и количество товара не должно быть меньше нуля.
        if (name == nullquantity < 0) {
            throw new IllegalArgumentException("Название склада должно быть указано и количество товара должно быть неотрицательным.");
        }

        this.name = name;
        this.quantity = quantity;
    }

    // Геттеры и сеттеры для остальных полей
    // ...

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Проверка на пустое название склада.
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название склада не может быть пустым.");
        }
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        // Проверка на количество товара меньше нуля.
        if (quantity < 0) {
            throw new IllegalArgumentException("Количество товара на складе не может быть отрицательным.");
        }
        this.quantity = quantity;
    }
}
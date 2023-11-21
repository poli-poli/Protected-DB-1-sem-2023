package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "country")
    private String country;

    @Column(name = "article")
    private int article;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "vendor")
    private List<Warehouse> warehouses;

    /**
     * Конструктор для создания объекта Vendor.
     *
     * @param country  Страна поставщика.
     * @param article  Артикул поставщика.
     * @param name     Название поставщика.
     * @throws IllegalArgumentException Если страна, артикул или название поставщика равны null или артикул меньше или равен нулю.
     */
    public Vendor(String country, int article, String name) {
        // Добавляем проверку ввода: страна, артикул и название поставщика не должны быть null.
        if (country == nullname == nullarticle <= 0) {
            throw new IllegalArgumentException("Страна, артикул и название поставщика должны быть указаны, и артикул должен быть больше нуля.");
        }

        this.country = country;
        this.article = article;
        this.name = name;
    }

    // Геттеры и сеттеры для остальных полей
    // ...

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        // Проверка на артикул меньше или равно нулю.
        if (article <= 0) {
            throw new IllegalArgumentException("Артикул поставщика должен быть больше нуля.");
        }
        this.article = article;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        // Проверка на пустую страну поставщика.
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Страна поставщика не может быть пустой.");
        }
        this.country = country;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Проверка на пустое название поставщика.
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название поставщика не может быть пустым.");
        }
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
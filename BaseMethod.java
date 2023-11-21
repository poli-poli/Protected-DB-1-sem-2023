package org.example;

import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "client")
    private List<Invoice> invoices;

    /**
     * Конструктор для создания объекта Client.
     *
     * @param name    Имя клиента.
     * @param country Страна клиента.
     * @param phone   Телефон клиента.
     * @throws IllegalArgumentException Если имя, страна или телефон клиента являются пустыми или null.
     */
    public Client(String name, String country, String phone) {
        // Добавляем проверку ввода: имя, страна и телефон не должны быть null или пустыми строками.
        if (name == nullcountry == nullphone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Имя, страна и телефон клиента не могут быть пустыми.");
        }

        this.name = name;
        this.country = country;
        this.phone = phone;
    }

    // Геттеры и сеттеры для остальных полей
    // ...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Проверка на пустое имя клиента.
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя клиента не может быть пустым.");
        }
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        // Проверка на пустую страну клиента.
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Страна клиента не может быть пустой.");
        }
        this.country = country;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        // Проверка на пустой телефон клиента.
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Телефон клиента не может быть пустым.");
        }
        this.phone = phone;
    }
}
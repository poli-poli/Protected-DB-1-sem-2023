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
     * ����������� ��� �������� ������� Client.
     *
     * @param name    ��� �������.
     * @param country ������ �������.
     * @param phone   ������� �������.
     * @throws IllegalArgumentException ���� ���, ������ ��� ������� ������� �������� ������� ��� null.
     */
    public Client(String name, String country, String phone) {
        // ��������� �������� �����: ���, ������ � ������� �� ������ ���� null ��� ������� ��������.
        if (name == nullcountry == nullphone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("���, ������ � ������� ������� �� ����� ���� �������.");
        }

        this.name = name;
        this.country = country;
        this.phone = phone;
    }

    // ������� � ������� ��� ��������� �����
    // ...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // �������� �� ������ ��� �������.
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("��� ������� �� ����� ���� ������.");
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
        // �������� �� ������ ������ �������.
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("������ ������� �� ����� ���� ������.");
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
        // �������� �� ������ ������� �������.
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("������� ������� �� ����� ���� ������.");
        }
        this.phone = phone;
    }
}
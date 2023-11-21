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

    // Пустой конструктор для Hibernate
    public Client() {
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя клиента не может быть пустым.");
        }
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    private void setCountry(String country) {
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Страна клиента не может быть пустой.");
        }
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Телефон клиента не может быть пустым.");
        }
        this.phone = phone;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    private void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
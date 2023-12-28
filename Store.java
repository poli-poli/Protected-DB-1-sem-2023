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

    // Пустой конструктор для Hibernate
    public Store() {
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
            throw new IllegalArgumentException("Название магазина не может быть пустым.");
        }
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    private void setPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new IllegalArgumentException("Телефон магазина не может быть пустым.");
        }
        this.phone = phone;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    private void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    private void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
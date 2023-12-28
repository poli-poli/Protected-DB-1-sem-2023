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

    // Пустой конструктор для Hibernate
    public Warehouse() {
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
            throw new IllegalArgumentException("Название склада не может быть пустым.");
        }
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    private void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Количество товара на складе не может быть отрицательным.");
        }
        this.quantity = quantity;
    }

    public Vendor getVendor() {
        return vendor;
    }

    private void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}

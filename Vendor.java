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

    // Пустой конструктор для Hibernate
    public Vendor() {
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    private void setCountry(String country) {
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("Страна поставщика не может быть пустой.");
        }
        this.country = country;
    }

    public int getArticle() {
        return article;
    }

    private void setArticle(int article) {
        if (article <= 0) {
            throw new IllegalArgumentException("Артикул поставщика должен быть больше нуля.");
        }
        this.article = article;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название поставщика не может быть пустым.");
        }
        this.name = name;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    private void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }
}
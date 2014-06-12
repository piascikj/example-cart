package piascik.domain;

import org.springframework.data.annotation.Id;

/**
 * @author <a href="http://about.me/jesse.piascik">Jesse Piascik</a>
 */
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private int    price;

    public Product() {}

    public Product(String id, String title, String description, int price) {
        this.id = id;
        this.name = title;
        this.description = description;
        this.price = price;
    }

    public Product(String title, String description, int price) {
        this.name = title;
        this.description = description;
        this.price = price;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public int getPrice() { return price; }
}

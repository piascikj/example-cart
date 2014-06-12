package piascik.domain;

/**
 * @author <a href="http://about.me/jesse.piascik">Jesse Piascik</a>
 */
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    @Id
    private String     id;
    private String     owner;
    private List<Item> items = new ArrayList<Item>();

    public Cart() {}

    // In a more developed model, owner would be a pointer to customer
    public Cart(String owner) {
        this.owner = owner;
    }

    public Cart(String id, String owner) {
        this.id = id;
        this.owner = owner;
    }

    public String getId() { return id; }

    public String getOwner() { return owner; }

    public List<Item> getItems() { return items; }

    public List<Item> addItem(String product, int quantity) {
        items.add(new Item(product, quantity));
        return items;
    }

    public List<Item> removeItem(int index) {
        items.remove(index);
        return items;
    }

    public void removeAllItems() {
        items = new ArrayList<Item>();
    }

    public List<Item> updateItem(int index, int quantity) {
        items.get(index).quantity = quantity;
        return items;
    }

    public class Item {
        private String product;
        private int    quantity;

        public String getProduct() { return product; }

        public int getQuantity() { return quantity; }

        public Item(String product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }
}
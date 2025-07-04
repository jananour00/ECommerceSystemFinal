package ECommerceSystem;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot add null product to cart");
        }
        if (product.isOutOfStock()) {
            throw new IllegalStateException("Product " + product.getName() + " is out of stock");
        }
        if (product instanceof Expirable && ((Expirable) product).isExpired()) {
            throw new IllegalStateException("Product " + product.getName() + " has expired");
        }
        
        items.add(new CartItem(product, quantity));
    }

    public void removeProduct(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double calculateSubtotal() {
        return items.stream()
                   .mapToDouble(CartItem::getTotalPrice)
                   .sum();
    }

    public void clear() {
        items.clear();
    }
}
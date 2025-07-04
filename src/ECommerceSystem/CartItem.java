package ECommerceSystem;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (quantity > product.getAvailableQuantity()) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock");
        }
        
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public boolean isShippable() {
        return product.requiresShipping();
    }

    public boolean isExpired() {
        if (product instanceof Expirable) {
            return ((Expirable) product).isExpired();
        }
        return false;
    }
}
package ECommerceSystem;

public abstract class Product {
    private String name;
    private double price;
    private int availableQuantity;

    public Product(String name, double price, int availableQuantity) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (availableQuantity < 0) {
            throw new IllegalArgumentException("Available quantity cannot be negative");
        }
        
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void reduceQuantity(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Reduction amount cannot be negative");
        }
        if (amount > availableQuantity) {
            throw new IllegalStateException("Not enough quantity available");
        }
        availableQuantity -= amount;
    }

    public boolean isOutOfStock() {
        return availableQuantity <= 0;
    }

    public abstract boolean doesExpire();

    public abstract boolean requiresShipping();
}
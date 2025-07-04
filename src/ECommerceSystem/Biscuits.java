package ECommerceSystem;

import java.time.LocalDate;

public class Biscuits extends Product implements Expirable, Shippable {
    private LocalDate expiryDate;
    private double weightInKg;

    public Biscuits(String name, double price, int availableQuantity, LocalDate expiryDate, double weightInKg) {
        super(name, price, availableQuantity);
        if (expiryDate == null) {
            throw new IllegalArgumentException("Expiry date cannot be null for biscuits");
        }
        if (weightInKg <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        
        this.expiryDate = expiryDate;
        this.weightInKg = weightInKg;
    }

    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public boolean doesExpire() {
        return true;
    }

    @Override
    public boolean requiresShipping() {
        return true;
    }

    @Override
    public double getWeightInKg() {
        return weightInKg;
    }

    @Override
    public String getWeightDescription() {
        return String.format("%.0fg", weightInKg * 1000);
    }
}
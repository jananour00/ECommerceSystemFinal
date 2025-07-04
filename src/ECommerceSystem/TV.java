package ECommerceSystem;

public class TV extends Product implements Shippable {
    private double weightInKg;

    public TV(String name, double price, int availableQuantity, double weightInKg) {
        super(name, price, availableQuantity);
        if (weightInKg <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weightInKg = weightInKg;
    }

    @Override
    public boolean doesExpire() {
        return false;
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
        return String.format("%.1fkg", weightInKg);
    }
}
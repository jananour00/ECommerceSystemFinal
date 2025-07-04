package ECommerceSystem;

public class ScratchCard extends Product {
    public ScratchCard(String name, double price, int availableQuantity) {
        super(name, price, availableQuantity);
    }

    @Override
    public boolean doesExpire() {
        return false;
    }

    @Override
    public boolean requiresShipping() {
        return false;
    }
}
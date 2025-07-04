package ECommerceSystem;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private ShippingService shippingService;

    public CheckoutService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public void processCheckout(Cart cart, Customer customer) {
        if (cart == null || customer == null) {
            throw new IllegalArgumentException("Cart and customer cannot be null");
        }
        
        validateCheckout(cart, customer);
        
        double subtotal = cart.calculateSubtotal();
        double shippingFee = calculateShippingFee(cart);
        double totalAmount = subtotal + shippingFee;
        
        customer.deductBalance(totalAmount);
        updateProductInventories(cart);
        
        printReceipt(cart, subtotal, shippingFee, totalAmount, customer);
        prepareShippingIfNeeded(cart);
        
        cart.clear();
    }

    private void validateCheckout(Cart cart, Customer customer) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cannot checkout with empty cart");
        }
        
        for (CartItem item : cart.getItems()) {
            if (item.getProduct().isOutOfStock()) {
                throw new IllegalStateException(
                    "Product " + item.getProduct().getName() + " is out of stock");
            }
            if (item.isExpired()) {
                throw new IllegalStateException(
                    "Product " + item.getProduct().getName() + " has expired");
            }
        }
        
        double totalAmount = cart.calculateSubtotal() + calculateShippingFee(cart);
        if (customer.getBalance() < totalAmount) {
            throw new IllegalStateException(
                "Insufficient balance. Needed: " + totalAmount + ", Available: " + customer.getBalance());
        }
    }
    private double calculateShippingFee(Cart cart) {
        // Flat rate shipping of 30 EGP if any shippable items exist
        boolean hasShippableItems = cart.getItems().stream()
                                       .anyMatch(CartItem::isShippable);
        return hasShippableItems ? 30 : 0;
    }
    private void updateProductInventories(Cart cart) {
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
    }

    private void printReceipt(Cart cart, double subtotal, double shippingFee, 
                            double totalAmount, Customer customer) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.printf("%dx %s %.2f%n", 
                item.getQuantity(), 
                item.getProduct().getName(), 
                item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal %.2f%n", subtotal);
        System.out.printf("Shipping %.2f%n", shippingFee);
        System.out.printf("Amount %.2f%n", totalAmount);
        System.out.printf("Remaining balance %.2f%n", customer.getBalance());
    }

    private void prepareShippingIfNeeded(Cart cart) {
        List<ShippingService.ShippableItem> shippableItems = new ArrayList<>();
        
        for (CartItem item : cart.getItems()) {
            if (item.isShippable()) {
                Product product = item.getProduct();
                shippableItems.add(new ShippingService.ShippableItem() {
                    @Override
                    public String getName() {
                        return product.getName();
                    }

                    @Override
                    public double getWeightInKg() {
                        return ((Shippable) product).getWeightInKg();
                    }

                    @Override
                    public String getWeightDescription() {
                        return ((Shippable) product).getWeightDescription();
                    }

                    @Override
                    public int getQuantity() {
                        return item.getQuantity();
                    }
                });
            }
        }
        
        shippingService.prepareShipping(shippableItems);
    }
}
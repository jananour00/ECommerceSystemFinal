package ECommerceSystem;

import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        try {
            // Create products
            Product cheese = new Cheese("Cheese", 100, 10, 
                LocalDate.now().plusDays(7), 0.4);
            Product biscuits = new Biscuits("Biscuits", 150, 5, 
                LocalDate.now().plusDays(30), 0.7);
            Product tv = new TV("TV", 10000, 3, 15.5);
            Product scratchCard = new ScratchCard("Mobile Scratch Card", 50, 100);

            // Create customer
            Customer customer = new Customer("John Doe", 1000);

            // Create services
            ShippingService shippingService = new ShippingService();
            CheckoutService checkoutService = new CheckoutService(shippingService);

            // Test case 1: Normal checkout with shippable and non-shippable items
            System.out.println("\n=== Test Case 1: Successful Checkout ===");
            Cart cart1 = new Cart();
            cart1.addProduct(cheese, 2);
            cart1.addProduct(biscuits, 1);
            cart1.addProduct(scratchCard, 1);
            checkoutService.processCheckout(cart1, customer);

            // Test case 2: Checkout with insufficient balance
            System.out.println("\n=== Test Case 2: Insufficient Balance ===");
            Cart cart2 = new Cart();
            cart2.addProduct(tv, 1);
            try {
                checkoutService.processCheckout(cart2, customer);
            } catch (IllegalStateException e) {
                System.out.println("Error handled: " + e.getMessage());
                System.out.println("Action: Ask customer to add more funds or remove items");
            }

            // Test case 3: Checkout with expired product
            System.out.println("\n=== Test Case 3: Expired Product ===");
            Product expiredCheese = new Cheese("Expired Cheese", 80, 5, 
                LocalDate.now().minusDays(1), 0.4);
            Cart cart3 = new Cart();
            try {
                cart3.addProduct(expiredCheese, 1);
                checkoutService.processCheckout(cart3, customer);
            } catch (IllegalStateException e) {
                System.out.println("Error handled: " + e.getMessage());
                System.out.println("Action: Remove expired product from cart and notify customer");
            }

            // Test case 4: Empty cart
            System.out.println("\n=== Test Case 4: Empty Cart ===");
            Cart cart4 = new Cart();
            try {
                checkoutService.processCheckout(cart4, customer);
            } catch (IllegalStateException e) {
                System.out.println("Error handled: " + e.getMessage());
                System.out.println("Action: Prompt customer to add items to cart");
            }

            // Test case 5: Out of stock
            System.out.println("\n=== Test Case 5: Out of Stock ===");
            Product rareItem = new TV("Rare TV", 5000, 1, 10.0);
            Cart cart5 = new Cart();
            try {
                cart5.addProduct(rareItem, 2); // Try to buy 2 when only 1 is available
                checkoutService.processCheckout(cart5, customer);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error handled: " + e.getMessage());
                System.out.println("Action: Adjust quantity or notify customer of stock limits");
            }

            // Test case 6: Successful checkout after errors
            System.out.println("\n=== Test Case 6: Successful Checkout After Errors ===");
            Cart cart6 = new Cart();
            cart6.addProduct(cheese, 1);
            cart6.addProduct(scratchCard, 2);
            checkoutService.processCheckout(cart6, customer);
            System.out.println("Checkout completed successfully!");

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
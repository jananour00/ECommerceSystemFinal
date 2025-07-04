package ECommerceSystem;

import java.util.List;

public class ShippingService {
    public void prepareShipping(List<ShippableItem> shippableItems) {
        if (shippableItems == null || shippableItems.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        
        for (ShippableItem item : shippableItems) {
            System.out.printf("%dx %s %s%n", 
                item.getQuantity(), 
                item.getName(), 
                item.getWeightDescription());
            totalWeight += item.getWeightInKg() * item.getQuantity();
        }
        
        System.out.printf("Total package weight %.1fkg%n", totalWeight);
    }

    public interface ShippableItem {
        String getName();
        double getWeightInKg();
        String getWeightDescription();
        int getQuantity();
    }
}
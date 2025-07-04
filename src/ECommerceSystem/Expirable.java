package ECommerceSystem;

import java.time.LocalDate;

public interface Expirable {
    LocalDate getExpiryDate();
    boolean isExpired();
}
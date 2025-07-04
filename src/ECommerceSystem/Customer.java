package ECommerceSystem;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deduction amount cannot be negative");
        }
        if (balance < amount) {
            throw new IllegalStateException("Insufficient balance");
        }
        balance -= amount;
    }

    public void addBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Added amount cannot be negative");
        }
        balance += amount;
    }
}
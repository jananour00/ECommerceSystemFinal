# E-Commerce System Documentation

## Table of Contents

1. [System Overview](#system-overview)
2. [Class Structure](#class-structure)
3. [Key Features](#key-features)
4. [Error Handling](#error-handling)
5. [Test Cases](#test-cases)
6. [How to Run](#how-to-run)
7. [Future Enhancements](#future-enhancements)

## System Overview

This e-commerce system demonstrates proper OOP principles with:

* Product hierarchy (physical goods, digital goods)
* Shopping cart functionality
* Checkout process with validation
* Shipping preparation
* Comprehensive error handling

## Class Structure
![nLLHJzim47xFhxZrPHqGnBIt2Yf6GHFQ16n1xDbnhgs54mVRwOOw_ljySNfAIa52UnWlvRuxVz_vxdmvTLvOtvGwUUUNM2AiL6v5vPFtSDB-mSnOX9aMpg7hi4II2JVM58tqi4u0qagKE87CMrKjoAwjaW4eJ9Dh942iXD8Y6D-RmA_yumHKq07ONw2_2gV77_Xy16-8Wb0csRSO59](https://github.com/user-attachments/assets/6c372bc0-5b83-44e7-9a2c-a305b3266ca9)

## Output Screen
![image](https://github.com/user-attachments/assets/aebfb8ac-c8dc-4618-b710-3f19b2bad7bc)


### Core Classes

* **Product**: Abstract base class for all products
* **Cheese/Biscuits/TV/ScratchCard**: Concrete product implementations
* **Cart**: Manages shopping cart items
* **CartItem**: Represents a product with quantity in cart
* **Customer**: Stores customer information and balance

### Interfaces

* **Expirable**: For products with expiry dates
* **Shippable**: For products that require shipping

### Services

* **ShippingService**: Handles shipping preparation
* **CheckoutService**: Manages the checkout process

## Key Features

1. **Product Types**:

   * Physical shippable products (Cheese, Biscuits, TV)
   * Non-shippable products (ScratchCard)
   * Expirable products (Cheese, Biscuits)

2. **Shopping Cart**:

   * Add/remove products
   * Quantity validation
   * Subtotal calculation

3. **Checkout Process**:

   * Balance verification
   * Stock level checks
   * Expiry date validation
   * Shipping fee calculation
   * Inventory updates

4. **Shipping Preparation**:

   * Package weight calculation
   * Shipping notice generation

## Error Handling

The system handles various error scenarios gracefully:

1. **Product Validation**:

   * Negative prices/quantities
   * Empty product names
   * Invalid weights/expiry dates

2. **Cart Operations**:

   * Adding out-of-stock products
   * Adding expired products
   * Invalid quantities

3. **Checkout Process**:

   * Empty cart
   * Insufficient balance
   * Concurrent stock changes

All errors include descriptive messages to help identify and resolve issues.

## Test Cases

The demo includes comprehensive test cases:

### 1. Successful Checkout

* Tests normal operation with mixed product types
* Verifies:

  * Subtotal calculation
  * Shipping fee
  * Balance deduction
  * Inventory update
  * Shipping preparation

### 2. Insufficient Balance

* Attempts checkout with insufficient funds
* Verifies:

  * Proper error detection
  * Clear error message
  * No balance deduction
  * Cart remains unchanged

### 3. Expired Product

* Attempts to add expired product to cart
* Verifies:

  * Expiration detection
  * Prevention of checkout
  * Clear error message

### 4. Empty Cart

* Attempts checkout with empty cart
* Verifies:

  * Proper error detection
  * Clear error message

### 5. Out of Stock

* Attempts to purchase more than available quantity
* Verifies:

  * Stock level validation
  * Clear error message
  * Prevention of invalid purchase

### 6. Successful Checkout After Errors

* Demonstrates recovery after previous errors
* Verifies system remains in consistent state

## How to Run

1. Ensure you have Java JDK installed
2. Compile all Java files:

   ```
   javac ECommerceSystem/*.java
   ```
3. Run the demo:

   ```
   java ECommerceSystem.Demo
   ```

## Project Structure

Below is the recommended Eclipse/Maven folder layout. All **.java** files live under a single package named `ECommerceSystem` so they can access each other without fully‑qualified imports.

```text
ECommerceSystem/          <–– project root
├── src/                  <–– source folder (configured as “Source” in Eclipse)
│   └── ECommerceSystem/  <–– Java package directory
│       ├── Product.java
│       ├── Cheese.java
│       ├── Biscuits.java
│       ├── TV.java
│       ├── ScratchCard.java
│       ├── Cart.java
│       ├── CartItem.java
│       ├── Customer.java
│       ├── CheckoutService.java
│       ├── ShippingService.java
│       └── Demo.java      <–– contains the main() demo
├── README.md             <–– (this file)
└── .gitignore            <–– ignores .class, .settings/, etc.
```


7. **Web Interface**

The system is designed to be extensible for these future requirements while maintaining clean architecture and solid OOP principles.

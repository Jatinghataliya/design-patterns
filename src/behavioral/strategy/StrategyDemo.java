package behavioral.strategy;

import behavioral.strategy.cart.Item;
import behavioral.strategy.cart.ShoppingCart;
import behavioral.strategy.strategies.CreditCardPayment;
import behavioral.strategy.strategies.CryptoPayment;
import behavioral.strategy.strategies.PayPalPayment;

/**
 * Demo runner for Strategy Pattern.
 */
public class StrategyDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("          STRATEGY DESIGN PATTERN DEMO            ");
        System.out.println("==================================================\n");

        ShoppingCart cart = new ShoppingCart();

        // 1. Customer buys books & pays with Credit Card
        System.out.println("--- Order 1: Tech Books ---");
        cart.addItem(new Item("Designing Data-Intensive Applications", 45.99));
        cart.addItem(new Item("Clean Code", 39.50));

        // Inject Credit Card strategy
        cart.setPaymentStrategy(new CreditCardPayment(
                "4532758912345678", "Alice Smith", "321", "12/28"
        ));
        cart.checkout();

        // 2. Customer buys hardware & pays with PayPal
        System.out.println("--- Order 2: Electronics ---");
        cart.addItem(new Item("Mechanical Keyboard", 129.99));
        cart.addItem(new Item("Wireless Mouse", 49.99));

        // Switch strategy at runtime to PayPal
        cart.setPaymentStrategy(new PayPalPayment("alice.smith@example.com", "securePassword123"));
        cart.checkout();

        // 3. Customer buys subscription & pays with Crypto
        System.out.println("--- Order 3: Cloud Subscription ---");
        cart.addItem(new Item("Cloud Hosting 1-Year", 240.00));

        // Switch strategy at runtime to Crypto
        cart.setPaymentStrategy(new CryptoPayment("0x71C...8942B", "Ethereum"));
        cart.checkout();

        // 4. Java 8+ Lambda Strategy (inline algorithm without explicit class)
        System.out.println("--- Order 4: Gift Voucher (Using Lambda Strategy) ---");
        cart.addItem(new Item("Coffee Mug", 15.00));

        cart.setPaymentStrategy(new behavioral.strategy.strategies.PaymentStrategy() {
            @Override
            public void pay(double amount) {
                System.out.printf("  [Gift Card] Deducted $%.2f from voucher CODE#SUMMER2025%n", amount);
            }

            @Override
            public String getPaymentMethodName() {
                return "Gift Card Voucher";
            }
        });
        cart.checkout();
    }
}

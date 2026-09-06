package behavioral.strategy.cart;

import behavioral.strategy.strategies.PaymentStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Context Class: ShoppingCart.
 * Maintains a reference to a Strategy object and delegates payment execution to it.
 * The Strategy can be swapped at runtime.
 */
public class ShoppingCart {
    private final List<Item> items = new ArrayList<>();
    private PaymentStrategy paymentStrategy;

    public void addItem(Item item) {
        items.add(item);
        System.out.printf("Added '%s' ($%.2f) to cart.%n", item.getName(), item.getPrice());
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }

    /**
     * Set or change the strategy dynamically at runtime.
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * Executes the strategy algorithm.
     */
    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }

        if (paymentStrategy == null) {
            throw new IllegalStateException("No payment strategy selected. Please select a payment method before checkout.");
        }

        double total = calculateTotal();
        System.out.printf("%nProcessing checkout via %s... Total: $%.2f%n",
                paymentStrategy.getPaymentMethodName(), total);
        paymentStrategy.pay(total);
        items.clear();
        System.out.println("Payment complete. Cart has been emptied.\n");
    }
}

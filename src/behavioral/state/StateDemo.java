package behavioral.state;

import behavioral.state.order.Order;

/**
 * Demo runner for State Pattern.
 */
public class StateDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("            STATE DESIGN PATTERN DEMO             ");
        System.out.println("==================================================");

        // --- Scenario 1: Happy Path (New -> Paid -> Shipped -> Delivered) ---
        System.out.println("\n--- Scenario 1: Order #ORD-101 (Happy Path to Delivery) ---");
        Order order1 = new Order("ORD-101", 149.99);

        // Try invalid action first
        order1.ship(); // Should be blocked (not paid yet)

        // Proceed with correct sequence
        order1.pay();      // NEW -> PAID
        order1.pay();      // Duplicate pay warning
        order1.ship();     // PAID -> SHIPPED
        order1.deliver();  // SHIPPED -> DELIVERED
        order1.cancel();   // Should be blocked (already delivered)

        // --- Scenario 2: Paid Order Cancellation with Refund ---
        System.out.println("\n--------------------------------------------------");
        System.out.println("--- Scenario 2: Order #ORD-202 (Paid then Cancelled with Refund) ---");
        Order order2 = new Order("ORD-202", 399.00);
        order2.pay();      // NEW -> PAID
        order2.cancel();   // PAID -> CANCELLED (triggers refund)
        order2.ship();     // Blocked on cancelled order

        // --- Scenario 3: In-Transit Cancellation Attempt ---
        System.out.println("\n--------------------------------------------------");
        System.out.println("--- Scenario 3: Order #ORD-303 (Attempting to Cancel Shipped Order) ---");
        Order order3 = new Order("ORD-303", 79.50);
        order3.pay();      // NEW -> PAID
        order3.ship();     // PAID -> SHIPPED
        order3.cancel();   // Blocked because it's already in transit!
        order3.deliver();  // Successfully delivered
    }
}

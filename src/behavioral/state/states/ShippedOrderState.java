package behavioral.state.states;

import behavioral.state.order.Order;

/**
 * Concrete State 3: Shipped Order.
 * Can transition to: DELIVERED (via deliver). Cannot be directly cancelled (needs return workflow).
 */
public class ShippedOrderState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.printf("  [Duplicate Action] Order #%s was already paid before shipping.%n", order.getOrderId());
    }

    @Override
    public void ship(Order order) {
        System.out.printf("  [Duplicate Action] Order #%s is already in transit.%n", order.getOrderId());
    }

    @Override
    public void deliver(Order order) {
        System.out.printf("  [Delivery Completed] Order #%s delivered to customer successfully!%n", order.getOrderId());
        order.setState(new DeliveredOrderState());
    }

    @Override
    public void cancel(Order order) {
        System.out.printf("  [Action Blocked] Cannot cancel Order #%s. Package is already in transit! Customer must return it after delivery.%n",
                order.getOrderId());
    }

    @Override
    public String getStateName() {
        return "SHIPPED";
    }
}

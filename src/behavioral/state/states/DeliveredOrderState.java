package behavioral.state.states;

import behavioral.state.order.Order;

/**
 * Concrete State 4: Delivered Order (Terminal / Final state).
 */
public class DeliveredOrderState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.printf("  [Action Blocked] Order #%s is already delivered and closed.%n", order.getOrderId());
    }

    @Override
    public void ship(Order order) {
        System.out.printf("  [Action Blocked] Order #%s has already been delivered.%n", order.getOrderId());
    }

    @Override
    public void deliver(Order order) {
        System.out.printf("  [Duplicate Action] Order #%s is already marked as delivered.%n", order.getOrderId());
    }

    @Override
    public void cancel(Order order) {
        System.out.printf("  [Action Blocked] Cannot cancel delivered Order #%s. Please initiate a product return request.%n", order.getOrderId());
    }

    @Override
    public String getStateName() {
        return "DELIVERED";
    }
}

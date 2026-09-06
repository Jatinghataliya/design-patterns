package behavioral.state.states;

import behavioral.state.order.Order;

/**
 * Concrete State 5: Cancelled Order (Terminal / Final state).
 */
public class CancelledOrderState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.printf("  [Action Blocked] Cannot pay for cancelled Order #%s.%n", order.getOrderId());
    }

    @Override
    public void ship(Order order) {
        System.out.printf("  [Action Blocked] Cannot ship cancelled Order #%s.%n", order.getOrderId());
    }

    @Override
    public void deliver(Order order) {
        System.out.printf("  [Action Blocked] Cannot deliver cancelled Order #%s.%n", order.getOrderId());
    }

    @Override
    public void cancel(Order order) {
        System.out.printf("  [Duplicate Action] Order #%s is already cancelled.%n", order.getOrderId());
    }

    @Override
    public String getStateName() {
        return "CANCELLED";
    }
}

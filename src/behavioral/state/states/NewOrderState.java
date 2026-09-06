package behavioral.state.states;

import behavioral.state.order.Order;

/**
 * Concrete State 1: New / Created Order.
 * Can transition to: PAID (via pay) or CANCELLED (via cancel).
 */
public class NewOrderState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.printf("  [Payment Successful] Order #%s marked as PAID.%n", order.getOrderId());
        order.setState(new PaidOrderState());
    }

    @Override
    public void ship(Order order) {
        System.out.printf("  [Action Blocked] Cannot ship Order #%s. Payment has not been received yet!%n", order.getOrderId());
    }

    @Override
    public void deliver(Order order) {
        System.out.printf("  [Action Blocked] Cannot deliver Order #%s. It has not even been paid or shipped!%n", order.getOrderId());
    }

    @Override
    public void cancel(Order order) {
        System.out.printf("  [Order Cancelled] Unpaid Order #%s has been cancelled.%n", order.getOrderId());
        order.setState(new CancelledOrderState());
    }

    @Override
    public String getStateName() {
        return "NEW";
    }
}

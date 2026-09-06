package behavioral.state.states;

import behavioral.state.order.Order;

/**
 * Concrete State 2: Paid Order.
 * Can transition to: SHIPPED (via ship) or CANCELLED (via cancel with refund).
 */
public class PaidOrderState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.printf("  [Duplicate Action] Order #%s is ALREADY paid.%n", order.getOrderId());
    }

    @Override
    public void ship(Order order) {
        System.out.printf("  [Shipping Dispatched] Order #%s has been dispatched with courier.%n", order.getOrderId());
        order.setState(new ShippedOrderState());
    }

    @Override
    public void deliver(Order order) {
        System.out.printf("  [Action Blocked] Cannot deliver Order #%s yet. It must be shipped first!%n", order.getOrderId());
    }

    @Override
    public void cancel(Order order) {
        System.out.printf("  [Order Cancelled & Refunded] Order #%s cancelled. A full refund of $%.2f was issued.%n",
                order.getOrderId(), order.getTotalAmount());
        order.setState(new CancelledOrderState());
    }

    @Override
    public String getStateName() {
        return "PAID";
    }
}

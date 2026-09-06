package behavioral.state.order;

import behavioral.state.states.NewOrderState;
import behavioral.state.states.OrderState;

/**
 * Context Class: Order
 * Maintains a reference to an instance of an OrderState subclass that represents the current state.
 */
public class Order {
    private final String orderId;
    private final double totalAmount;
    private OrderState state;

    public Order(String orderId, double totalAmount) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        // Initial state is NEW
        this.state = new NewOrderState();
        System.out.printf("Created Order #%s ($%.2f) in initial state: [%s]%n",
                orderId, totalAmount, state.getStateName());
    }

    /**
     * Allows State transitions.
     */
    public void setState(OrderState state) {
        System.out.printf("  ==> [State Transition] Order #%s: %s -> %s%n",
                orderId, this.state.getStateName(), state.getStateName());
        this.state = state;
    }

    // Context delegates client requests directly to current State object:

    public void pay() {
        System.out.printf("%nOrder #%s: User requested PAY (Current State: [%s])%n", orderId, state.getStateName());
        state.pay(this);
    }

    public void ship() {
        System.out.printf("%nOrder #%s: User requested SHIP (Current State: [%s])%n", orderId, state.getStateName());
        state.ship(this);
    }

    public void deliver() {
        System.out.printf("%nOrder #%s: User requested DELIVER (Current State: [%s])%n", orderId, state.getStateName());
        state.deliver(this);
    }

    public void cancel() {
        System.out.printf("%nOrder #%s: User requested CANCEL (Current State: [%s])%n", orderId, state.getStateName());
        state.cancel(this);
    }

    public String getOrderId() { return orderId; }
    public double getTotalAmount() { return totalAmount; }
    public OrderState getState() { return state; }
}

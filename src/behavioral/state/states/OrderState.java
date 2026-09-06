package behavioral.state.states;

import behavioral.state.order.Order;

/**
 * State Interface.
 * Declares all actions that can happen to an Order across its lifecycle.
 */
public interface OrderState {
    void pay(Order order);
    void ship(Order order);
    void deliver(Order order);
    void cancel(Order order);
    String getStateName();
}

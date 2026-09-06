package behavioral.strategy.strategies;

/**
 * Strategy Interface.
 * Declares the method common to all supported payment algorithms.
 */
public interface PaymentStrategy {
    void pay(double amount);
    String getPaymentMethodName();
}

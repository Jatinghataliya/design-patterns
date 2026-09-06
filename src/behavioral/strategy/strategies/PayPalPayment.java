package behavioral.strategy.strategies;

/**
 * Concrete Strategy 2: PayPal Payment.
 */
public class PayPalPayment implements PaymentStrategy {
    private final String email;
    private final String password; // In real life, would be OAuth token

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("  [PayPal] Charged $%.2f from account '%s'%n", amount, email);
    }

    @Override
    public String getPaymentMethodName() {
        return "PayPal";
    }
}

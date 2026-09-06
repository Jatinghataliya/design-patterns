package behavioral.strategy.strategies;

/**
 * Concrete Strategy 1: Credit Card Payment.
 */
public class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    private final String cardHolder;
    private final String cvv;
    private final String expiryDate;

    public CreditCardPayment(String cardNumber, String cardHolder, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(double amount) {
        String maskedCard = "****-****-****-" + cardNumber.substring(Math.max(0, cardNumber.length() - 4));
        System.out.printf("  [Credit Card] Charged $%.2f to %s (Holder: %s, Exp: %s)%n",
                amount, maskedCard, cardHolder, expiryDate);
    }

    @Override
    public String getPaymentMethodName() {
        return "Credit Card";
    }
}

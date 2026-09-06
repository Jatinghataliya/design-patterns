package behavioral.strategy.strategies;

/**
 * Concrete Strategy 3: Crypto / Bitcoin Payment.
 */
public class CryptoPayment implements PaymentStrategy {
    private final String walletAddress;
    private final String network; // e.g., "Bitcoin", "Ethereum"

    public CryptoPayment(String walletAddress, String network) {
        this.walletAddress = walletAddress;
        this.network = network;
    }

    @Override
    public void pay(double amount) {
        String shortWallet = walletAddress.substring(0, Math.min(6, walletAddress.length())) + "..." +
                walletAddress.substring(Math.max(0, walletAddress.length() - 4));
        System.out.printf("  [Crypto - %s] Transferred equivalent of $%.2f to wallet %s%n",
                network, amount, shortWallet);
    }

    @Override
    public String getPaymentMethodName() {
        return "Crypto (" + network + ")";
    }
}

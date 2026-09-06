package structural.adapter.adaptee;

/**
 * Adaptee Class (Legacy Third-Party Provider).
 * Has an incompatible interface: emits data strictly in XML format.
 */
public class LegacyXmlStockProvider {

    /**
     * Fetches raw stock quote as XML string:
     * <StockQuote><ticker>AAPL</ticker><lastPrice>189.50</lastPrice><shares>1200000</shares></StockQuote>
     */
    public String fetchStockQuoteXml(String ticker) {
        System.out.printf("  [LegacyXmlStockProvider] Fetching XML stock feed for ticker: %s...%n", ticker);
        double simulatedPrice = 150.0 + (ticker.hashCode() % 50);
        int simulatedVolume = 500000 + (Math.abs(ticker.hashCode()) % 1000000);
        return String.format("<StockQuote><ticker>%s</ticker><lastPrice>%.2f</lastPrice><shares>%d</shares></StockQuote>",
                ticker, simulatedPrice, simulatedVolume);
    }
}

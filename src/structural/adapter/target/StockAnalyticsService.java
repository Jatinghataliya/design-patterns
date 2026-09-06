package structural.adapter.target;

/**
 * Target Interface.
 * The standard domain interface that our modern client application expects.
 */
public interface StockAnalyticsService {
    /**
     * Processes stock quotes provided in standard JSON format:
     * {"symbol": "AAPL", "price": 189.50, "volume": 1200000}
     */
    void processStockData(String jsonStockData);
}

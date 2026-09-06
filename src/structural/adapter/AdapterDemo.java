package structural.adapter;

import structural.adapter.adaptee.LegacyXmlStockProvider;
import structural.adapter.adapter.XmlToJsonStockAdapter;
import structural.adapter.target.JsonStockAnalytics;
import structural.adapter.target.StockAnalyticsService;

/**
 * Demo runner for Adapter Pattern.
 */
public class AdapterDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("           ADAPTER DESIGN PATTERN DEMO            ");
        System.out.println("     Legacy XML Stock Feed -> Modern JSON App     ");
        System.out.println("==================================================\n");

        // 1. Direct modern JSON service usage (Target)
        System.out.println("--- 1. Direct Modern JSON Processing ---");
        StockAnalyticsService modernService = new JsonStockAnalytics();
        modernService.processStockData("{\"symbol\": \"MSFT\", \"price\": 420.75, \"volume\": 2500000}");

        // 2. Legacy Provider alone (Incompatible with modern service)
        System.out.println("\n--- 2. Legacy Provider (Emits XML Only) ---");
        LegacyXmlStockProvider legacyXmlProvider = new LegacyXmlStockProvider();
        String rawXml = legacyXmlProvider.fetchStockQuoteXml("AAPL");
        System.out.println("Raw XML from Legacy Service: " + rawXml);
        System.out.println("(Modern JSON analytics cannot consume this raw XML directly)");

        // 3. Adapter bridging the gap
        System.out.println("\n--- 3. Using Adapter to Connect Legacy XML Feed to JSON Analytics ---");
        XmlToJsonStockAdapter adapter = new XmlToJsonStockAdapter(legacyXmlProvider, modernService);

        System.out.println("\n[Client Request 1]: Fetch AAPL Stock");
        adapter.fetchAndProcess("AAPL");

        System.out.println("\n[Client Request 2]: Fetch GOOGL Stock");
        adapter.fetchAndProcess("GOOGL");

        System.out.println("\n[Client Request 3]: Fetch NVDA Stock");
        adapter.fetchAndProcess("NVDA");
    }
}

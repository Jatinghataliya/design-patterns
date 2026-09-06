package structural.adapter.adapter;

import structural.adapter.adaptee.LegacyXmlStockProvider;
import structural.adapter.target.StockAnalyticsService;

/**
 * Adapter Class: XmlToJsonStockAdapter.
 * Implements the Target interface (StockAnalyticsService) and wraps the Adaptee (LegacyXmlStockProvider).
 * Translates XML feeds from the legacy provider into JSON expected by modern analytics.
 */
public class XmlToJsonStockAdapter implements StockAnalyticsService {
    private final LegacyXmlStockProvider xmlProvider;
    private final StockAnalyticsService analyticsTarget;

    public XmlToJsonStockAdapter(LegacyXmlStockProvider xmlProvider, StockAnalyticsService analyticsTarget) {
        this.xmlProvider = xmlProvider;
        this.analyticsTarget = analyticsTarget;
    }

    /**
     * Fetches XML for a given ticker and converts it to JSON before passing to the target.
     */
    public void fetchAndProcess(String ticker) {
        String xmlData = xmlProvider.fetchStockQuoteXml(ticker);
        System.out.println("  [Adapter] Intercepted XML raw payload: " + xmlData);

        String jsonData = convertXmlToJson(xmlData);
        System.out.println("  [Adapter] Converted XML -> JSON: " + jsonData);

        // Forward converted JSON to the target
        processStockData(jsonData);
    }

    @Override
    public void processStockData(String jsonStockData) {
        analyticsTarget.processStockData(jsonStockData);
    }

    /**
     * Converts:
     * <StockQuote><ticker>XYZ</ticker><lastPrice>123.45</lastPrice><shares>1000</shares></StockQuote>
     * into:
     * {"symbol": "XYZ", "price": 123.45, "volume": 1000}
     */
    private String convertXmlToJson(String xml) {
        String ticker = extractXmlTag(xml, "ticker");
        String price = extractXmlTag(xml, "lastPrice");
        String shares = extractXmlTag(xml, "shares");

        return String.format("{\"symbol\": \"%s\", \"price\": %s, \"volume\": %s}",
                ticker, price, shares);
    }

    private String extractXmlTag(String xml, String tag) {
        String openTag = "<" + tag + ">";
        String closeTag = "</" + tag + ">";
        int start = xml.indexOf(openTag);
        int end = xml.indexOf(closeTag);
        if (start == -1 || end == -1) return "";
        return xml.substring(start + openTag.length(), end);
    }
}

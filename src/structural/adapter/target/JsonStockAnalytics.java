package structural.adapter.target;

/**
 * Modern Client Implementation: JsonStockAnalytics.
 * Processes JSON payloads and generates market charts and alerts.
 */
public class JsonStockAnalytics implements StockAnalyticsService {

    @Override
    public void processStockData(String jsonStockData) {
        System.out.println("  [JsonStockAnalytics] Analyzing JSON payload: " + jsonStockData);

        // Simple parser extracting symbol, price, volume from JSON string
        String symbol = extractJsonField(jsonStockData, "symbol");
        String price = extractJsonField(jsonStockData, "price");
        String volume = extractJsonField(jsonStockData, "volume");

        System.out.printf("  ==> Analytics Output: Stock [%s] is trading at $%s (Daily Volume: %s shares)%n",
                symbol, price, volume);
    }

    private String extractJsonField(String json, String field) {
        String key = "\"" + field + "\":";
        int idx = json.indexOf(key);
        if (idx == -1) return "N/A";
        int start = idx + key.length();
        while (start < json.length() && (json.charAt(start) == ' ' || json.charAt(start) == '"')) {
            start++;
        }
        int end = start;
        while (end < json.length() && json.charAt(end) != '"' && json.charAt(end) != ',' && json.charAt(end) != '}') {
            end++;
        }
        return json.substring(start, end).trim();
    }
}

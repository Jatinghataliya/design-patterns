package behavioral.template_method.miners;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Concrete Miner 3: JSON Data Miner.
 * Specializes in parsing hierarchical JSON payload structures.
 */
public class JsonDataMiner extends DataMiner {

    @Override
    protected byte[] extractData(File file) {
        System.out.printf("  [JSON Extraction] Reading UTF-8 JSON buffer from '%s'...%n", file.getName());
        return "{\"event\": \"order_placed\", \"userId\": \"usr_889\", \"amount\": 299.99}".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected String parseData(byte[] rawData) {
        System.out.println("  [JSON Parser] Deserializing JSON object graph and flattening nested keys.");
        return "order_placed,usr_889,299.99";
    }

    @Override
    protected boolean shouldArchiveFile() {
        return true;
    }
}

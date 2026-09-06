package behavioral.template_method.miners;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Concrete Miner 1: CSV Data Miner.
 * Specializes in extracting and parsing comma-separated text files.
 */
public class CsvDataMiner extends DataMiner {

    @Override
    protected byte[] extractData(File file) {
        System.out.printf("  [CSV Extraction] Reading raw byte stream from tabular CSV file '%s'...%n", file.getName());
        return "id,name,revenue,department\n1,AlphaCorp,50000,Sales\n2,BetaLLC,120000,Tech".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected String parseData(byte[] rawData) {
        String csvContent = new String(rawData, StandardCharsets.UTF_8);
        System.out.println("  [CSV Parser] Tokenizing CSV lines by commas into structured tabular rows.");
        return csvContent.replace("\n", ",");
    }

    // CSV files are sensitive, so we override the hook to archive them
    @Override
    protected boolean shouldArchiveFile() {
        return true;
    }
}

package behavioral.template_method;

import behavioral.template_method.miners.CsvDataMiner;
import behavioral.template_method.miners.DataMiner;
import behavioral.template_method.miners.JsonDataMiner;
import behavioral.template_method.miners.PdfDataMiner;

/**
 * Demo runner for Template Method Pattern.
 */
public class TemplateMethodDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       TEMPLATE METHOD DESIGN PATTERN DEMO        ");
        System.out.println("==================================================");

        // 1. Process CSV File (Custom extraction, parsing, and archives file via hook)
        DataMiner csvMiner = new CsvDataMiner();
        csvMiner.mine("/data/quarterly_finance_report.csv");

        // 2. Process PDF File (Custom PDF parsing, default hook: no archive)
        DataMiner pdfMiner = new PdfDataMiner();
        pdfMiner.mine("/documents/vendor_invoice_2025.pdf");

        // 3. Process JSON File (Custom JSON deserialization, archives file via hook)
        DataMiner jsonMiner = new JsonDataMiner();
        jsonMiner.mine("/logs/user_activity_stream.json");
    }
}

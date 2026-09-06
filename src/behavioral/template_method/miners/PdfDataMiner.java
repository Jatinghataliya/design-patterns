package behavioral.template_method.miners;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Concrete Miner 2: PDF Data Miner.
 * Specializes in OCR & vector font extraction from PDF documents.
 */
public class PdfDataMiner extends DataMiner {

    @Override
    protected byte[] extractData(File file) {
        System.out.printf("  [PDF Extraction] Extracting raw binary streams & layout trees from PDF '%s'...%n", file.getName());
        return "PDF-v1.7: [Invoice#90210, Client: Acme, Total: $4500.00, Status: Settled]".getBytes(StandardCharsets.UTF_8);
    }

    @Override
    protected String parseData(byte[] rawData) {
        System.out.println("  [PDF Parser] Applying OCR layout analysis and font vector conversion.");
        return "Invoice#90210,Acme,$4500.00,Settled";
    }

    // PDF files use default hook (shouldArchiveFile = false)
}

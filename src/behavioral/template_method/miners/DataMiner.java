package behavioral.template_method.miners;

import java.io.File;

/**
 * Abstract Class defining the Template Method.
 * Defines the skeleton of a data mining & reporting algorithm.
 */
public abstract class DataMiner {

    /**
     * The TEMPLATE METHOD.
     * Marked 'final' so subclasses cannot override the algorithm's execution sequence.
     */
    public final void mine(String filePath) {
        System.out.printf("%n=== Starting Data Mining for: %s ===%n", filePath);
        File file = openFile(filePath);
        byte[] rawData = extractData(file);
        String parsedData = parseData(rawData);
        String analysis = analyzeData(parsedData);
        sendReport(analysis);

        // Optional Hook method (executed if hook condition is true)
        if (shouldArchiveFile()) {
            archiveFile(filePath);
        }

        closeFile(file);
        System.out.println("=== Data Mining Process Completed ===\n");
    }

    // --- Standard invariant steps (same for all subclasses) ---

    protected File openFile(String path) {
        System.out.printf("  [Standard Step] Opening file descriptor for: %s%n", path);
        return new File(path);
    }

    protected String analyzeData(String parsedData) {
        System.out.printf("  [Standard Step] Running statistical NLP & anomaly analysis on parsed records...%n");
        return "Analysis Report: Processed " + parsedData.split(",").length + " data fields successfully.";
    }

    protected void sendReport(String analysis) {
        System.out.printf("  [Standard Step] Dispatching report to operations dashboard: \"%s\"%n", analysis);
    }

    protected void closeFile(File file) {
        System.out.printf("  [Standard Step] Closing file descriptor for: %s%n", file.getName());
    }

    // --- Primitive steps (abstract methods subclasses MUST implement) ---

    protected abstract byte[] extractData(File file);

    protected abstract String parseData(byte[] rawData);

    // --- Hook methods (subclasses CAN optionally override) ---

    /**
     * Hook: Subclasses can override this to control whether the source file is archived.
     * Default behavior: false.
     */
    protected boolean shouldArchiveFile() {
        return false;
    }

    protected void archiveFile(String path) {
        System.out.printf("  [Hook Triggered] Archiving processed file '%s' to S3 cold storage bucket.%n", path);
    }
}

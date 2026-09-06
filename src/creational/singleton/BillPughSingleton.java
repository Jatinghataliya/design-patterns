package creational.singleton;

/**
 * 3. Bill Pugh Singleton (Initialization-on-demand holder idiom).
 * - Thread-safe: Yes (guaranteed by JVM classloader mechanism)
 * - Lazy: Yes (Inner class is not loaded until getInstance() is called)
 * - High performance: No synchronization overhead after initialization.
 */
public class BillPughSingleton {

    private BillPughSingleton() {
        System.out.println("  [BillPughSingleton] Initializing instance on demand via Holder class.");
    }

    // Static nested class is only loaded when BillPughSingleton.getInstance() is called
    private static class SingletonHolder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void logMessage(String message) {
        System.out.println("  [BillPughSingleton] Log: " + message);
    }
}

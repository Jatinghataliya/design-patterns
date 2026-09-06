package creational.singleton;

/**
 * 1. Eager Initialization Singleton.
 * Instance is created when the class is loaded by JVM.
 * - Thread-safe: Yes (guaranteed by JVM classloader)
 * - Lazy: No (created even if never used)
 */
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        System.out.println("  [EagerSingleton] Initializing instance eagerly at class load time.");
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public void logMessage(String message) {
        System.out.println("  [EagerSingleton] Log: " + message);
    }
}

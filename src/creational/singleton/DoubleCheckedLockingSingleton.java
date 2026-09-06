package creational.singleton;

import java.io.Serializable;

/**
 * 2. Thread-Safe Lazy Singleton using Double-Checked Locking.
 * - Thread-safe: Yes
 * - Lazy: Yes
 * - Uses 'volatile' to prevent instruction reordering by the JVM.
 * - Implements readResolve() to prevent deserialization breaking the singleton.
 */
public class DoubleCheckedLockingSingleton implements Serializable {
    private static final long serialVersionUID = 1L;

    // volatile keyword ensures that multiple threads handle the instance variable correctly
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() {
        // Reflection protection
        if (instance != null) {
            throw new RuntimeException("Instance already exists. Use getInstance() instead of reflection.");
        }
        System.out.println("  [DoubleCheckedLocking] Initializing instance lazily.");
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) { // 1st check (no lock)
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) { // 2nd check (with lock)
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    /**
     * Prevents creating a new instance during Java deserialization.
     */
    protected Object readResolve() {
        return getInstance();
    }

    public void logMessage(String message) {
        System.out.println("  [DoubleCheckedLocking] Log: " + message);
    }
}

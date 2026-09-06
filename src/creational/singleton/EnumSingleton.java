package creational.singleton;

/**
 * 4. Enum Singleton (Effective Java Recommendation by Joshua Bloch).
 * - Thread-safe: Yes (handled internally by JVM)
 * - Reflection-proof: Yes (JVM explicitly forbids creating enum instances via reflection)
 * - Serialization-proof: Yes (JVM guarantees single enum instance preservation)
 */
public enum EnumSingleton {
    INSTANCE;

    private int counter = 0;

    public void logMessage(String message) {
        counter++;
        System.out.println("  [EnumSingleton] (Op #" + counter + ") Log: " + message);
    }

    public int getCounter() {
        return counter;
    }
}

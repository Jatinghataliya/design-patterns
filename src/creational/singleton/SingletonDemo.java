package creational.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Demo runner for Singleton Pattern.
 * Tests multithreading concurrency, reflection attacks, and serialization attacks.
 */
public class SingletonDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("==================================================");
        System.out.println("          SINGLETON DESIGN PATTERN DEMO           ");
        System.out.println("==================================================\n");

        // 1. Basic usage and identity check
        System.out.println("--- 1. Basic Instance Verification ---");
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        System.out.println("  Eager: eager1 == eager2 ? " + (eager1 == eager2));

        BillPughSingleton billPugh1 = BillPughSingleton.getInstance();
        BillPughSingleton billPugh2 = BillPughSingleton.getInstance();
        System.out.println("  Bill Pugh: billPugh1 == billPugh2 ? " + (billPugh1 == billPugh2));

        EnumSingleton enum1 = EnumSingleton.INSTANCE;
        EnumSingleton enum2 = EnumSingleton.INSTANCE;
        System.out.println("  Enum: enum1 == enum2 ? " + (enum1 == enum2));

        // 2. Multithreading Test (Concurrent calls to DoubleCheckedLockingSingleton)
        System.out.println("\n--- 2. Multithreading Concurrency Test (5 Threads) ---");
        int threadCount = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        DoubleCheckedLockingSingleton[] results = new DoubleCheckedLockingSingleton[threadCount];

        for (int i = 0; i < threadCount; i++) {
            final int index = i;
            executor.submit(() -> {
                results[index] = DoubleCheckedLockingSingleton.getInstance();
                latch.countDown();
            });
        }
        latch.await();
        executor.shutdown();

        boolean allSame = true;
        for (int i = 1; i < threadCount; i++) {
            if (results[i] != results[0]) {
                allSame = false;
                break;
            }
        }
        System.out.println("  All concurrent threads received identical instance? " + allSame);
        System.out.println("  Instance HashCode: " + System.identityHashCode(results[0]));

        // 3. Serialization Safety Test
        System.out.println("\n--- 3. Serialization & Deserialization Test ---");
        DoubleCheckedLockingSingleton origInstance = DoubleCheckedLockingSingleton.getInstance();

        // Serialize
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(origInstance);
        oos.close();

        // Deserialize
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        DoubleCheckedLockingSingleton deserializedInstance = (DoubleCheckedLockingSingleton) ois.readObject();
        ois.close();

        System.out.println("  Original Instance HashCode    : " + System.identityHashCode(origInstance));
        System.out.println("  Deserialized Instance HashCode: " + System.identityHashCode(deserializedInstance));
        System.out.println("  origInstance == deserializedInstance ? " + (origInstance == deserializedInstance));

        // 4. Enum Singleton Protection from Reflection
        System.out.println("\n--- 4. Reflection Attack Defense on Enum Singleton ---");
        try {
            Constructor<?>[] constructors = EnumSingleton.class.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                constructor.setAccessible(true);
                constructor.newInstance(); // Should throw IllegalArgumentException
            }
        } catch (Exception e) {
            System.out.println("  [Defense Succeeded] JVM blocked reflection on Enum: " + e.getCause());
        }

        // 5. Practical Operation with Enum Singleton
        System.out.println("\n--- 5. Practical Usage ---");
        EnumSingleton.INSTANCE.logMessage("Database connection pool initialized");
        EnumSingleton.INSTANCE.logMessage("User 'admin' authenticated");
    }
}

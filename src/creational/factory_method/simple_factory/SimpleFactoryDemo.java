package creational.factory_method.simple_factory;

import creational.factory_method.transport.Transport;

/**
 * Demo runner for Simple Factory (Parametrized Factory).
 */
public class SimpleFactoryDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("          SIMPLE FACTORY PATTERN DEMO             ");
        System.out.println("==================================================\n");

        // 1. Requesting by Enum
        System.out.println("--- 1. Creating via Enum: TRUCK ---");
        Transport truck = TransportFactory.createTransport(TransportFactory.TransportType.TRUCK);
        truck.deliver();

        // 2. Requesting by String (e.g., config, HTTP request parameter, DB value)
        System.out.println("\n--- 2. Creating via String: 'ship' ---");
        String userInput = "ship";
        Transport ship = TransportFactory.createTransport(userInput);
        ship.deliver();

        System.out.println("\n--- 3. Creating via String: 'plane' ---");
        Transport plane = TransportFactory.createTransport("plane");
        plane.deliver();

        // 4. Handling invalid input gracefully
        System.out.println("\n--- 4. Handling Invalid Input: 'rocket' ---");
        try {
            TransportFactory.createTransport("rocket");
        } catch (IllegalArgumentException e) {
            System.out.println("  [Error Caught]: " + e.getMessage());
        }
    }
}

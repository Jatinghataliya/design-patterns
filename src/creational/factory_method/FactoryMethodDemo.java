package creational.factory_method;

import creational.factory_method.logistics.AirLogistics;
import creational.factory_method.logistics.Logistics;
import creational.factory_method.logistics.RoadLogistics;
import creational.factory_method.logistics.SeaLogistics;

/**
 * Demo runner for Factory Method Pattern.
 */
public class FactoryMethodDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("       FACTORY METHOD DESIGN PATTERN DEMO         ");
        System.out.println("==================================================\n");

        // 1. Road logistics creates and uses Trucks
        System.out.println("--- Scenario 1: Standard Land Delivery ---");
        Logistics roadLogistics = new RoadLogistics();
        roadLogistics.planDelivery();

        // 2. Sea logistics creates and uses Ships
        System.out.println("--- Scenario 2: Overseas Delivery ---");
        Logistics seaLogistics = new SeaLogistics();
        seaLogistics.planDelivery();

        // 3. Air logistics creates and uses Planes
        System.out.println("--- Scenario 3: Express Rush Delivery ---");
        Logistics airLogistics = new AirLogistics();
        airLogistics.planDelivery();
    }
}

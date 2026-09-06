package creational.factory_method.transport;

/**
 * Concrete Product: Truck
 */
public class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("  [Truck] Delivering cargo by land in a container truck.");
    }

    @Override
    public String getTransportType() {
        return "Land Transport";
    }
}

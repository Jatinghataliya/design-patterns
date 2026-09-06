package creational.factory_method.transport;

/**
 * Concrete Product: Plane
 */
public class Plane implements Transport {
    @Override
    public void deliver() {
        System.out.println("  [Plane] Delivering cargo by air in a cargo airplane.");
    }

    @Override
    public String getTransportType() {
        return "Air Transport";
    }
}

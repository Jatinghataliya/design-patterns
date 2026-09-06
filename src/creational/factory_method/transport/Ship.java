package creational.factory_method.transport;

/**
 * Concrete Product: Ship
 */
public class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("  [Ship] Delivering cargo by sea in a cargo ship.");
    }

    @Override
    public String getTransportType() {
        return "Sea Transport";
    }
}

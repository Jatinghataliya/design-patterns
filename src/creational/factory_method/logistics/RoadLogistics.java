package creational.factory_method.logistics;

import creational.factory_method.transport.Transport;
import creational.factory_method.transport.Truck;

/**
 * Concrete Creator: RoadLogistics
 * Overrides the factory method to return a Truck.
 */
public class RoadLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}

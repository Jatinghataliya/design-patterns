package creational.factory_method.logistics;

import creational.factory_method.transport.Plane;
import creational.factory_method.transport.Transport;

/**
 * Concrete Creator: AirLogistics
 * Overrides the factory method to return a Plane.
 */
public class AirLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Plane();
    }
}

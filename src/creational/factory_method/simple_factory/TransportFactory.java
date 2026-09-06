package creational.factory_method.simple_factory;

import creational.factory_method.transport.Plane;
import creational.factory_method.transport.Ship;
import creational.factory_method.transport.Transport;
import creational.factory_method.transport.Truck;

/**
 * Simple Factory (Parametrized Factory).
 * A single class with a method that takes an identifier/type and returns the corresponding object.
 */
public class TransportFactory {

    public enum TransportType {
        TRUCK,
        SHIP,
        PLANE
    }

    /**
     * Factory method based on enum/string parameter.
     */
    public static Transport createTransport(TransportType type) {
        if (type == null) {
            throw new IllegalArgumentException("Transport type cannot be null");
        }

        switch (type) {
            case TRUCK:
                return new Truck();
            case SHIP:
                return new Ship();
            case PLANE:
                return new Plane();
            default:
                throw new IllegalArgumentException("Unknown transport type: " + type);
        }
    }

    /**
     * Overloaded factory method accepting a String (e.g. from config or user input).
     */
    public static Transport createTransport(String typeName) {
        if (typeName == null || typeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Transport type string cannot be null or empty");
        }

        try {
            TransportType type = TransportType.valueOf(typeName.trim().toUpperCase());
            return createTransport(type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported transport type: '" + typeName + "'. Supported: TRUCK, SHIP, PLANE");
        }
    }
}

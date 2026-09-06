package creational.factory_method.logistics;

import creational.factory_method.transport.Transport;

/**
 * Creator (Abstract Factory Class).
 * Declares the factory method that returns a new product object.
 * Also contains core business logic that relies on the product interface.
 */
public abstract class Logistics {

    /**
     * Core business logic. Subclasses don't need to change this logic;
     * they only change the product created.
     */
    public void planDelivery() {
        System.out.println("Logistics: Initiating delivery planning process...");
        Transport transport = createTransport();
        System.out.println("Logistics: Assigned transport category -> " + transport.getTransportType());
        transport.deliver();
        System.out.println("Logistics: Delivery scheduled successfully.\n");
    }

    /**
     * The Factory Method.
     * Concrete subclasses override this to produce specific Transport types.
     */
    public abstract Transport createTransport();
}

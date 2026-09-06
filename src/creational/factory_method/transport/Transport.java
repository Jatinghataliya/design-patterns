package creational.factory_method.transport;

/**
 * Product Interface.
 * Declares the operations that all concrete products must implement.
 */
public interface Transport {
    void deliver();
    String getTransportType();
}

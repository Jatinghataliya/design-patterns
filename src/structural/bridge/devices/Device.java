package structural.bridge.devices;

/**
 * Implementation Interface: Device.
 * Declares low-level operations for any media hardware device.
 */
public interface Device {
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int percent);
    int getChannel();
    void setChannel(int channel);
    String getDeviceName();
}

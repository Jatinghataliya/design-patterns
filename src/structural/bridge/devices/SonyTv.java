package structural.bridge.devices;

/**
 * Concrete Implementation 1: Sony Television.
 */
public class SonyTv implements Device {
    private boolean on = false;
    private int volume = 20;
    private int channel = 1;

    @Override
    public boolean isEnabled() { return on; }

    @Override
    public void enable() {
        this.on = true;
        System.out.println("  [Sony TV] Powered ON. Displaying BRAVIA 4K HDR home screen.");
    }

    @Override
    public void disable() {
        this.on = false;
        System.out.println("  [Sony TV] Powered OFF (Standby mode).");
    }

    @Override
    public int getVolume() { return volume; }

    @Override
    public void setVolume(int percent) {
        this.volume = Math.max(0, Math.min(100, percent));
        System.out.printf("  [Sony TV] Volume adjusted to %d%%%n", this.volume);
    }

    @Override
    public int getChannel() { return channel; }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.printf("  [Sony TV] Switched to TV Channel #%d%n", this.channel);
    }

    @Override
    public String getDeviceName() {
        return "Sony Bravia 4K TV";
    }
}

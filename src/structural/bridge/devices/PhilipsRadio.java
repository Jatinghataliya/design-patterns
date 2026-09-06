package structural.bridge.devices;

/**
 * Concrete Implementation 2: Philips Sound System / Radio.
 */
public class PhilipsRadio implements Device {
    private boolean on = false;
    private int volume = 15;
    private int frequencyChannel = 88; // e.g. 88.5 FM

    @Override
    public boolean isEnabled() { return on; }

    @Override
    public void enable() {
        this.on = true;
        System.out.println("  [Philips Radio] Tuner ON. FM/AM amplifier active.");
    }

    @Override
    public void disable() {
        this.on = false;
        System.out.println("  [Philips Radio] Tuner OFF.");
    }

    @Override
    public int getVolume() { return volume; }

    @Override
    public void setVolume(int percent) {
        this.volume = Math.max(0, Math.min(100, percent));
        System.out.printf("  [Philips Radio] Volume adjusted to %d%%%n", this.volume);
    }

    @Override
    public int getChannel() { return frequencyChannel; }

    @Override
    public void setChannel(int channel) {
        this.frequencyChannel = channel;
        System.out.printf("  [Philips Radio] Tuned to FM Station %d.1 MHz%n", this.frequencyChannel);
    }

    @Override
    public String getDeviceName() {
        return "Philips Audio System";
    }
}

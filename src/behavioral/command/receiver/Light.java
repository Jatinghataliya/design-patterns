package behavioral.command.receiver;

/**
 * Receiver 1: Light
 * Performs the actual operations when requested by a command.
 */
public class Light {
    private final String location;
    private boolean isOn = false;
    private int brightness = 100;

    public Light(String location) {
        this.location = location;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.printf("  [Light - %s] Turned ON (Brightness: %d%%)%n", location, brightness);
    }

    public void turnOff() {
        this.isOn = false;
        System.out.printf("  [Light - %s] Turned OFF%n", location);
    }

    public void setBrightness(int level) {
        this.brightness = Math.max(0, Math.min(100, level));
        System.out.printf("  [Light - %s] Brightness set to %d%%%n", location, brightness);
    }

    public boolean isOn() { return isOn; }
    public int getBrightness() { return brightness; }
    public String getLocation() { return location; }
}

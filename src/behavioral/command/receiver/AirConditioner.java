package behavioral.command.receiver;

/**
 * Receiver 2: Air Conditioner
 */
public class AirConditioner {
    private final String location;
    private boolean isOn = false;
    private int temperature = 24; // Default 24C

    public AirConditioner(String location) {
        this.location = location;
    }

    public void turnOn() {
        this.isOn = true;
        System.out.printf("  [AC - %s] Turned ON (Target Temp: %d°C)%n", location, temperature);
    }

    public void turnOff() {
        this.isOn = false;
        System.out.printf("  [AC - %s] Turned OFF%n", location);
    }

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.printf("  [AC - %s] Temperature set to %d°C%n", location, temperature);
    }

    public boolean isOn() { return isOn; }
    public int getTemperature() { return temperature; }
    public String getLocation() { return location; }
}

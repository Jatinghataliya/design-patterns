package structural.facade.subsystem;

/**
 * Subsystem 2: 4K Laser Projector.
 */
public class Projector {
    public void on() {
        System.out.println("  [Projector] Powering ON 4K Laser Projector...");
    }

    public void setInput(String source) {
        System.out.printf("  [Projector] Input source switched to '%s'%n", source);
    }

    public void setWideScreenMode() {
        System.out.println("  [Projector] Aspect ratio set to 21:9 CinemaScope Widescreen.");
    }

    public void off() {
        System.out.println("  [Projector] Powering OFF Projector (Cooling fans running)...");
    }
}

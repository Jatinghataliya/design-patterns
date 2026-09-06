package structural.facade.subsystem;

/**
 * Subsystem 1: Smart Lighting System.
 */
public class SmartLighting {
    public void dim(int level) {
        System.out.printf("  [SmartLighting] Dimming theater ambient lights to %d%%%n", level);
    }

    public void on() {
        System.out.println("  [SmartLighting] Theater lights set to full brightness (100%)");
    }
}

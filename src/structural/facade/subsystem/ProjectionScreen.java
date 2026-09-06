package structural.facade.subsystem;

/**
 * Subsystem 3: Motorized Projection Screen.
 */
public class ProjectionScreen {
    public void down() {
        System.out.println("  [Screen] Lowering 120-inch motorized projection screen...");
    }

    public void up() {
        System.out.println("  [Screen] Retracting motorized projection screen into ceiling...");
    }
}

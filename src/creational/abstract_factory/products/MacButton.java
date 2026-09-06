package creational.abstract_factory.products;

/**
 * Concrete Product A2: Mac-styled Button
 */
public class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("  [Mac Button] Rendering rounded corner button with macOS brushed metal style.");
    }

    @Override
    public void onClick() {
        System.out.println("  [Mac Button] Clicked with macOS ripple animation.");
    }
}

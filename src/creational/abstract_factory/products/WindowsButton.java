package creational.abstract_factory.products;

/**
 * Concrete Product A1: Windows-styled Button
 */
public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("  [Windows Button] Rendering flat rectangular button with Windows style.");
    }

    @Override
    public void onClick() {
        System.out.println("  [Windows Button] Clicked with Windows click animation.");
    }
}
